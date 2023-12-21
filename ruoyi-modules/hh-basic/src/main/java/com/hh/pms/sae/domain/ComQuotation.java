package com.hh.pms.sae.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 报价单对象 com_quotation
 *
 * @author ruoyi
 * @date 2023-12-20
 */
public class ComQuotation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报价单号
     */
    private Long bjId;

    /**
     * 采购项目编号
     */
    @Excel(name = "采购项目编号")
    private String gfId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long bjSecond;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal bjTotal;

    /**
     * 供应商ID
     */
    private Long bjHid;

    /**
     * 是否删除
     */
    private Long isDelete;

    public Long getBjHid() {
        return bjHid;
    }

    public void setBjHid(Long bjHid) {
        this.bjHid = bjHid;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public void setBjId(Long bjId) {
        this.bjId = bjId;
    }

    public Long getBjId() {
        return bjId;
    }

    public void setGfId(String gfId) {
        this.gfId = gfId;
    }

    public String getGfId() {
        return gfId;
    }

    public void setBjSecond(Long bjSecond) {
        this.bjSecond = bjSecond;
    }

    public Long getBjSecond() {
        return bjSecond;
    }

    public void setBjTotal(BigDecimal bjTotal) {
        this.bjTotal = bjTotal;
    }

    public BigDecimal getBjTotal() {
        return bjTotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("bjId", getBjId())
                .append("gfId", getGfId())
                .append("bjSecond", getBjSecond())
                .append("bjTotal", getBjTotal())
                .toString();
    }
}

