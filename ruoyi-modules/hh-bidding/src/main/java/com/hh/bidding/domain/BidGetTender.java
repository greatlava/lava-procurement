package com.hh.bidding.domain;

import java.util.Date;
import java.util.List;

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
public class BidGetTender extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 获取标书ID
     */
    private Long yid;

    /**
     * 招标项目ID
     */
    @Excel(name = "招标项目ID")
    private Long sid;

    /**
     * 供应商ID
     */
    @Excel(name = "供应商ID")
    private Long hid;

    /**
     * 下载时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下载时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date yDownloadTime;


    private List<BsSupplier> bsSuppliers;
    public void setYid(Long yid) {
        this.yid = yid;
    }

    public Long getYid() {
        return yid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getSid() {
        return sid;
    }

    public void setyDownloadTime(Date yDownloadTime) {
        this.yDownloadTime = yDownloadTime;
    }

    public Date getyDownloadTime() {
        return yDownloadTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("yid", getYid())
                .append("sid", getSid())
                .append("yDownloadTime", getyDownloadTime())
                .append("hid", getHid())
                .toString();
    }

    public List<BsSupplier> getBsSuppliers() {
        return bsSuppliers;
    }

    public void setBsSuppliers(List<BsSupplier> bsSuppliers) {
        this.bsSuppliers = bsSuppliers;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }
}
