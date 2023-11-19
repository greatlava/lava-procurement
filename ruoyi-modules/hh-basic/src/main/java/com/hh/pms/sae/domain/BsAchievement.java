package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 业绩对象 bs_achievement
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsAchievement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业绩ID */
    private Long yjId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 采购单位 */
    @Excel(name = "采购单位")
    private String yjUnit;

    /** 合作方 */
    @Excel(name = "合作方")
    private String yjPartner;

    /** 签订日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yjDate;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private Long yjRmb;

    /** 买方业务代表 */
    @Excel(name = "买方业务代表")
    private String yjBbr;

    /** 买方业务代表联系电话 */
    @Excel(name = "买方业务代表联系电话")
    private String yjPhone;

    /** 合同扫描件 */
    @Excel(name = "合同扫描件")
    private String yjScanContract;

    /** 合同内容 */
    @Excel(name = "合同内容")
    private String yjContent;

    public void setYjId(Long yjId) 
    {
        this.yjId = yjId;
    }

    public Long getYjId() 
    {
        return yjId;
    }
    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }
    public void setYjUnit(String yjUnit) 
    {
        this.yjUnit = yjUnit;
    }

    public String getYjUnit() 
    {
        return yjUnit;
    }
    public void setYjPartner(String yjPartner) 
    {
        this.yjPartner = yjPartner;
    }

    public String getYjPartner() 
    {
        return yjPartner;
    }
    public void setYjDate(Date yjDate) 
    {
        this.yjDate = yjDate;
    }

    public Date getYjDate() 
    {
        return yjDate;
    }
    public void setYjRmb(Long yjRmb) 
    {
        this.yjRmb = yjRmb;
    }

    public Long getYjRmb() 
    {
        return yjRmb;
    }
    public void setYjBbr(String yjBbr) 
    {
        this.yjBbr = yjBbr;
    }

    public String getYjBbr() 
    {
        return yjBbr;
    }
    public void setYjPhone(String yjPhone) 
    {
        this.yjPhone = yjPhone;
    }

    public String getYjPhone() 
    {
        return yjPhone;
    }
    public void setYjScanContract(String yjScanContract) 
    {
        this.yjScanContract = yjScanContract;
    }

    public String getYjScanContract() 
    {
        return yjScanContract;
    }
    public void setYjContent(String yjContent) 
    {
        this.yjContent = yjContent;
    }

    public String getYjContent() 
    {
        return yjContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("yjId", getYjId())
            .append("hid", getHid())
            .append("yjUnit", getYjUnit())
            .append("yjPartner", getYjPartner())
            .append("yjDate", getYjDate())
            .append("yjRmb", getYjRmb())
            .append("yjBbr", getYjBbr())
            .append("yjPhone", getYjPhone())
            .append("yjScanContract", getYjScanContract())
            .append("yjContent", getYjContent())
            .toString();
    }
}
