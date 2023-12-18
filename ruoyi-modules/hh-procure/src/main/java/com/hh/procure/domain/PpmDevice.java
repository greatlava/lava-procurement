package com.hh.procure.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 设备信息对象 ppm_device
 *
 * @author ruoyi
 * @date 2023-11-28
 */
public class PpmDevice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品编码
     */
    private String tid;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String tName;

    /**
     * 类别
     */
    @Excel(name = "类别")
    private String category;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    private String tUnit;

    /**
     * 税率
     */
    @Excel(name = "税率")
    private BigDecimal shui;

    /**
     * 需求说明
     */
    @Excel(name = "需求说明")
    private String tIllustrate;

    /**
     * 预算科目编号
     */
    @Excel(name = "预算科目编号")
    private String duCode;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String tNotes;

    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String tModel;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private BigDecimal tPrice;

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettName() {
        return tName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void settUnit(String tUnit) {
        this.tUnit = tUnit;
    }

    public String gettUnit() {
        return tUnit;
    }

    public void setShui(BigDecimal shui) {
        this.shui = shui;
    }

    public BigDecimal getShui() {
        return shui;
    }

    public void settIllustrate(String tIllustrate) {
        this.tIllustrate = tIllustrate;
    }

    public String gettIllustrate() {
        return tIllustrate;
    }

    public void setDuCode(String duCode) {
        this.duCode = duCode;
    }

    public String getDuCode() {
        return duCode;
    }

    public void settNotes(String tNotes) {
        this.tNotes = tNotes;
    }

    public String gettNotes() {
        return tNotes;
    }

    public void settModel(String tModel) {
        this.tModel = tModel;
    }

    public String gettModel() {
        return tModel;
    }

    public void settPrice(BigDecimal tPrice) {
        this.tPrice = tPrice;
    }

    public BigDecimal gettPrice() {
        return tPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tid", getTid())
                .append("tName", gettName())
                .append("category", getCategory())
                .append("tUnit", gettUnit())
                .append("shui", getShui())
                .append("tIllustrate", gettIllustrate())
                .append("duCode", getDuCode())
                .append("tNotes", gettNotes())
                .append("tModel", gettModel())
                .append("tPrice", gettPrice())
                .toString();
    }
}
