package com.hh.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 框架计划对象 ppm_frame_plan
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class PpmFramePlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 框架计划ID */
    private Long jhId;

    /** 框架协议ID */
    @Excel(name = "框架协议ID")
    private Long oid;

    /** 框架计划编码 */
    @Excel(name = "框架计划编码")
    private String jhCode;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String jhName;

    /** 计划预算 */
    @Excel(name = "计划预算")
    private Long jhYu;

    /** 创建部门 */
    @Excel(name = "创建部门")
    private String dept;

    /** 计划状态 */
    @Excel(name = "计划状态")
    private Long jhStatus;

    /** 审批人 */
    @Excel(name = "审批人")
    private String jhPerson;

    /** 采购方式 */
    @Excel(name = "采购方式")
    private String jhPmethod;

    public void setJhId(Long jhId) 
    {
        this.jhId = jhId;
    }

    public Long getJhId() 
    {
        return jhId;
    }
    public void setOid(Long oid) 
    {
        this.oid = oid;
    }

    public Long getOid() 
    {
        return oid;
    }
    public void setJhCode(String jhCode) 
    {
        this.jhCode = jhCode;
    }

    public String getJhCode() 
    {
        return jhCode;
    }
    public void setJhName(String jhName) 
    {
        this.jhName = jhName;
    }

    public String getJhName() 
    {
        return jhName;
    }
    public void setJhYu(Long jhYu) 
    {
        this.jhYu = jhYu;
    }

    public Long getJhYu() 
    {
        return jhYu;
    }
    public void setDept(String dept) 
    {
        this.dept = dept;
    }

    public String getDept() 
    {
        return dept;
    }
    public void setJhStatus(Long jhStatus) 
    {
        this.jhStatus = jhStatus;
    }

    public Long getJhStatus() 
    {
        return jhStatus;
    }
    public void setJhPerson(String jhPerson) 
    {
        this.jhPerson = jhPerson;
    }

    public String getJhPerson() 
    {
        return jhPerson;
    }
    public void setJhPmethod(String jhPmethod) 
    {
        this.jhPmethod = jhPmethod;
    }

    public String getJhPmethod() 
    {
        return jhPmethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jhId", getJhId())
            .append("oid", getOid())
            .append("jhCode", getJhCode())
            .append("jhName", getJhName())
            .append("jhYu", getJhYu())
            .append("dept", getDept())
            .append("jhStatus", getJhStatus())
            .append("jhPerson", getJhPerson())
            .append("jhPmethod", getJhPmethod())
            .toString();
    }
}
