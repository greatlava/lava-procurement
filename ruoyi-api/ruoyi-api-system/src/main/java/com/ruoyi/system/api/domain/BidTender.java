package com.ruoyi.system.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

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
    private Integer xyId;

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
    private Integer sWay;

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
    private Integer sSway;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private Integer sType;

    /**
     * 项目预算
     */
    @Excel(name = "项目预算")
    private Integer sBudget;

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
    private Integer sProjectState;

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
    private Integer eid;

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getSid() {
        return sid;
    }


    public Integer getXyId() {
        return xyId;
    }

    public void setXyId(Integer xyId) {
        this.xyId = xyId;
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

    public Integer getsWay() {
        return sWay;
    }

    public void setsWay(Integer sWay) {
        this.sWay = sWay;
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

    public Integer getsSway() {
        return sSway;
    }

    public void setsSway(Integer sSway) {
        this.sSway = sSway;
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

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }

    public Integer getsBudget() {
        return sBudget;
    }

    public void setsBudget(Integer sBudget) {
        this.sBudget = sBudget;
    }

    public Integer getsProjectState() {
        return sProjectState;
    }

    public void setsProjectState(Integer sProjectState) {
        this.sProjectState = sProjectState;
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
