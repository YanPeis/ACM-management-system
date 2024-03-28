package com.ruoyi.certificates.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.certificates.mapper.CertificatesMapper;
import com.ruoyi.certificates.domain.Certificates;
import com.ruoyi.certificates.service.ICertificatesService;

/**
 * certificatesService业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-30
 */
@Service
public class CertificatesServiceImpl implements ICertificatesService 
{
    @Autowired
    private CertificatesMapper certificatesMapper;

    /**
     * 查询certificates
     * 
     * @param certificatesId certificates主键
     * @return certificates
     */
    @Override
    public Certificates selectCertificatesByCertificatesId(Long certificatesId)
    {
        return certificatesMapper.selectCertificatesByCertificatesId(certificatesId);
    }

    /**
     * 查询certificates列表
     * 
     * @param certificates certificates
     * @return certificates
     */
    @Override
    public List<Certificates> selectCertificatesList(Certificates certificates)
    {
        return certificatesMapper.selectCertificatesList(certificates);
    }

    /**
     * 新增certificates
     * 
     * @param certificates certificates
     * @return 结果
     */
    @Override
    public int insertCertificates(Certificates certificates)
    {
        return certificatesMapper.insertCertificates(certificates);
    }

    /**
     * 修改certificates
     * 
     * @param certificates certificates
     * @return 结果
     */
    @Override
    public int updateCertificates(Certificates certificates)
    {
        return certificatesMapper.updateCertificates(certificates);
    }

    /**
     * 批量删除certificates
     * 
     * @param certificatesIds 需要删除的certificates主键
     * @return 结果
     */
    @Override
    public int deleteCertificatesByCertificatesIds(Long[] certificatesIds)
    {
        return certificatesMapper.deleteCertificatesByCertificatesIds(certificatesIds);
    }

    /**
     * 删除certificates信息
     * 
     * @param certificatesId certificates主键
     * @return 结果
     */
    @Override
    public int deleteCertificatesByCertificatesId(Long certificatesId)
    {
        return certificatesMapper.deleteCertificatesByCertificatesId(certificatesId);
    }
}
