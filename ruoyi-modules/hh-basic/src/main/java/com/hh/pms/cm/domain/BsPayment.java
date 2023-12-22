package com.hh.pms.cm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class BsPayment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 支付约定ID
     */
    private Long payId;

    /**
     * 合同ID
     */
    @Excel(name = "合同ID")
    private Long eid;

    /**
     * 款项内容
     */
    @Excel(name = "款项内容")
    private Long payContent;

    /**
     * 付款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /**
     * 付款单位
     */
    @Excel(name = "付款单位")
    private String payer;

    /**
     * 收款合同方ID
     */
    @Excel(name = "收款合同方ID")
    private Long hid;

    /**
     * 收款合同方
     */
    @Excel(name = "收款合同方")
    private String hName;

    /**
     * 付款条件
     */
    @Excel(name = "付款条件")
    private String payTerms;

    /**
     * 付款金额
     */
    @Excel(name = "付款金额")
    private BigDecimal payAmount;

    /**
     * 违约责任
     */
    @Excel(name = "违约责任")
    private String debty;

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getEid() {
        return eid;
    }

    public void setPayContent(Long payContent) {
        this.payContent = payContent;
    }

    public Long getPayContent() {
        return payContent;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayer() {
        return payer;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getHid() {
        return hid;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethName() {
        return hName;
    }

    public void setPayTerms(String payTerms) {
        this.payTerms = payTerms;
    }

    public String getPayTerms() {
        return payTerms;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal                                                                getPayAmount() {
        return payAmount;
    }

    public void setDebty(String debty) {
        this.debty = debty;
    }

    public String getDebty() {
        return debty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("payId", getPayId())
                .append("eid", getEid())
                .append("payContent", getPayContent())
                .append("payDate", getPayDate())
                .append("payer", getPayer())
                .append("hid", getHid())
                .append("hName", gethName())
                .append("payTerms", getPayTerms())
                .append("payAmount", getPayAmount())
                .append("debty", getDebty())
                .toString();
    }
}
