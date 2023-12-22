package com.hh.pms.cm.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 签署执行状态对象 bs_sign
 *
 * @author ruoyi
 * @date 2023-11-30
 */
public class BsSign extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 合同签署ID
     */
    @Excel(name = "合同签署ID")
    private Long gnId;

    /**
     * 合同ID
     */
    private Long eid;

    /**
     * 签署方数
     */
    @Excel(name = "签署方数")
    private Long gnSignatorycount;

    /**
     * 我方主体
     */
    @Excel(name = "我方主体")
    private String gnSub;

    /**
     * 乙方供应商ID
     */
    @Excel(name = "乙方供应商ID")
    private Long gnPbid;

    /**
     * 乙方名称
     */
    @Excel(name = "乙方名称")
    private String gnPbname;

    /**
     * 乙方地址
     */
    @Excel(name = "乙方地址")
    private String gnPbaddress;

    /**
     * 乙方联系人
     */
    @Excel(name = "乙方联系人")
    private String gnPbcontact;

    /**
     * 乙方联系方式
     */
    @Excel(name = "乙方联系方式")
    private String gnPbcif;

    /**
     * 乙方开户行
     */
    @Excel(name = "乙方开户行")
    private String gnPbbank;

    /**
     * 乙方开户账号
     */
    @Excel(name = "乙方开户账号")
    private String gnPbaccount;

    /**
     * 合同方金额
     */
    @Excel(name = "合同方金额")
    private BigDecimal gnPbamount;

    /**
     * 币别
     */
    @Excel(name = "币别")
    private String gnPbcurrency;

    /**
     * 已支付金额
     */
    @Excel(name = "已支付金额")
    private BigDecimal gnPbpayment;

    /**
     * 锁定金额
     */
    @Excel(name = "锁定金额")
    private BigDecimal gnPbfixedprice;

    /**
     * 剩余金额
     */
    @Excel(name = "剩余金额")
    private BigDecimal gnPbbalance;

    /**
     * 丙方名称
     */
    @Excel(name = "丙方名称")
    private String gnPcname;

    /**
     * 丙方地址
     */
    @Excel(name = "丙方地址")
    private String gnPcaddress;

    /**
     * 丙方联系人
     */
    @Excel(name = "丙方联系人")
    private String gnPccontact;

    /**
     * 丙方联系方式
     */
    @Excel(name = "丙方联系方式")
    private String gnPccif;

    /**
     * 丙方开户行
     */
    @Excel(name = "丙方开户行")
    private String gnPcbank;

    /**
     * 丙方开户账号
     */
    @Excel(name = "丙方开户账号")
    private String gnPcaccount;

    /**
     * 合同方金额
     */
    @Excel(name = "合同方金额")
    private BigDecimal gnPcamount;

    /**
     * 币别
     */
    @Excel(name = "币别")
    private String gnPccurrency;

    /**
     * 已支付金额
     */
    @Excel(name = "已支付金额")
    private BigDecimal gnPcpayment;

    /**
     * 锁定金额
     */
    @Excel(name = "锁定金额")
    private BigDecimal gnPcfixedprice;

    /**
     * 剩余金额
     */
    @Excel(name = "剩余金额")
    private BigDecimal gnPcbalance;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public void setGnId(Long gnId) {
        this.gnId = gnId;
    }

    public Long getGnId() {
        return gnId;
    }

    public void setGnSignatorycount(Long gnSignatorycount) {
        this.gnSignatorycount = gnSignatorycount;
    }

    public Long getGnSignatorycount() {
        return gnSignatorycount;
    }

    public void setGnSub(String gnSub) {
        this.gnSub = gnSub;
    }

    public String getGnSub() {
        return gnSub;
    }

    public void setGnPbid(Long gnPbid) {
        this.gnPbid = gnPbid;
    }

    public Long getGnPbid() {
        return gnPbid;
    }

    public void setGnPbname(String gnPbname) {
        this.gnPbname = gnPbname;
    }

    public String getGnPbname() {
        return gnPbname;
    }

    public void setGnPbaddress(String gnPbaddress) {
        this.gnPbaddress = gnPbaddress;
    }

    public String getGnPbaddress() {
        return gnPbaddress;
    }

    public void setGnPbcontact(String gnPbcontact) {
        this.gnPbcontact = gnPbcontact;
    }

    public String getGnPbcontact() {
        return gnPbcontact;
    }

    public void setGnPbcif(String gnPbcif) {
        this.gnPbcif = gnPbcif;
    }

    public String getGnPbcif() {
        return gnPbcif;
    }

    public void setGnPbbank(String gnPbbank) {
        this.gnPbbank = gnPbbank;
    }

    public String getGnPbbank() {
        return gnPbbank;
    }

    public void setGnPbaccount(String gnPbaccount) {
        this.gnPbaccount = gnPbaccount;
    }

    public String getGnPbaccount() {
        return gnPbaccount;
    }


    public void setGnPbcurrency(String gnPbcurrency) {
        this.gnPbcurrency = gnPbcurrency;
    }

    public String getGnPbcurrency() {
        return gnPbcurrency;
    }

    public void setGnPcname(String gnPcname) {
        this.gnPcname = gnPcname;
    }

    public String getGnPcname() {
        return gnPcname;
    }

    public void setGnPcaddress(String gnPcaddress) {
        this.gnPcaddress = gnPcaddress;
    }

    public String getGnPcaddress() {
        return gnPcaddress;
    }

    public void setGnPccontact(String gnPccontact) {
        this.gnPccontact = gnPccontact;
    }

    public String getGnPccontact() {
        return gnPccontact;
    }

    public void setGnPccif(String gnPccif) {
        this.gnPccif = gnPccif;
    }

    public String getGnPccif() {
        return gnPccif;
    }

    public void setGnPcbank(String gnPcbank) {
        this.gnPcbank = gnPcbank;
    }

    public String getGnPcbank() {
        return gnPcbank;
    }

    public void setGnPcaccount(String gnPcaccount) {
        this.gnPcaccount = gnPcaccount;
    }

    public String getGnPcaccount() {
        return gnPcaccount;
    }

    public void setGnPccurrency(String gnPccurrency) {
        this.gnPccurrency = gnPccurrency;
    }

    public String getGnPccurrency() {
        return gnPccurrency;
    }

    public BigDecimal getGnPbamount() {
        return gnPbamount;
    }

    public void setGnPbamount(BigDecimal gnPbamount) {
        this.gnPbamount = gnPbamount;
    }

    public BigDecimal getGnPbpayment() {
        return gnPbpayment;
    }

    public void setGnPbpayment(BigDecimal gnPbpayment) {
        this.gnPbpayment = gnPbpayment;
    }

    public BigDecimal getGnPbfixedprice() {
        return gnPbfixedprice;
    }

    public void setGnPbfixedprice(BigDecimal gnPbfixedprice) {
        this.gnPbfixedprice = gnPbfixedprice;
    }

    public BigDecimal getGnPbbalance() {
        return gnPbbalance;
    }

    public void setGnPbbalance(BigDecimal gnPbbalance) {
        this.gnPbbalance = gnPbbalance;
    }

    public BigDecimal getGnPcamount() {
        return gnPcamount;
    }

    public void setGnPcamount(BigDecimal gnPcamount) {
        this.gnPcamount = gnPcamount;
    }

    public BigDecimal getGnPcpayment() {
        return gnPcpayment;
    }

    public void setGnPcpayment(BigDecimal gnPcpayment) {
        this.gnPcpayment = gnPcpayment;
    }

    public BigDecimal getGnPcfixedprice() {
        return gnPcfixedprice;
    }

    public void setGnPcfixedprice(BigDecimal gnPcfixedprice) {
        this.gnPcfixedprice = gnPcfixedprice;
    }

    public BigDecimal getGnPcbalance() {
        return gnPcbalance;
    }

    public void setGnPcbalance(BigDecimal gnPcbalance) {
        this.gnPcbalance = gnPcbalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gnId", getGnId())
                .append("eid", getEid())
                .append("gnSignatorycount", getGnSignatorycount())
                .append("gnSub", getGnSub())
                .append("gnPbid", getGnPbid())
                .append("gnPbname", getGnPbname())
                .append("gnPbaddress", getGnPbaddress())
                .append("gnPbcontact", getGnPbcontact())
                .append("gnPbcif", getGnPbcif())
                .append("gnPbbank", getGnPbbank())
                .append("gnPbaccount", getGnPbaccount())
                .append("gnPbamount", getGnPbamount())
                .append("gnPbcurrency", getGnPbcurrency())
                .append("gnPbpayment", getGnPbpayment())
                .append("gnPbfixedprice", getGnPbfixedprice())
                .append("gnPbbalance", getGnPbbalance())
                .append("gnPcname", getGnPcname())
                .append("gnPcaddress", getGnPcaddress())
                .append("gnPccontact", getGnPccontact())
                .append("gnPccif", getGnPccif())
                .append("gnPcbank", getGnPcbank())
                .append("gnPcaccount", getGnPcaccount())
                .append("gnPcamount", getGnPcamount())
                .append("gnPccurrency", getGnPccurrency())
                .append("gnPcpayment", getGnPcpayment())
                .append("gnPcfixedprice", getGnPcfixedprice())
                .append("gnPcbalance", getGnPcbalance())
                .toString();
    }
}