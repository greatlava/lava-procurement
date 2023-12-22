package com.hh.pms.cm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 招标项目对象 bid_tender
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidTender extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 招标项目ID
     */
    private Long sid;

    /**
     * 采购计划序号
     */
    @Excel(name = "采购计划序号")
    private Long xyId;

    /**
     * 招标项目编号
     */
    @Excel(name = "招标项目编号")
    private String sCode;

    /**
     * 招标项目名称
     */
    @Excel(name = "招标项目名称")
    private String sName;

    /**
     * 招标方式
     */
    @Excel(name = "招标方式")
    private Long sWay;

    /**
     * 项目负责人
     */
    @Excel(name = "项目负责人")
    private String sLeader;

    /**
     * 是否必招
     */
    @Excel(name = "是否必招")
    private Long sMust;

    /**
     * 资格审查方式
     */
    @Excel(name = "资格审查方式")
    private Long sSway;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private Long sType;

    /**
     * 项目预算
     */
    @Excel(name = "项目预算")
    private Long sBudget;

    /**
     * 招标单位
     */
    @Excel(name = "招标单位")
    private String sUnit;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String sPerson;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String sPhone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String sAddress;

    /**
     * 公示开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "公示开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sStartTime;

    /**
     * 公示结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "公示结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sEndTime;

    /**
     * 项目状态
     */
    @Excel(name = "项目状态")
    private Long sProjectState;

    /**
     * 报价截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sDeadline;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;


    /**
     * 查询日期范围 开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "范围开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rangeStartTime;

    /**
     * 查询日期范围 截至
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "范围截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rangeEndTime;

    /**
     * 合同ID
     */
    @Excel(name = "合同ID")
    private Long eid;
//
//    private List<BidNotice> bidNotices;
//
//    public void setBidNotices(List<BidNotice> bidNotices) {
//        this.bidNotices = bidNotices;
//    }
//
//    public List<BidNotice> getBidNotices() {
//        return bidNotices;
//    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getSid() {
        return sid;
    }

    public void setXyId(Long xyId) {
        this.xyId = xyId;
    }

    public Long getXyId() {
        return xyId;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsName() {
        return sName;
    }

    public void setsWay(Long sWay) {
        this.sWay = sWay;
    }

    public Long getsWay() {
        return sWay;
    }

    public void setsLeader(String sLeader) {
        this.sLeader = sLeader;
    }

    public String getsLeader() {
        return sLeader;
    }

    public void setsMust(Long sMust) {
        this.sMust = sMust;
    }

    public Long getsMust() {
        return sMust;
    }

    public void setsSway(Long sSway) {
        this.sSway = sSway;
    }

    public Long getsSway() {
        return sSway;
    }

    public void setsType(Long sType) {
        this.sType = sType;
    }

    public Long getsType() {
        return sType;
    }

    public void setsBudget(Long sBudget) {
        this.sBudget = sBudget;
    }

    public Long getsBudget() {
        return sBudget;
    }

    public void setsUnit(String sUnit) {
        this.sUnit = sUnit;
    }

    public String getsUnit() {
        return sUnit;
    }

    public void setsPerson(String sPerson) {
        this.sPerson = sPerson;
    }

    public String getsPerson() {
        return sPerson;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsStartTime(Date sStartTime) {
        this.sStartTime = sStartTime;
    }

    public Date getsStartTime() {
        return sStartTime;
    }

    public void setsEndTime(Date sEndTime) {
        this.sEndTime = sEndTime;
    }

    public Date getsEndTime() {
        return sEndTime;
    }

    public void setsProjectState(Long sProjectState) {
        this.sProjectState = sProjectState;
    }

    public Long getsProjectState() {
        return sProjectState;
    }

    public void setsDeadline(Date sDeadline) {
        this.sDeadline = sDeadline;
    }

    public Date getsDeadline() {
        return sDeadline;
    }

    public void setCreatetime(Date CreateTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setRangeStartTime(Date rangeStartTime) {
        this.rangeStartTime = rangeStartTime;
    }

    public Date getRangeStartTime() {
        return rangeStartTime;
    }

    public void setRangeEndTime(Date rangeEndTime) {
        this.rangeEndTime = rangeEndTime;
    }

    public Date getRangeEndTime() {
        return rangeEndTime;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getEid() {
        return eid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sid", getSid())
                .append("xyId", getXyId())
                .append("sCode", getsCode())
                .append("sName", getsName())
                .append("sLeader", getsLeader())
                .append("sWay", getsWay())
                .append("sMust", getsMust())
                .append("sSway", getsSway())
                .append("sType", getsType())
                .append("sBudget", getsBudget())
                .append("sUnit", getsUnit())
                .append("sPerson", getsPerson())
                .append("sPhone", getsPhone())
                .append("email", getEmail())
                .append("sAddress", getsAddress())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("sStartTime", getsStartTime())
                .append("sEndTime", getsEndTime())
                .append("sProjectState", getsProjectState())
                .append("sDeadline", getsDeadline())
                .append("eid", getEid())
                .toString();
    }
}
