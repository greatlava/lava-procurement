package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 企业资质对象 bs_enterprise
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资质ID */
    private Long zzId;

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String zzCertName;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String zzCertNo;

    /** 发证单位 */
    @Excel(name = "发证单位")
    private String zzUnit;

    /** 发证日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发证日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zzDate;

    /** 有效期至 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期至", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zzExpirationDate;

    /** 扫描件 */
    @Excel(name = "扫描件")
    private String zzScan;

    /** 证书内容 */
    @Excel(name = "证书内容")
    private String zzCertContent;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    public void setZzId(Long zzId) 
    {
        this.zzId = zzId;
    }

    public Long getZzId() 
    {
        return zzId;
    }
    public void setZzCertName(String zzCertName) 
    {
        this.zzCertName = zzCertName;
    }

    public String getZzCertName() 
    {
        return zzCertName;
    }
    public void setZzCertNo(String zzCertNo) 
    {
        this.zzCertNo = zzCertNo;
    }

    public String getZzCertNo() 
    {
        return zzCertNo;
    }
    public void setZzUnit(String zzUnit) 
    {
        this.zzUnit = zzUnit;
    }

    public String getZzUnit() 
    {
        return zzUnit;
    }
    public void setZzDate(Date zzDate) 
    {
        this.zzDate = zzDate;
    }

    public Date getZzDate() 
    {
        return zzDate;
    }
    public void setZzExpirationDate(Date zzExpirationDate) 
    {
        this.zzExpirationDate = zzExpirationDate;
    }

    public Date getZzExpirationDate() 
    {
        return zzExpirationDate;
    }
    public void setZzScan(String zzScan) 
    {
        this.zzScan = zzScan;
    }

    public String getZzScan() 
    {
        return zzScan;
    }
    public void setZzCertContent(String zzCertContent) 
    {
        this.zzCertContent = zzCertContent;
    }

    public String getZzCertContent() 
    {
        return zzCertContent;
    }
    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("zzId", getZzId())
            .append("zzCertName", getZzCertName())
            .append("zzCertNo", getZzCertNo())
            .append("zzUnit", getZzUnit())
            .append("zzDate", getZzDate())
            .append("zzExpirationDate", getZzExpirationDate())
            .append("zzScan", getZzScan())
            .append("zzCertContent", getZzCertContent())
            .append("hid", getHid())
            .toString();
    }
}
