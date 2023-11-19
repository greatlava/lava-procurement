package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 培训对象 bs_training
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsTraining extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 培训ID */
    private Long lid;

    /** 专家ID */
    @Excel(name = "专家ID")
    private Long jid;

    /** 培训机构 */
    @Excel(name = "培训机构")
    private String lTraining;

    /** 培训专业 */
    @Excel(name = "培训专业")
    private String lSpecialized;

    /** 培训开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "培训开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lStartDate;

    /** 培训结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "培训结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lEndDate;

    public void setLid(Long lid) 
    {
        this.lid = lid;
    }

    public Long getLid() 
    {
        return lid;
    }
    public void setJid(Long jid) 
    {
        this.jid = jid;
    }

    public Long getJid() 
    {
        return jid;
    }
    public void setlTraining(String lTraining) 
    {
        this.lTraining = lTraining;
    }

    public String getlTraining() 
    {
        return lTraining;
    }
    public void setlSpecialized(String lSpecialized) 
    {
        this.lSpecialized = lSpecialized;
    }

    public String getlSpecialized() 
    {
        return lSpecialized;
    }
    public void setlStartDate(Date lStartDate) 
    {
        this.lStartDate = lStartDate;
    }

    public Date getlStartDate() 
    {
        return lStartDate;
    }
    public void setlEndDate(Date lEndDate) 
    {
        this.lEndDate = lEndDate;
    }

    public Date getlEndDate() 
    {
        return lEndDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lid", getLid())
            .append("jid", getJid())
            .append("lTraining", getlTraining())
            .append("lSpecialized", getlSpecialized())
            .append("lStartDate", getlStartDate())
            .append("lEndDate", getlEndDate())
            .toString();
    }
}
