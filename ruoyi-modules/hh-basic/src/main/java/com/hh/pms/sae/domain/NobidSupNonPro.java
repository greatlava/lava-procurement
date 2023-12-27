package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 供应商非招标项目对象 nobid_sup_non_pro
 *
 * @author ruoyi
 * @date 2023-12-20
 */
public class NobidSupNonPro extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购项目编号 */
    private String gfId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 采购项目名称 */
    @Excel(name = "采购项目名称")
    private String gfName;

    /** 采购单位 */
    @Excel(name = "采购单位")
    private String gfUnit;

    /** 采购方式 */
    @Excel(name = "采购方式")
    private Long gfWay;

    /** 是否流标 */
    @Excel(name = "是否流标")
    private Long gfLn;

    /** 终止采购 */
    @Excel(name = "终止采购")
    private Long gfTp;

    /** 过期未接受 */
    @Excel(name = "过期未接受")
    private Long gfEba;

    /** 是否中标 */
    @Excel(name = "是否中标")
    private Long gfSb;

    /** 报价截至时间 */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "报价截至时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gfQtime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long gfQstate;

    public void setGfId(String gfId)
    {
        this.gfId = gfId;
    }

    public String getGfId()
    {
        return gfId;
    }
    public void setHid(Long hid)
    {
        this.hid = hid;
    }

    public Long getHid()
    {
        return hid;
    }
    public void setGfName(String gfName)
    {
        this.gfName = gfName;
    }

    public String getGfName()
    {
        return gfName;
    }
    public void setGfUnit(String gfUnit)
    {
        this.gfUnit = gfUnit;
    }

    public String getGfUnit()
    {
        return gfUnit;
    }
    public void setGfWay(Long gfWay)
    {
        this.gfWay = gfWay;
    }

    public Long getGfWay()
    {
        return gfWay;
    }
    public void setGfLn(Long gfLn)
    {
        this.gfLn = gfLn;
    }

    public Long getGfLn()
    {
        return gfLn;
    }
    public void setGfTp(Long gfTp)
    {
        this.gfTp = gfTp;
    }

    public Long getGfTp()
    {
        return gfTp;
    }
    public void setGfEba(Long gfEba)
    {
        this.gfEba = gfEba;
    }

    public Long getGfEba()
    {
        return gfEba;
    }
    public void setGfSb(Long gfSb)
    {
        this.gfSb = gfSb;
    }

    public Long getGfSb()
    {
        return gfSb;
    }
    public void setGfQtime(Date gfQtime)
    {
        this.gfQtime = gfQtime;
    }

    public Date getGfQtime()
    {
        return gfQtime;
    }
    public void setGfQstate(Long gfQstate)
    {
        this.gfQstate = gfQstate;
    }

    public Long getGfQstate()
    {
        return gfQstate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("gfId", getGfId())
                .append("hid", getHid())
                .append("gfName", getGfName())
                .append("gfUnit", getGfUnit())
                .append("gfWay", getGfWay())
                .append("gfLn", getGfLn())
                .append("gfTp", getGfTp())
                .append("gfEba", getGfEba())
                .append("gfSb", getGfSb())
                .append("gfQtime", getGfQtime())
                .append("gfQstate", getGfQstate())
                .toString();
    }
}
