package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教育对象 bs_educate
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsEducate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教育ID */
    private Long rid;

    /** 专家ID */
    @Excel(name = "专家ID")
    private Long jid;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String rSchool;

    /** 专业 */
    @Excel(name = "专业")
    private String specialized;

    /** 学历 */
    @Excel(name = "学历")
    private String rDegree;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rEnrollmentTime;

    /** 毕业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "毕业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rGraduationTime;

    public void setRid(Long rid) 
    {
        this.rid = rid;
    }

    public Long getRid() 
    {
        return rid;
    }
    public void setJid(Long jid) 
    {
        this.jid = jid;
    }

    public Long getJid() 
    {
        return jid;
    }
    public void setrSchool(String rSchool) 
    {
        this.rSchool = rSchool;
    }

    public String getrSchool() 
    {
        return rSchool;
    }
    public void setSpecialized(String specialized) 
    {
        this.specialized = specialized;
    }

    public String getSpecialized() 
    {
        return specialized;
    }
    public void setrDegree(String rDegree) 
    {
        this.rDegree = rDegree;
    }

    public String getrDegree() 
    {
        return rDegree;
    }
    public void setrEnrollmentTime(Date rEnrollmentTime) 
    {
        this.rEnrollmentTime = rEnrollmentTime;
    }

    public Date getrEnrollmentTime() 
    {
        return rEnrollmentTime;
    }
    public void setrGraduationTime(Date rGraduationTime) 
    {
        this.rGraduationTime = rGraduationTime;
    }

    public Date getrGraduationTime() 
    {
        return rGraduationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("jid", getJid())
            .append("rSchool", getrSchool())
            .append("specialized", getSpecialized())
            .append("rDegree", getrDegree())
            .append("rEnrollmentTime", getrEnrollmentTime())
            .append("rGraduationTime", getrGraduationTime())
            .toString();
    }
}
