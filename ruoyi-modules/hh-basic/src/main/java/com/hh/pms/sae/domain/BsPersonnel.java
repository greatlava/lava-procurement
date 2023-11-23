package com.hh.pms.sae.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 核心技术人员对象 bs_personnel
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 技术人员ID */
    private Long jsId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String jsName;

    /** 职位 */
    @Excel(name = "职位")
    private String jsPosition;

    /** 学历 */
    @Excel(name = "学历")
    private String jsEducation;

    /** 职称 */
    @Excel(name = "职称")
    private String jsTitle;

    public void setJsId(Long jsId) 
    {
        this.jsId = jsId;
    }

    public Long getJsId() 
    {
        return jsId;
    }
    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }
    public void setJsName(String jsName) 
    {
        this.jsName = jsName;
    }

    public String getJsName() 
    {
        return jsName;
    }
    public void setJsPosition(String jsPosition) 
    {
        this.jsPosition = jsPosition;
    }

    public String getJsPosition() 
    {
        return jsPosition;
    }
    public void setJsEducation(String jsEducation) 
    {
        this.jsEducation = jsEducation;
    }

    public String getJsEducation() 
    {
        return jsEducation;
    }
    public void setJsTitle(String jsTitle) 
    {
        this.jsTitle = jsTitle;
    }

    public String getJsTitle() 
    {
        return jsTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jsId", getJsId())
            .append("hid", getHid())
            .append("jsName", getJsName())
            .append("jsPosition", getJsPosition())
            .append("jsEducation", getJsEducation())
            .append("jsTitle", getJsTitle())
            .toString();
    }
}
