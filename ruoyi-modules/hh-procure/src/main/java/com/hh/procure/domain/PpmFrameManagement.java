package com.hh.procure.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 框架协议管理对象 ppm_frame_management
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class PpmFrameManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 框架协议ID */
    private Long oid;

    /** 框架计划ID */
    @Excel(name = "框架计划ID")
    private Long jhId;

    /** 框架计划编号 */
    @Excel(name = "框架计划编号")
    private String oCode;

    /** 框架计划名称 */
    @Excel(name = "框架计划名称")
    private String oName;

    /** 采购方式 */
    @Excel(name = "采购方式")
    private String oWay;

    /** 协议供应商ID */
    @Excel(name = "协议供应商ID")
    private Long oBid;

    /** 合同管理状态 */
    @Excel(name = "合同管理状态")
    private Long oHstatus;

    public void setOid(Long oid) 
    {
        this.oid = oid;
    }

    public Long getOid() 
    {
        return oid;
    }
    public void setJhId(Long jhId) 
    {
        this.jhId = jhId;
    }

    public Long getJhId() 
    {
        return jhId;
    }
    public void setoCode(String oCode) 
    {
        this.oCode = oCode;
    }

    public String getoCode() 
    {
        return oCode;
    }
    public void setoName(String oName) 
    {
        this.oName = oName;
    }

    public String getoName() 
    {
        return oName;
    }
    public void setoWay(String oWay) 
    {
        this.oWay = oWay;
    }

    public String getoWay() 
    {
        return oWay;
    }
    public void setoBid(Long oBid) 
    {
        this.oBid = oBid;
    }

    public Long getoBid() 
    {
        return oBid;
    }
    public void setoHstatus(Long oHstatus) 
    {
        this.oHstatus = oHstatus;
    }

    public Long getoHstatus() 
    {
        return oHstatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oid", getOid())
            .append("jhId", getJhId())
            .append("oCode", getoCode())
            .append("oName", getoName())
            .append("oWay", getoWay())
            .append("oBid", getoBid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("oHstatus", getoHstatus())
            .toString();
    }
}
