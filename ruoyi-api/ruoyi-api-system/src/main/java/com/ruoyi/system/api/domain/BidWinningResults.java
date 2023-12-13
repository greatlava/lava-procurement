package com.ruoyi.system.api.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

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

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String gsName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long gsState;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date gsRelease;

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
    public void setGsName(String gsName) 
    {
        this.gsName = gsName;
    }

    public String getGsName() 
    {
        return gsName;
    }
    public void setGsState(Long gsState) 
    {
        this.gsState = gsState;
    }

    public Long getGsState() 
    {
        return gsState;
    }
    public void setGsRelease(Date gsRelease) 
    {
        this.gsRelease = gsRelease;
    }

    public Date getGsRelease() 
    {
        return gsRelease;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gsId", getGsId())
            .append("sid", getSid())
            .append("gsName", getGsName())
            .append("gsState", getGsState())
            .append("gsRelease", getGsRelease())
            .toString();
    }
}
