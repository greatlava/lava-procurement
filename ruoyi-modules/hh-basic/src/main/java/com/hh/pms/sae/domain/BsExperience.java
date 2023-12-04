package com.hh.pms.sae.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 任职经历对象 bs_experience
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsExperience extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 任职经历ID
     */
    private Long qid;

    /**
     * 单位名称
     */
    @Excel(name = "单位名称")
    private String unit;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /**
     * 职务
     */
    @Excel(name = "职务")
    private String qOffice;

    /**
     * 专业
     */
    @Excel(name = "专业")
    private String specialized;

    private int jid;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Long getQid() {
        return qid;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setqOffice(String qOffice) {
        this.qOffice = qOffice;
    }

    public String getqOffice() {
        return qOffice;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    public String getSpecialized() {
        return specialized;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("qid", getQid())
                .append("unit", getUnit())
                .append("time", getTime())
                .append("qOffice", getqOffice())
                .append("specialized", getSpecialized())
                .toString();
    }
}
