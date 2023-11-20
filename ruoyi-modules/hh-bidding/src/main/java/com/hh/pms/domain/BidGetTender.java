package com.hh.pms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 获取标书对象 bid_get_tender
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidGetTender extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 获取标书ID */
    private Long yid;

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

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 下载时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下载时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yDownloadTime;

    /** 投标状态 */
    @Excel(name = "投标状态")
    private Long tdStatus;

    public void setYid(Long yid) 
    {
        this.yid = yid;
    }

    public Long getYid() 
    {
        return yid;
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
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setyDownloadTime(Date yDownloadTime) 
    {
        this.yDownloadTime = yDownloadTime;
    }

    public Date getyDownloadTime() 
    {
        return yDownloadTime;
    }
    public void setTdStatus(Long tdStatus) 
    {
        this.tdStatus = tdStatus;
    }

    public Long getTdStatus() 
    {
        return tdStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("yid", getYid())
            .append("sid", getSid())
            .append("tdName", getTdName())
            .append("tdPerson", getTdPerson())
            .append("tdPhone", getTdPhone())
            .append("email", getEmail())
            .append("yDownloadTime", getyDownloadTime())
            .append("tdStatus", getTdStatus())
            .toString();
    }
}
