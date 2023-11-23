package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 职称对象 bs_job
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsJob extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职称ID */
    private Long mid;

    /** 专家ID */
    @Excel(name = "专家ID")
    private Long jid;

    /** 证书名称 */
    @Excel(name = "证书名称")
    private String mName;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String mCode;

    /** 职称 */
    @Excel(name = "职称")
    private String mJob;

    /** 颁发机构 */
    @Excel(name = "颁发机构")
    private String mInstitution;

    /** 获得时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "获得时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date mGetDate;

    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setJid(Long jid) 
    {
        this.jid = jid;
    }

    public Long getJid() 
    {
        return jid;
    }
    public void setmName(String mName) 
    {
        this.mName = mName;
    }

    public String getmName() 
    {
        return mName;
    }
    public void setmCode(String mCode) 
    {
        this.mCode = mCode;
    }

    public String getmCode() 
    {
        return mCode;
    }
    public void setmJob(String mJob) 
    {
        this.mJob = mJob;
    }

    public String getmJob() 
    {
        return mJob;
    }
    public void setmInstitution(String mInstitution) 
    {
        this.mInstitution = mInstitution;
    }

    public String getmInstitution() 
    {
        return mInstitution;
    }
    public void setmGetDate(Date mGetDate) 
    {
        this.mGetDate = mGetDate;
    }

    public Date getmGetDate() 
    {
        return mGetDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mid", getMid())
            .append("jid", getJid())
            .append("mName", getmName())
            .append("mCode", getmCode())
            .append("mJob", getmJob())
            .append("mInstitution", getmInstitution())
            .append("mGetDate", getmGetDate())
            .toString();
    }
}
