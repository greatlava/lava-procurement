package com.hh.pms.sae.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.pms.cm.domain.BsContract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 供应商风险监控对象 bs_risk_monitor
 *
 * @author ruoyi
 * @date 2023-11-28
 */
public class BsRiskMonitor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 风险监控ID
     */
    private Long rmId;

    /**
     * 合同ID
     */
    @Excel(name = "合同ID")
    private Long eid;

    /**
     * 风险说明
     */
    @Excel(name = "风险说明")
    private String rmIllustrate;

    /**
     * 发生日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "发生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rmHappenDate;

    /**
     * 风险项
     */
    @Excel(name = "风险项")
    private Long rmItem;

    /**
     * 风险处理状态
     */
    @Excel(name = "风险处理状态")
    private Long rmStatus;

    /**
     * 处理日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "处理日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rmHandingDate;

    /**
     * 处理人
     */
    @Excel(name = "处理人")
    private String rmHandingPerson;

    /**
     * 处理结果
     */
    @Excel(name = "处理结果")
    private String rmHandingResult;

    //合同
    private BsContract bsContract;

    private BsSupplier bsSupplier;

    private String eHname;
    private String hName;

    public String geteHname() {
        return eHname;
    }

    public void seteHname(String eHname) {
        this.eHname = eHname;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public BsSupplier getBsSupplier() {
        return bsSupplier;
    }

    public void setBsSupplier(BsSupplier bsSupplier) {
        this.bsSupplier = bsSupplier;
    }

    public BsContract getBsContract() {
        return bsContract;
    }

    public void setBsContract(BsContract bsContract) {
        this.bsContract = bsContract;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getEid() {
        return eid;
    }

    public void setRmIllustrate(String rmIllustrate) {
        this.rmIllustrate = rmIllustrate;
    }

    public String getRmIllustrate() {
        return rmIllustrate;
    }

    public void setRmHappenDate(Date rmHappenDate) {
        this.rmHappenDate = rmHappenDate;
    }

    public Date getRmHappenDate() {
        return rmHappenDate;
    }

    public void setRmItem(Long rmItem) {
        this.rmItem = rmItem;
    }

    public Long getRmItem() {
        return rmItem;
    }

    public void setRmStatus(Long rmStatus) {
        this.rmStatus = rmStatus;
    }

    public Long getRmStatus() {
        return rmStatus;
    }

    public void setRmHandingDate(Date rmHandingDate) {
        this.rmHandingDate = rmHandingDate;
    }

    public Date getRmHandingDate() {
        return rmHandingDate;
    }

    public void setRmHandingPerson(String rmHandingPerson) {
        this.rmHandingPerson = rmHandingPerson;
    }

    public String getRmHandingPerson() {
        return rmHandingPerson;
    }

    public void setRmHandingResult(String rmHandingResult) {
        this.rmHandingResult = rmHandingResult;
    }

    public String getRmHandingResult() {
        return rmHandingResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rmId", getRmId())
                .append("eid", getEid())
                .append("rmIllustrate", getRmIllustrate())
                .append("rmHappenDate", getRmHappenDate())
                .append("rmItem", getRmItem())
                .append("rmStatus", getRmStatus())
                .append("rmHandingDate", getRmHandingDate())
                .append("rmHandingPerson", getRmHandingPerson())
                .append("rmHandingResult", getRmHandingResult())
                .toString();
    }
}
