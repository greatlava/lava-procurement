package com.hh.pms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 投递标书对象 bid_submission
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidSubmission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long tdId;

    /** 招标项目ID */
    @Excel(name = "招标项目ID")
    private Long sid;

    /** 投标人名称 */
    @Excel(name = "投标人名称")
    private String tdName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String tdPerson;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String tdPhone;

    /** 投标状态 */
    @Excel(name = "投标状态")
    private Long tdStatus;

    /** 成功递交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成功递交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tdSuccessTime;

    /** 文件撤回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "文件撤回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tdFailTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String tdIp;

    public void setTdId(Long tdId) 
    {
        this.tdId = tdId;
    }

    public Long getTdId() 
    {
        return tdId;
    }
    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setTdName(String tdName) 
    {
        this.tdName = tdName;
    }

    public String getTdName() 
    {
        return tdName;
    }
    public void setTdPerson(String tdPerson) 
    {
        this.tdPerson = tdPerson;
    }

    public String getTdPerson() 
    {
        return tdPerson;
    }
    public void setTdPhone(String tdPhone) 
    {
        this.tdPhone = tdPhone;
    }

    public String getTdPhone() 
    {
        return tdPhone;
    }
    public void setTdStatus(Long tdStatus) 
    {
        this.tdStatus = tdStatus;
    }

    public Long getTdStatus() 
    {
        return tdStatus;
    }
    public void setTdSuccessTime(Date tdSuccessTime) 
    {
        this.tdSuccessTime = tdSuccessTime;
    }

    public Date getTdSuccessTime() 
    {
        return tdSuccessTime;
    }
    public void setTdFailTime(Date tdFailTime) 
    {
        this.tdFailTime = tdFailTime;
    }

    public Date getTdFailTime() 
    {
        return tdFailTime;
    }
    public void setTdIp(String tdIp) 
    {
        this.tdIp = tdIp;
    }

    public String getTdIp() 
    {
        return tdIp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tdId", getTdId())
            .append("sid", getSid())
            .append("tdName", getTdName())
            .append("tdPerson", getTdPerson())
            .append("tdPhone", getTdPhone())
            .append("tdStatus", getTdStatus())
            .append("tdSuccessTime", getTdSuccessTime())
            .append("tdFailTime", getTdFailTime())
            .append("tdIp", getTdIp())
            .toString();
    }
}
