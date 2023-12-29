package com.hh.pms.sae.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 投递标书对象 bid_submission
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidSubmission extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long tdId;

    /**
     * 招标项目ID
     */
    @Excel(name = "招标项目ID")
    private Long sid;

    /**
     * 供应商ID
     */
    @Excel(name = "供应商ID")
    private Long hid;


    /**
     * 投标状态
     */
    @Excel(name = "投标状态")
    private Long tdStatus;

    /**
     * 成功递交时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "成功递交时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date tdSuccessTime;

    /**
     * 文件撤回时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "文件撤回时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date tdFailTime;

    @Excel(name = "上传标书")
    private String fjFiles;

    private String sCode;

    private String sName;

    private Long sWay;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date sEndTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "投标截止日期", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date uEndTime;


    public Date getuEndTime() {
        return uEndTime;
    }

    public void setuEndTime(Date uEndTime) {
        this.uEndTime = uEndTime;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Long getsWay() {
        return sWay;
    }

    public void setsWay(Long sWay) {
        this.sWay = sWay;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getsEndTime() {
        return sEndTime;
    }

    public void setsEndTime(Date sEndTime) {
        this.sEndTime = sEndTime;
    }

    private List<BsSupplier> bsSuppliers;

    public void setTdId(Long tdId) {
        this.tdId = tdId;
    }

    public Long getTdId() {
        return tdId;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getSid() {
        return sid;
    }

    public void setTdStatus(Long tdStatus) {
        this.tdStatus = tdStatus;
    }

    public Long getTdStatus() {
        return tdStatus;
    }

    public void setTdSuccessTime(Date tdSuccessTime) {
        this.tdSuccessTime = tdSuccessTime;
    }

    public Date getTdSuccessTime() {
        return tdSuccessTime;
    }

    public void setTdFailTime(Date tdFailTime) {
        this.tdFailTime = tdFailTime;
    }

    public Date getTdFailTime() {
        return tdFailTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tdId", getTdId())
                .append("sid", getSid())
                .append("hid", getHid())
                .append("tdStatus", getTdStatus())
                .append("tdSuccessTime", getTdSuccessTime())
                .append("tdFailTime", getTdFailTime())
                .append("fjFiles", getFjFiles())
                .toString();
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public List<BsSupplier> getBsSuppliers() {
        return bsSuppliers;
    }

    public void setBsSuppliers(List<BsSupplier> bsSuppliers) {
        this.bsSuppliers = bsSuppliers;
    }

    public String getFjFiles() {
        return fjFiles;
    }

    public void setFjFiles(String fjFiles) {
        this.fjFiles = fjFiles;
    }
}
