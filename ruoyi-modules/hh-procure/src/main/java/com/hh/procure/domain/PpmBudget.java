package com.hh.procure.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 预算对象 ppm_budget
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public class PpmBudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预算ID */
    @Excel(name = "预算ID")
    private String duId;


    /** 采购计划ID */
    @Excel(name = "采购计划ID")
    private String aid;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String duDept;



    /** 预算科目名称 */
    @Excel(name = "预算科目名称")
    private String duName;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal duTotal;


    public void setDuId(String duId) 
    {
        this.duId = duId;
    }

    public String getDuId() 
    {
        return duId;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setDuDept(String duDept)
    {
        this.duDept = duDept;
    }

    public String getDuDept() 
    {
        return duDept;
    }
    public void setDuName(String duName) 
    {
        this.duName = duName;
    }

    public String getDuName() 
    {
        return duName;
    }
    public void setDuTotal(BigDecimal duTotal) 
    {
        this.duTotal = duTotal;
    }

    public BigDecimal getDuTotal() 
    {
        return duTotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duId", getDuId())
            .append("aid", getAid())
            .append("duDept", getDuDept())
            .append("duName", getDuName())
            .append("duTotal", getDuTotal())
            .toString();
    }
}
