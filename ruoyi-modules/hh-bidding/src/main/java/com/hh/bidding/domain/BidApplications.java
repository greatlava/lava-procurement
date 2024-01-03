package com.hh.bidding.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 抽取申请对象 bid_applications
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public class BidApplications extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 抽取申请ID */
    private Long xid;

    /** 评标ID */
    @Excel(name = "评标ID")
    private Long pbId;

    /** 申请编号 */
    @Excel(name = "申请编号")
    private String xCode;

    /** 招标项目名称 */
    @Excel(name = "招标项目名称")
    private String xName;

    /** 专家确定方式 */
    @Excel(name = "专家确定方式")
    private Long xWay;

    /** 资格审查类型 */
    @Excel(name = "资格审查类型")
    private Long xType;

    /** 评标开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "评标开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date xStartTime;

    /** 评标结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "评标结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date xEndTime;

    /** 专家人数 */
    @Excel(name = "专家人数")
    private Long xCount;

    /** 采购方代表人数 */
    @Excel(name = "采购方代表人数")
    private Long xDaiCount;

    /** 评标地点 */
    @Excel(name = "评标地点")
    private String xArea;
    @Excel(name = "招标项目ID")
    private Long sid;

    public void setXid(Long xid)
    {
        this.xid = xid;
    }

    public Long getXid()
    {
        return xid;
    }
    public void setPbId(Long pbId)
    {
        this.pbId = pbId;
    }

    public Long getPbId()
    {
        return pbId;
    }
    public void setxCode(String xCode)
    {
        this.xCode = xCode;
    }

    public String getxCode()
    {
        return xCode;
    }
    public void setxName(String xName)
    {
        this.xName = xName;
    }

    public String getxName()
    {
        return xName;
    }
    public void setxWay(Long xWay)
    {
        this.xWay = xWay;
    }

    public Long getxWay()
    {
        return xWay;
    }
    public void setxType(Long xType)
    {
        this.xType = xType;
    }

    public Long getxType()
    {
        return xType;
    }
    public void setxStartTime(Date xStartTime)
    {
        this.xStartTime = xStartTime;
    }

    public Date getxStartTime()
    {
        return xStartTime;
    }
    public void setxEndTime(Date xEndTime)
    {
        this.xEndTime = xEndTime;
    }

    public Date getxEndTime()
    {
        return xEndTime;
    }
    public void setxCount(Long xCount)
    {
        this.xCount = xCount;
    }

    public Long getxCount()
    {
        return xCount;
    }
    public void setxDaiCount(Long xDaiCount)
    {
        this.xDaiCount = xDaiCount;
    }

    public Long getxDaiCount()
    {
        return xDaiCount;
    }
    public void setxArea(String xArea)
    {
        this.xArea = xArea;
    }

    public String getxArea()
    {
        return xArea;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("xid", getXid())
                .append("pbId", getPbId())
                .append("xCode", getxCode())
                .append("xName", getxName())
                .append("xWay", getxWay())
                .append("xType", getxType())
                .append("xStartTime", getxStartTime())
                .append("xEndTime", getxEndTime())
                .append("xCount", getxCount())
                .append("xDaiCount", getxDaiCount())
                .append("xArea", getxArea())
                .append("sid", getSid())
                .toString();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
}
