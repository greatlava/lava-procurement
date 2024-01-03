package com.hh.bidding.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 招标文件对象 bid_documents
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidDocuments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long wid;

    /** 招标项目ID */
    @Excel(name = "招标项目ID")
    private Long sid;

    /** 文件标题 */
    @Excel(name = "文件标题")
    private String wTitle;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long wSize;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date wUploadTime;

    /** 文件大小 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件大小 */
    @Excel(name = "上传路径")
    private String url;

    private BidNotice bidNotice;



    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }



    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setBidNotice(BidNotice bidNotice)
    {
        this.bidNotice = bidNotice;
    }

    public BidNotice getBidNotice()
    {
        return bidNotice;
    }

    public void setWid(Long wid) 
    {
        this.wid = wid;
    }

    public Long getWid() 
    {
        return wid;
    }
    public void setSid(Long sid)
    {
        this.sid = sid;
    }

    public Long getSid()
    {
        return sid;
    }
    public void setwTitle(String wTitle) 
    {
        this.wTitle = wTitle;
    }

    public String getwTitle() 
    {
        return wTitle;
    }
    public void setwSize(Long wSize) 
    {
        this.wSize = wSize;
    }

    public Long getwSize() 
    {
        return wSize;
    }
    public void setwUploadTime(Date wUploadTime) 
    {
        this.wUploadTime = wUploadTime;
    }

    public Date getwUploadTime() 
    {
        return wUploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wid", getWid())
            .append("sid", getSid())
            .append("wTitle", getwTitle())
            .append("wSize", getwSize())
            .append("wUploadTime", getwUploadTime())
            .append("fileName", getFileName())
            .append("url", getUrl())
            .toString();
    }
}
