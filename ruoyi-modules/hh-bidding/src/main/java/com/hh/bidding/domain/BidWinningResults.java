package com.hh.bidding.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 中标结果公示对象 bid_winning_results
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidWinningResults extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公示ID */
    private Long gsId;
    /** 招标项目ID */
    @Excel(name = "招标项目ID")
    private Long sid;

    @Excel(name = "供应商ID")
    private Long hid;

    @Excel(name = "供应商名称")
    private String hName;

    @Excel(name = "公示状态")
    private Long gsState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "公示发布时间")
    private Date gsResDate;

    @Excel(name = "内容")
    private String resultDesc;

    @Excel(name = "标题")
    private String resultTitle;


    private List<BsSupplier> bsSupplier;

    private BidCandidate bidCandidate;

    public void setGsId(Long gsId) 
    {
        this.gsId = gsId;
    }

    public Long getGsId() 
    {
        return gsId;
    }
    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gsId", getGsId())
            .append("sid", getSid())
            .append("hid", getHid())
            .append("hName", gethName())
            .append("gsState", getGsState())
            .append("gsResDate", getGsResDate())
                .append("resultDesc", getResultDesc())
                .append("resultTitle", getResultTitle())
            .toString();
    }

    public Date getGsResDate() {
        return gsResDate;
    }

    public void setGsResDate(Date gsResDate) {
        this.gsResDate = gsResDate;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getGsState() {
        return gsState;
    }

    public void setGsState(Long gsState) {
        this.gsState = gsState;
    }

    public List<BsSupplier> getBsSupplier() {
        return bsSupplier;
    }

    public void setBsSupplier(List<BsSupplier> bsSupplier) {
        this.bsSupplier = bsSupplier;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultTitle() {
        return resultTitle;
    }

    public void setResultTitle(String resultTitle) {
        this.resultTitle = resultTitle;
    }

    public BidCandidate getBidCandidate() {
        return bidCandidate;
    }

    public void setBidCandidate(BidCandidate bidCandidate) {
        this.bidCandidate = bidCandidate;
    }
}
