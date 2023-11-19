package com.hh.pms.cm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 合同对象 bs_contract
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 合同ID */
    private Long eid;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String eXcode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String eXname;

    /** 中标人 */
    @Excel(name = "中标人")
    private String eWinningPerson;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String eHcode;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String eHname;

    /** 合同状态 */
    @Excel(name = "合同状态")
    private Long eStatus;

    /** 交付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交付日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eDeliveryTime;

    /** 合同管理状态 */
    @Excel(name = "合同管理状态")
    private Long oHstatus;

    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }
    public void setEid(Long eid) 
    {
        this.eid = eid;
    }

    public Long getEid() 
    {
        return eid;
    }
    public void seteXcode(String eXcode) 
    {
        this.eXcode = eXcode;
    }

    public String geteXcode() 
    {
        return eXcode;
    }
    public void seteXname(String eXname) 
    {
        this.eXname = eXname;
    }

    public String geteXname() 
    {
        return eXname;
    }
    public void seteWinningPerson(String eWinningPerson) 
    {
        this.eWinningPerson = eWinningPerson;
    }

    public String geteWinningPerson() 
    {
        return eWinningPerson;
    }
    public void seteHcode(String eHcode) 
    {
        this.eHcode = eHcode;
    }

    public String geteHcode() 
    {
        return eHcode;
    }
    public void seteHname(String eHname) 
    {
        this.eHname = eHname;
    }

    public String geteHname() 
    {
        return eHname;
    }
    public void seteStatus(Long eStatus) 
    {
        this.eStatus = eStatus;
    }

    public Long geteStatus() 
    {
        return eStatus;
    }
    public void seteDeliveryTime(Date eDeliveryTime) 
    {
        this.eDeliveryTime = eDeliveryTime;
    }

    public Date geteDeliveryTime() 
    {
        return eDeliveryTime;
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
            .append("hid", getHid())
            .append("eid", getEid())
            .append("eXcode", geteXcode())
            .append("eXname", geteXname())
            .append("eWinningPerson", geteWinningPerson())
            .append("eHcode", geteHcode())
            .append("eHname", geteHname())
            .append("eStatus", geteStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("eDeliveryTime", geteDeliveryTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("oHstatus", getoHstatus())
            .toString();
    }
}
