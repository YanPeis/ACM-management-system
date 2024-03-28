package com.ruoyi.certificates.service;

import java.util.List;
import com.ruoyi.certificates.domain.Certificates;

/**
 * certificatesService接口
 * 
 * @author ruoyi
 * @date 2022-03-30
 */
public interface ICertificatesService 
{
    /**
     * 查询certificates
     * 
     * @param certificatesId certificates主键
     * @return certificates
     */
    public Certificates selectCertificatesByCertificatesId(Long certificatesId);

    /**
     * 查询certificates列表
     * 
     * @param certificates certificates
     * @return certificates集合
     */
    public List<Certificates> selectCertificatesList(Certificates certificates);

    /**
     * 新增certificates
     * 
     * @param certificates certificates
     * @return 结果
     */
    public int insertCertificates(Certificates certificates);

    /**
     * 修改certificates
     * 
     * @param certificates certificates
     * @return 结果
     */
    public int updateCertificates(Certificates certificates);

    /**
     * 批量删除certificates
     * 
     * @param certificatesIds 需要删除的certificates主键集合
     * @return 结果
     */
    public int deleteCertificatesByCertificatesIds(Long[] certificatesIds);

    /**
     * 删除certificates信息
     * 
     * @param certificatesId certificates主键
     * @return 结果
     */
    public int deleteCertificatesByCertificatesId(Long certificatesId);
}
