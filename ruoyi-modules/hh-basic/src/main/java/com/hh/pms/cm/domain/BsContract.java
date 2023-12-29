package com.hh.pms.cm.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.pms.sae.domain.BsSupplier;
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
public class BsContract extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     */
    @Excel(name = "供应商ID")
    private Long hid;

    /**
     * 合同ID
     */
    private Long eid;

    /**
     * 招标项目ID
     */
    private Long sid;

    /**
     * 非招标项目ID
     */
    private Long gid;

    /**
     * 合同编号
     */
    @Excel(name = "合同编号")
    private String eHcode;

    /**
     * 合同名称
     */
    @Excel(name = "合同名称")
    private String eHname;

    /**
     * 合同状态
     */
    @Excel(name = "合同状态")
    private Long eStatus;

    /**
     * 交付日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交付日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eDeliveryTime;

    /**
     * 合同管理状态
     */
    @Excel(name = "合同管理状态")
    private Long oHstatus;

    private BsSupplier bsSupplier;

    public BsSupplier getBsSupplier() {
        return bsSupplier;
    }

    public void setBsSupplier(BsSupplier bsSupplier) {
        this.bsSupplier = bsSupplier;
    }

    /**
     * 合同类型
     */
    @Excel(name = "合同类型")
    private String eType;

    /**
     * 密级
     */
    @Excel(name = "密级")
    private Long eCon;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eStartdate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date eEnddate;

    /**
     * 合同金额
     */
    @Excel(name = "合同金额")
    private BigDecimal eAmount;

    /**
     * 合同情况说明
     */
    @Excel(name = "合同情况说明")
    private String eDescription;

    /**
     * 意见
     */
    @Excel(name = "意见")
    private String eOpinion;

    /**
     * 合同影像
     */
    @Excel(name = "合同影像")
    private String eImage;

    /**
     * 合同文件
     */
    @Excel(name = "合同文件")
    private String eDocuments;

    /** 是否作废 */
    @Excel(name = "是否作废")
    private Long eCancel;

//    private BidTender bidTender;
//
//    public BidTender getBidTender() {
//        return bidTender;
//    }
//
//    public void setBidTender(BidTender bidTender) {
//        this.bidTender = bidTender;
//    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    private List<BsInventory> bsInventoryList;

    private List<BsPayment> bsPaymentList;

    private BsSign bsSign;

    private ComPubAttachments comPubAttachments;

    public ComPubAttachments getComPubAttachments() {
        return comPubAttachments;
    }

    public void setComPubAttachments(ComPubAttachments comPubAttachments) {
        this.comPubAttachments = comPubAttachments;
    }

    public List<BsInventory> getBsInventoryList() {
        return bsInventoryList;
    }

    public void setBsInventoryList(List<BsInventory> bsInventoryList) {
        this.bsInventoryList = bsInventoryList;
    }

    public List<BsPayment> getBsPaymentList() {
        return bsPaymentList;
    }

    public void setBsPaymentList(List<BsPayment> bsPaymentList) {
        this.bsPaymentList = bsPaymentList;
    }

    public BsSign getBsSign() {
        return bsSign;
    }

    public void setBsSign(BsSign bsSign) {
        this.bsSign = bsSign;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getEid() {
        return eid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getHid() {
        return hid;
    }

    public void seteHcode(String eHcode) {
        this.eHcode = eHcode;
    }

    public String geteHcode() {
        return eHcode;
    }

    public void seteHname(String eHname) {
        this.eHname = eHname;
    }

    public String geteHname() {
        return eHname;
    }

    public void seteStatus(Long eStatus) {
        this.eStatus = eStatus;
    }

    public Long geteStatus() {
        return eStatus;
    }

    public void seteDeliveryTime(Date eDeliveryTime) {
        this.eDeliveryTime = eDeliveryTime;
    }

    public Date geteDeliveryTime() {
        return eDeliveryTime;
    }

    public void setoHstatus(Long oHstatus) {
        this.oHstatus = oHstatus;
    }

    public Long getoHstatus() {
        return oHstatus;
    }

    public Long geteCancel() {
        return eCancel;
    }

    public void seteCancel(Long eCancel) {
        this.eCancel = eCancel;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }

    public String geteType() {
        return eType;
    }

    public void seteCon(Long eCon) {
        this.eCon = eCon;
    }

    public Long geteCon() {
        return eCon;
    }

    public void seteStartdate(Date eStartdate) {
        this.eStartdate = eStartdate;
    }

    public Date geteStartdate() {
        return eStartdate;
    }

    public void seteEnddate(Date eEnddate) {
        this.eEnddate = eEnddate;
    }

    public Date geteEnddate() {
        return eEnddate;
    }

    public void seteAmount(BigDecimal eAmount) {
        this.eAmount = eAmount;
    }

    public BigDecimal geteAmount() {
        return eAmount;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteOpinion(String eOpinion) {
        this.eOpinion = eOpinion;
    }

    public String geteOpinion() {
        return eOpinion;
    }

    public void seteImage(String eImage) {
        this.eImage = eImage;
    }

    public String geteImage() {
        return eImage;
    }

    public void seteDocuments(String eDocuments) {
        this.eDocuments = eDocuments;
    }

    public String geteDocuments() {
        return eDocuments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("eid", getEid())
                .append("hid", getHid())
                .append("sid", getSid())
                .append("gid", getGid())
                .append("eHcode", geteHcode())
                .append("eHname", geteHname())
                .append("eStatus", geteStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("eDeliveryTime", geteDeliveryTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("oHstatus", getoHstatus())
                .append("eType", geteType())
                .append("eCon", geteCon())
                .append("eStartdate", geteStartdate())
                .append("eEnddate", geteEnddate())
                .append("eAmount", geteAmount())
                .append("eDescription", geteDescription())
                .append("eOpinion", geteOpinion())
                .append("eImage", geteImage())
                .append("eDocuments", geteDocuments())
                .append("Cancel", geteCancel())
                .append("BsInventoryList", getBsInventoryList())
                .append("PaymentList", getBsPaymentList())
                .append("BsSign", getBsSign())
                .append("ComPubAttachments", getComPubAttachments())
                .toString();
    }
}
