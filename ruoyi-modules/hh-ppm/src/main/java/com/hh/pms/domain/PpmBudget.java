package com.hh.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 预算对象 ppm_budget
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class PpmBudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预算ID */
    private Long duId;

    /** 采购计划ID */
    @Excel(name = "采购计划ID")
    private Long aid;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String duDept;

    /** 预算科目编号 */
    @Excel(name = "预算科目编号")
    private String duCode;

    /** 预算科目名称 */
    @Excel(name = "预算科目名称")
    private String duName;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long duTotal;

    /** 已使用金额 */
    @Excel(name = "已使用金额")
    private Long duUsedMoney;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private Long duHaveMoney;

    public void setDuId(Long duId) 
    {
        this.duId = duId;
    }

    public Long getDuId() 
    {
        return duId;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }
    public void setDuDept(String duDept) 
    {
        this.duDept = duDept;
    }

    public String getDuDept() 
    {
        return duDept;
    }
    public void setDuCode(String duCode) 
    {
        this.duCode = duCode;
    }

    public String getDuCode() 
    {
        return duCode;
    }
    public void setDuName(String duName) 
    {
        this.duName = duName;
    }

    public String getDuName() 
    {
        return duName;
    }
    public void setDuTotal(Long duTotal) 
    {
        this.duTotal = duTotal;
    }

    public Long getDuTotal() 
    {
        return duTotal;
    }
    public void setDuUsedMoney(Long duUsedMoney) 
    {
        this.duUsedMoney = duUsedMoney;
    }

    public Long getDuUsedMoney() 
    {
        return duUsedMoney;
    }
    public void setDuHaveMoney(Long duHaveMoney) 
    {
        this.duHaveMoney = duHaveMoney;
    }

    public Long getDuHaveMoney() 
    {
        return duHaveMoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duId", getDuId())
            .append("aid", getAid())
            .append("duDept", getDuDept())
            .append("duCode", getDuCode())
            .append("duName", getDuName())
            .append("duTotal", getDuTotal())
            .append("duUsedMoney", getDuUsedMoney())
            .append("duHaveMoney", getDuHaveMoney())
            .toString();
    }
}
