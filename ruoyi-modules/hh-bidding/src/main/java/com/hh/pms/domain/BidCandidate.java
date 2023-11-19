package com.hh.pms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 中标候选人对象 bid_candidate
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BidCandidate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 候选人ID */
    private Long zid;

    /** 招标项目ID */
    @Excel(name = "招标项目ID")
    private Long sid;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String zBname;

    /** 最终报价 */
    @Excel(name = "最终报价")
    private Long zFinal;

    /** 最终得分 */
    @Excel(name = "最终得分")
    private Long zFraction;

    /** 是否推荐 */
    @Excel(name = "是否推荐")
    private Long zRecommend;

    /** 排名 */
    @Excel(name = "排名")
    private Long zRanking;

    /** 专家签到表 */
    @Excel(name = "专家签到表")
    private String zSign;

    /** 审查表 */
    @Excel(name = "审查表")
    private String zCensor;

    /** 评审表 */
    @Excel(name = "评审表")
    private String zReview;

    /** 最终汇总表 */
    @Excel(name = "最终汇总表")
    private String zSummary;

    /** 是否中标 */
    @Excel(name = "是否中标")
    private Long zBidder;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zSendTime;

    public void setZid(Long zid) 
    {
        this.zid = zid;
    }

    public Long getZid() 
    {
        return zid;
    }
    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setzBname(String zBname) 
    {
        this.zBname = zBname;
    }

    public String getzBname() 
    {
        return zBname;
    }
    public void setzFinal(Long zFinal) 
    {
        this.zFinal = zFinal;
    }

    public Long getzFinal() 
    {
        return zFinal;
    }
    public void setzFraction(Long zFraction) 
    {
        this.zFraction = zFraction;
    }

    public Long getzFraction() 
    {
        return zFraction;
    }
    public void setzRecommend(Long zRecommend) 
    {
        this.zRecommend = zRecommend;
    }

    public Long getzRecommend() 
    {
        return zRecommend;
    }
    public void setzRanking(Long zRanking) 
    {
        this.zRanking = zRanking;
    }

    public Long getzRanking() 
    {
        return zRanking;
    }
    public void setzSign(String zSign) 
    {
        this.zSign = zSign;
    }

    public String getzSign() 
    {
        return zSign;
    }
    public void setzCensor(String zCensor) 
    {
        this.zCensor = zCensor;
    }

    public String getzCensor() 
    {
        return zCensor;
    }
    public void setzReview(String zReview) 
    {
        this.zReview = zReview;
    }

    public String getzReview() 
    {
        return zReview;
    }
    public void setzSummary(String zSummary) 
    {
        this.zSummary = zSummary;
    }

    public String getzSummary() 
    {
        return zSummary;
    }
    public void setzBidder(Long zBidder) 
    {
        this.zBidder = zBidder;
    }

    public Long getzBidder() 
    {
        return zBidder;
    }
    public void setzSendTime(Date zSendTime) 
    {
        this.zSendTime = zSendTime;
    }

    public Date getzSendTime() 
    {
        return zSendTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("zid", getZid())
            .append("sid", getSid())
            .append("zBname", getzBname())
            .append("zFinal", getzFinal())
            .append("zFraction", getzFraction())
            .append("zRecommend", getzRecommend())
            .append("zRanking", getzRanking())
            .append("zSign", getzSign())
            .append("zCensor", getzCensor())
            .append("zReview", getzReview())
            .append("zSummary", getzSummary())
            .append("zBidder", getzBidder())
            .append("zSendTime", getzSendTime())
            .toString();
    }
}
