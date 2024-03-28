package com.ruoyi.web.controller;

import com.ruoyi.certificates.domain.Certificates;
import com.ruoyi.certificates.service.ICertificatesService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * certificatesController
 * 
 * @author ruoyi
 * @date 2022-03-30
 */
@RestController
@RequestMapping("/certificates/certificates")
public class CertificatesController extends BaseController
{
    @Autowired
    private ICertificatesService certificatesService;

    /**
     * 查询certificates列表
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:list')")
    @GetMapping("/list")
    public TableDataInfo list(Certificates certificates)
    {
        startPage();
        List<Certificates> list = certificatesService.selectCertificatesList(certificates);
        return getDataTable(list);
    }
    @GetMapping("/listUser")
    public TableDataInfo listUser(Certificates certificates)
    {
        startPage();
        certificates.setCertificatesOwnerScore(String.valueOf(getUsername()));
        List<Certificates> list = certificatesService.selectCertificatesList(certificates);
        return getDataTable(list);
    }

    /**
     * 导出certificates列表
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:export')")
    @Log(title = "certificates", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Certificates certificates)
    {
        List<Certificates> list = certificatesService.selectCertificatesList(certificates);
        ExcelUtil<Certificates> util = new ExcelUtil<Certificates>(Certificates.class);
        util.exportExcel(response, list, "certificates数据");
    }

    /**
     * 获取certificates详细信息
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:query')")
    @GetMapping(value = "/{certificatesId}")
    public AjaxResult getInfo(@PathVariable("certificatesId") Long certificatesId)
    {
        return AjaxResult.success(certificatesService.selectCertificatesByCertificatesId(certificatesId));
    }

    /**
     * 新增certificates
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:add')")
    @Log(title = "certificates", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Certificates certificates)
    {
        return toAjax(certificatesService.insertCertificates(certificates));
    }

    /**
     * 修改certificates
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:edit')")
    @Log(title = "certificates", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Certificates certificates)
    {
        return toAjax(certificatesService.updateCertificates(certificates));
    }

    /**
     * 删除certificates
     */
    @PreAuthorize("@ss.hasPermi('certificates:certificates:remove')")
    @Log(title = "certificates", businessType = BusinessType.DELETE)
	@DeleteMapping("/{certificatesIds}")
    public AjaxResult remove(@PathVariable Long[] certificatesIds)
    {
        return toAjax(certificatesService.deleteCertificatesByCertificatesIds(certificatesIds));
    }
}
