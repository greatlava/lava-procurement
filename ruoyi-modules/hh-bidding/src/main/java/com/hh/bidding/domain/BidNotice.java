package com.hh.bidding.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 招标公告对象 bid_notice
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 招标公告ID */
    private Long uid;

    /** 招标项目ID */
    @Excel(name = "招标项目ID")
    private Long sid;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String uTitle;

    /** 关联项目 */
    @Excel(name = "关联项目")
    private String uProject;

    /** 项目资金 */
    @Excel(name = "项目资金")
    private Long uMoney;

    /** 标注获取时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "标注获取时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uGetTime;

    /** 接受答疑时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "接受答疑时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uAcceptTime;

    /** 投标截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "投标截止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uEndTime;

    /** 开标时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开标时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uKaiTime;

    /** 附件 */
    @Excel(name = "附件")
    private String fjAnnex;

    /** 招标公告审批状态 */
    @Excel(name = "招标公告审批状态")
    private Long fjStatus;

    /** 内容 */
    @Excel(name = "内容")
    private String fjRemark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "公示时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uUpdateTime;

    private BidTender bidTender;//招标项目

    private List<Result> results;


    private Long hid;
    public void setResults(List<Result> results)
    {
        this.results = results;
    }

    public List<Result> getResults()
    {
        return results;
    }
    public void setBidTender(BidTender bidTender)
    {
        this.bidTender = bidTender;
    }

    public BidTender getBidTender()
    {
        return bidTender;
    }

    public void setuUpdateTime(Date uUpdateTime)
    {
        this.uUpdateTime = uUpdateTime;
    }

    public Date getuUpdateTime()
    {
        return uUpdateTime;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setuTitle(String uTitle) 
    {
        this.uTitle = uTitle;
    }

    public String getuTitle() 
    {
        return uTitle;
    }
    public void setuProject(String uProject)
    {
        this.uProject = uProject;
    }

    public String getuProject()
    {
        return uProject;
    }
    public void setuMoney(Long uMoney) 
    {
        this.uMoney = uMoney;
    }

    public Long getuMoney() 
    {
        return uMoney;
    }
    public void setuGetTime(Date uGetTime) 
    {
        this.uGetTime = uGetTime;
    }

    public Date getuGetTime() 
    {
        return uGetTime;
    }
    public void setuAcceptTime(Date uAcceptTime) 
    {
        this.uAcceptTime = uAcceptTime;
    }

    public Date getuAcceptTime() 
    {
        return uAcceptTime;
    }
    public void setuEndTime(Date uEndTime) 
    {
        this.uEndTime = uEndTime;
    }

    public Date getuEndTime() 
    {
        return uEndTime;
    }
    public void setuKaiTime(Date uKaiTime) 
    {
        this.uKaiTime = uKaiTime;
    }

    public Date getuKaiTime() 
    {
        return uKaiTime;
    }
    public void setFjAnnex(String fjAnnex)
    {
        this.fjAnnex = fjAnnex;
    }

    public String getFjAnnex()
    {
        return fjAnnex;
    }
    public void setFjStatus(Long fjStatus) 
    {
        this.fjStatus = fjStatus;
    }

    public Long getFjStatus() 
    {
        return fjStatus;
    }
    public void setFjRemark(String fjRemark) 
    {
        this.fjRemark = fjRemark;
    }

    public String getFjRemark() 
    {
        return fjRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("sid", getSid())
            .append("uTitle", getuTitle())
            .append("uProject", getuProject())
            .append("uMoney", getuMoney())
            .append("uGetTime", getuGetTime())
            .append("uAcceptTime", getuAcceptTime())
            .append("uEndTime", getuEndTime())
            .append("uKaiTime", getuKaiTime())
            .append("fjAnnex", getFjAnnex())
            .append("fjStatus", getFjStatus())
            .append("fjRemark", getFjRemark())
            .toString();
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }
}
