package com.hh.pms.sae.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 财务状态对象 bs_financial_status
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsFinancialStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 财务状态ID */
    private Long cId;

    /** 年度 */
    @Excel(name = "年度")
    private Long cAnnual;

    /** 净利润（万元） */
    @Excel(name = "净利润", readConverterExp = "万=元")
    private Long cNetProfit;

    /** 资产负债率（%） */
    @Excel(name = "资产负债率", readConverterExp = "%=")
    private String cLev;

    /** 财务审计报告扫描件 */
    @Excel(name = "财务审计报告扫描件")
    private Long cScanFar;

    /** 附件审计报告 */
    @Excel(name = "附件审计报告")
    private Long cScanAar;

    /** 资产负债扫描件 */
    @Excel(name = "资产负债扫描件")
    private Long cScanAl;

    /** 利润表扫描件 */
    @Excel(name = "利润表扫描件")
    private Long cScanIs;

    /** 现金流量表扫描件 */
    @Excel(name = "现金流量表扫描件")
    private Long cScanCfs;

    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }
    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setcAnnual(Long cAnnual) 
    {
        this.cAnnual = cAnnual;
    }

    public Long getcAnnual() 
    {
        return cAnnual;
    }
    public void setcNetProfit(Long cNetProfit) 
    {
        this.cNetProfit = cNetProfit;
    }

    public Long getcNetProfit() 
    {
        return cNetProfit;
    }
    public void setcLev(String cLev) 
    {
        this.cLev = cLev;
    }

    public String getcLev() 
    {
        return cLev;
    }
    public void setcScanFar(Long cScanFar) 
    {
        this.cScanFar = cScanFar;
    }

    public Long getcScanFar() 
    {
        return cScanFar;
    }
    public void setcScanAar(Long cScanAar) 
    {
        this.cScanAar = cScanAar;
    }

    public Long getcScanAar() 
    {
        return cScanAar;
    }
    public void setcScanAl(Long cScanAl) 
    {
        this.cScanAl = cScanAl;
    }

    public Long getcScanAl() 
    {
        return cScanAl;
    }
    public void setcScanIs(Long cScanIs) 
    {
        this.cScanIs = cScanIs;
    }

    public Long getcScanIs() 
    {
        return cScanIs;
    }
    public void setcScanCfs(Long cScanCfs) 
    {
        this.cScanCfs = cScanCfs;
    }

    public Long getcScanCfs() 
    {
        return cScanCfs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hid", getHid())
            .append("cId", getcId())
            .append("cAnnual", getcAnnual())
            .append("cNetProfit", getcNetProfit())
            .append("cLev", getcLev())
            .append("cScanFar", getcScanFar())
            .append("cScanAar", getcScanAar())
            .append("cScanAl", getcScanAl())
            .append("cScanIs", getcScanIs())
            .append("cScanCfs", getcScanCfs())
            .toString();
    }
}
