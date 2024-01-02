package com.hh.pms.sae.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 非招标项目对象 nobid_non_pro
 *
 * @author ruoyi
 * @date 2023-12-11
 */
public class NobidNonPro extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 非招标项目ID
     */
    private Long gid;

    /**
     * 合同ID
     */
    private Long eid;

    /**
     * 采购计划序号
     */
    @Excel(name = "采购计划序号")
    private Long xyId;

    /**
     * 非招标项目编号
     */
    @Excel(name = "非招标项目编号")
    private String gCode;

    /**
     * 非招标项目名称
     */
    @Excel(name = "非招标项目名称")
    private String gName;

    /**
     * 公开/邀请
     */
    @Excel(name = "公开/邀请")
    private Long gIsPublic;

    /**
     * 生成时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "生成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gSpawnTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "报价开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gTimeon;

    /**
     * 报价截至时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "报价截至时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gDeadline;

    /**
     * 单据状态
     */
    @Excel(name = "单据状态")
    private Long gStatus;

    /**
     * 报价数量
     */
    @Excel(name = "报价数量")
    private Long gCount;

    /**
     * 委托单位
     */
    @Excel(name = "委托单位")
    private String gUnit;

    /**
     * 报价轮次
     */
    @Excel(name = "报价轮次")
    private Long gRounds;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private Long gTendertype;

    public Date getgTimeon() {
        return gTimeon;
    }

    public void setgTimeon(Date gTimeon) {
        this.gTimeon = gTimeon;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getGid() {
        return gid;
    }

    public void setXyId(Long xyId) {
        this.xyId = xyId;
    }

    public Long getXyId() {
        return xyId;
    }

    public void setgCode(String gCode) {
        this.gCode = gCode;
    }

    public String getgCode() {
        return gCode;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgName() {
        return gName;
    }

    public void setgIsPublic(Long gIsPublic) {
        this.gIsPublic = gIsPublic;
    }

    public Long getgIsPublic() {
        return gIsPublic;
    }

    public void setgSpawnTime(Date gSpawnTime) {
        this.gSpawnTime = gSpawnTime;
    }

    public Date getgSpawnTime() {
        return gSpawnTime;
    }

    public void setgDeadline(Date gDeadline) {
        this.gDeadline = gDeadline;
    }

    public Date getgDeadline() {
        return gDeadline;
    }

    public void setgStatus(Long gStatus) {
        this.gStatus = gStatus;
    }

    public Long getgStatus() {
        return gStatus;
    }

    public void setgCount(Long gCount) {
        this.gCount = gCount;
    }

    public Long getgCount() {
        return gCount;
    }

    public void setgUnit(String gUnit) {
        this.gUnit = gUnit;
    }

    public String getgUnit() {
        return gUnit;
    }

    public void setgRounds(Long gRounds) {
        this.gRounds = gRounds;
    }

    public Long getgRounds() {
        return gRounds;
    }

    public void setgTendertype(Long gTendertype) {
        this.gTendertype = gTendertype;
    }

    public Long getgTendertype() {
        return gTendertype;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gid", getGid())
                .append("eid", getEid())
                .append("xyId", getXyId())
                .append("gCode", getgCode())
                .append("gName", getgName())
                .append("gIsPublic", getgIsPublic())
                .append("gSpawnTime", getgSpawnTime())
                .append("gDeadline", getgDeadline())
                .append("gStatus", getgStatus())
                .append("gCount", getgCount())
                .append("gUnit", getgUnit())
                .append("gRounds", getgRounds())
                .append("gTendertype", getgTendertype())
                .toString();
    }
}
