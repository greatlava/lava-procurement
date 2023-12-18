package com.hh.pms.domain;

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
     * 采购计划序号
     */
    @Excel(name = "采购计划序号")
    private Integer xyId;

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
    private Integer gIsPublic;

    /**
     * 生成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gSpawnTime;

    /**
     * 报价截至时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gDeadline;

    /**
     * 单据状态
     */
    @Excel(name = "单据状态")
    private Integer gStatus;

    /**
     * 报价数量
     */
    @Excel(name = "报价数量")
    private Integer gCount;

    /**
     * 委托单位
     */
    @Excel(name = "委托单位")
    private String gUnit;

    /**
     * 报价轮次
     */
    @Excel(name = "报价轮次")
    private Integer gRounds;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private Integer tendertype;

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getGid() {
        return gid;
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



    public void setgUnit(String gUnit) {
        this.gUnit = gUnit;
    }

    public String getgUnit() {
        return gUnit;
    }

    public Integer getXyId() {
        return xyId;
    }

    public void setXyId(Integer xyId) {
        this.xyId = xyId;
    }

    public Integer getgIsPublic() {
        return gIsPublic;
    }

    public void setgIsPublic(Integer gIsPublic) {
        this.gIsPublic = gIsPublic;
    }

    public Integer getgStatus() {
        return gStatus;
    }

    public void setgStatus(Integer gStatus) {
        this.gStatus = gStatus;
    }

    public Integer getgCount() {
        return gCount;
    }

    public void setgCount(Integer gCount) {
        this.gCount = gCount;
    }

    public Integer getgRounds() {
        return gRounds;
    }

    public void setgRounds(Integer gRounds) {
        this.gRounds = gRounds;
    }

    public Integer getTendertype() {
        return tendertype;
    }

    public void setTendertype(Integer tendertype) {
        this.tendertype = tendertype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gid", getGid())
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
                .append("tendertype", getTendertype())
                .toString();
    }
}
