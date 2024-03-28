package com.ruoyi.web.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.submission.domain.submission;
import com.ruoyi.submission.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * submissionController
 * 
 * @author ruoyi
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/submission/submission")
public class SubmissionController extends BaseController
{
    @Autowired
    private ISubmissionService submissionService;

    /**
     * 查询submission列表
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:list')")
    @GetMapping("/list")
    public TableDataInfo list(submission submission)
    {
        startPage();
        List<com.ruoyi.submission.domain.submission> list = submissionService.selectSubmissionList(submission);
        return getDataTable(list);
    }

    /**
     * 导出submission列表
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:export')")
    @Log(title = "submission", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, submission submission)
    {
        List<com.ruoyi.submission.domain.submission> list = submissionService.selectSubmissionList(submission);
        ExcelUtil<com.ruoyi.submission.domain.submission> util = new ExcelUtil<com.ruoyi.submission.domain.submission>(com.ruoyi.submission.domain.submission.class);
        util.exportExcel(response, list, "submission数据");
    }

    /**
     * 获取submission详细信息
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:query')")
    @GetMapping(value = "/{submitid}")
    public AjaxResult getInfo(@PathVariable("submitid") String submitid)
    {
        return AjaxResult.success(submissionService.selectSubmissionBySubmitid(submitid));
    }

    /**
     * 新增submission
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:add')")
    @Log(title = "submission", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody submission submission)
    {
        return toAjax(submissionService.insertSubmission(submission));
    }

    /**
     * 修改submission
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:edit')")
    @Log(title = "submission", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody submission submission)
    {
        return toAjax(submissionService.updateSubmission(submission));
    }

    /**
     * 删除submission
     */
    @PreAuthorize("@ss.hasPermi('submission:submission:remove')")
    @Log(title = "submission", businessType = BusinessType.DELETE)
	@DeleteMapping("/{submitids}")
    public AjaxResult remove(@PathVariable String[] submitids)
    {
        return toAjax(submissionService.deleteSubmissionBySubmitids(submitids));
    }
}
