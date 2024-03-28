package com.ruoyi.web.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ojAccount.domain.OjAccount;
import com.ruoyi.ojAccount.service.IOjAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ojAccountController
 *
 * @author ruoyi
 * @date 2022-04-02
 */
@RestController
@RequestMapping("/ojAccount/ojAccount")
public class OjAccountController extends BaseController
{
    @Autowired
    private IOjAccountService ojAccountService;

    /**
     * 查询ojAccount列表
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(OjAccount ojAccount)
    {
        startPage();
        List<OjAccount> list = ojAccountService.selectOjAccountList(ojAccount);
        return getDataTable(list);
    }

    /**
     * 导出ojAccount列表
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:export')")
    @Log(title = "ojAccount", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OjAccount ojAccount)
    {
        List<OjAccount> list = ojAccountService.selectOjAccountList(ojAccount);
        ExcelUtil<OjAccount> util = new ExcelUtil<OjAccount>(OjAccount.class);
        util.exportExcel(response, list, "ojAccount数据");
    }

    /**
     * 获取ojAccount详细信息
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:query')")
    @GetMapping(value = "/{userscore}")
    public AjaxResult getInfo(@PathVariable("userscore") String userscore)
    {
        return AjaxResult.success(ojAccountService.selectOjAccountByUserscore(userscore));
    }

    /**
     * 新增ojAccount
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:add')")
    @Log(title = "ojAccount", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OjAccount ojAccount)
    {
        return toAjax(ojAccountService.insertOjAccount(ojAccount));
    }

    /**
     * 修改ojAccount
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:edit')")
    @Log(title = "ojAccount", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OjAccount ojAccount)
    {
        return toAjax(ojAccountService.updateOjAccount(ojAccount));
    }

    /**
     * 删除ojAccount
     */
    @PreAuthorize("@ss.hasPermi('ojAccount:ojAccount:remove')")
    @Log(title = "ojAccount", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userscores}")
    public AjaxResult remove(@PathVariable String[] userscores)
    {
        return toAjax(ojAccountService.deleteOjAccountByUserscores(userscores));
    }
}
