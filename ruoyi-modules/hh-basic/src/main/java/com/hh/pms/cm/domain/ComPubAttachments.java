package com.hh.pms.cm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公共附件对象 com_pub_attachments
 *
 * @author ruoyi
 * @date 2023-12-13
 */
public class ComPubAttachments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Integer anId;

    /** 采购计划id */
    @Excel(name = "采购计划id")
    private Integer aid;

    /** 框架计划id */
    @Excel(name = "框架计划id")
    private Integer jhid;

    /** 合同ID */
    @Excel(name = "合同ID")
    private Integer eid;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String anName;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private Integer anType;

    /** 业务单据号 */
    @Excel(name = "业务单据号")
    private String anDan;

    /** URL */
    @Excel(name = "URL")
    private String anUrl;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal anSize;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date anUploadTime;

    public void setAnId(Integer anId)
    {
        this.anId = anId;
    }

    public Integer getAnId()
    {
        return anId;
    }
    public void setAid(Integer aid)
    {
        this.aid = aid;
    }

    public Integer getAid()
    {
        return aid;
    }
    public void setJhid(Integer jhid)
    {
        this.jhid = jhid;
    }

    public Integer getJhid()
    {
        return jhid;
    }
    public void setEid(Integer eid)
    {
        this.eid = eid;
    }

    public Integer getEid()
    {
        return eid;
    }
    public void setAnName(String anName)
    {
        this.anName = anName;
    }

    public String getAnName()
    {
        return anName;
    }
    public void setAnType(Integer anType)
    {
        this.anType = anType;
    }

    public Integer getAnType()
    {
        return anType;
    }
    public void setAnDan(String anDan)
    {
        this.anDan = anDan;
    }

    public String getAnDan()
    {
        return anDan;
    }
    public void setAnUrl(String anUrl)
    {
        this.anUrl = anUrl;
    }

    public String getAnUrl()
    {
        return anUrl;
    }
    public void setAnSize(BigDecimal anSize)
    {
        this.anSize = anSize;
    }

    public BigDecimal getAnSize()
    {
        return anSize;
    }
    public void setAnUploadTime(Date anUploadTime)
    {
        this.anUploadTime = anUploadTime;
    }

    public Date getAnUploadTime()
    {
        return anUploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("anId", getAnId())
                .append("aid", getAid())
                .append("jhid", getJhid())
                .append("eid", getEid())
                .append("anName", getAnName())
                .append("anType", getAnType())
                .append("anDan", getAnDan())
                .append("anUrl", getAnUrl())
                .append("anSize", getAnSize())
                .append("anUploadTime", getAnUploadTime())
                .toString();
    }
}
