package com.hh.bidding.domain;

import java.util.Date;
import java.util.List;

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

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;


    /** 投标状态 */
    @Excel(name = "投标状态")
    private Long tdStatus;

    /** 成功递交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "成功递交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date tdSuccessTime;

    /** 文件撤回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "文件撤回时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date tdFailTime;

    @Excel(name = "上传标书")
    private String fjFiles;

    private List<BsSupplier> bsSuppliers;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
