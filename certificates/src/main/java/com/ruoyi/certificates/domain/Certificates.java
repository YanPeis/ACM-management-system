package com.ruoyi.certificates.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * certificates对象 certificates
 * 
 * @author ruoyi
 * @date 2022-03-30
 */
public class Certificates extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long certificatesId;

    /** 获奖者姓名 */
    @Excel(name = "获奖者姓名")
    private String certificatesOwnerName;

    /** 获奖者学号 */
    @Excel(name = "获奖者学号")
    private String certificatesOwnerScore;

    /** 奖项名称 */
    @Excel(name = "奖项名称")
    private String certificatesName;

    /** 奖状路径 */
    @Excel(name = "奖状路径")
    private String certificatesPath;

    public void setCertificatesId(Long certificatesId) 
    {
        this.certificatesId = certificatesId;
    }

    public Long getCertificatesId() 
    {
        return certificatesId;
    }
    public void setCertificatesOwnerName(String certificatesOwnerName) 
    {
        this.certificatesOwnerName = certificatesOwnerName;
    }

    public String getCertificatesOwnerName() 
    {
        return certificatesOwnerName;
    }
    public void setCertificatesOwnerScore(String certificatesOwnerScore) 
    {
        this.certificatesOwnerScore = certificatesOwnerScore;
    }

    public String getCertificatesOwnerScore() 
    {
        return certificatesOwnerScore;
    }
    public void setCertificatesName(String certificatesName) 
    {
        this.certificatesName = certificatesName;
    }

    public String getCertificatesName() 
    {
        return certificatesName;
    }
    public void setCertificatesPath(String certificatesPath) 
    {
        this.certificatesPath = certificatesPath;
    }

    public String getCertificatesPath() 
    {
        return certificatesPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("certificatesId", getCertificatesId())
            .append("certificatesOwnerName", getCertificatesOwnerName())
            .append("certificatesOwnerScore", getCertificatesOwnerScore())
            .append("certificatesName", getCertificatesName())
            .append("certificatesPath", getCertificatesPath())
            .toString();
    }
}
