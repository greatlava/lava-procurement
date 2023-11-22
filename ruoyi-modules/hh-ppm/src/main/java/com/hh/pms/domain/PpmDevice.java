package com.hh.pms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 设备信息对象 ppm_device
 *
 * @author ruoyi
 * @date 2023-11-21
 */
public class PpmDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    private Long tid;

    /** 行项目编号 */
    @Excel(name = "行项目编号")
    private String vCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String tName;

    /** 大类 */
    @Excel(name = "大类")
    private Long tDa;

    /** 中类 */
    @Excel(name = "中类")
    private Long tZhong;

    /** 小类 */
    @Excel(name = "小类")
    private Long tXiao;

    /** 数量 */
    @Excel(name = "数量")
    private Long tUnit;

    /** 税率 */
    @Excel(name = "税率")
    private Long shui;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private Long tPrice;

    /** 预算总价 */
    @Excel(name = "预算总价")
    private Long tTotalPrice;

    /** 交付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tDate;

    /** 交付地点 */
    @Excel(name = "交付地点")
    private String tAddress;

    /** 需求说明 */
    @Excel(name = "需求说明")
    private String tIllustrate;

    /** 采购人 */
    @Excel(name = "采购人")
    private String tPurchaser;

    /** 预算科目编号 */
    @Excel(name = "预算科目编号")
    private String duCode;

    /** 备注 */
    @Excel(name = "备注")
    private String tNotes;

    /** 采购方式1：公开招标 2：邀请招标 3：询价 4：委托 5：竞争性谈判 6：单一来源 */
    @Excel(name = "采购方式1：公开招标 2：邀请招标 3：询价 4：委托 5：竞争性谈判 6：单一来源")
    private Long aWay;

    /** 采购计划状态 */
    @Excel(name = "采购计划状态")
    private Long aState;

    public void setTid(Long tid)
    {
        this.tid = tid;
    }

    public Long getTid()
    {
        return tid;
    }
    public void setvCode(String vCode)
    {
        this.vCode = vCode;
    }

    public String getvCode()
    {
        return vCode;
    }
    public void settName(String tName)
    {
        this.tName = tName;
    }

    public String gettName()
    {
        return tName;
    }
    public void settDa(Long tDa)
    {
        this.tDa = tDa;
    }

    public Long gettDa()
    {
        return tDa;
    }
    public void settZhong(Long tZhong)
    {
        this.tZhong = tZhong;
    }

    public Long gettZhong()
    {
        return tZhong;
    }
    public void settXiao(Long tXiao)
    {
        this.tXiao = tXiao;
    }

    public Long gettXiao()
    {
        return tXiao;
    }
    public void settUnit(Long tUnit)
    {
        this.tUnit = tUnit;
    }

    public Long gettUnit()
    {
        return tUnit;
    }
    public void setShui(Long shui)
    {
        this.shui = shui;
    }

    public Long getShui()
    {
        return shui;
    }
    public void settPrice(Long tPrice)
    {
        this.tPrice = tPrice;
    }

    public Long gettPrice()
    {
        return tPrice;
    }
    public void settTotalPrice(Long tTotalPrice)
    {
        this.tTotalPrice = tTotalPrice;
    }

    public Long gettTotalPrice()
    {
        return tTotalPrice;
    }
    public void settDate(Date tDate)
    {
        this.tDate = tDate;
    }

    public Date gettDate()
    {
        return tDate;
    }
    public void settAddress(String tAddress)
    {
        this.tAddress = tAddress;
    }

    public String gettAddress()
    {
        return tAddress;
    }
    public void settIllustrate(String tIllustrate)
    {
        this.tIllustrate = tIllustrate;
    }

    public String gettIllustrate()
    {
        return tIllustrate;
    }
    public void settPurchaser(String tPurchaser)
    {
        this.tPurchaser = tPurchaser;
    }

    public String gettPurchaser()
    {
        return tPurchaser;
    }
    public void setDuCode(String duCode)
    {
        this.duCode = duCode;
    }

    public String getDuCode()
    {
        return duCode;
    }
    public void settNotes(String tNotes)
    {
        this.tNotes = tNotes;
    }

    public String gettNotes()
    {
        return tNotes;
    }
    public void setaWay(Long aWay)
    {
        this.aWay = aWay;
    }

    public Long getaWay()
    {
        return aWay;
    }
    public void setaState(Long aState)
    {
        this.aState = aState;
    }

    public Long getaState()
    {
        return aState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tid", getTid())
                .append("vCode", getvCode())
                .append("tDa", gettDa())
                .append("tZhong", gettZhong())
                .append("tXiao", gettXiao())
                .append("tUnit", gettUnit())
                .append("shui", getShui())
                .append("tPrice", gettPrice())
                .append("tTotalPrice", gettTotalPrice())
                .append("tDate", gettDate())
                .append("tAddress", gettAddress())
                .append("tIllustrate", gettIllustrate())
                .append("tPurchaser", gettPurchaser())
                .append("duCode", getDuCode())
                .append("tNotes", gettNotes())
                .append("aWay", getaWay())
                .append("aState", getaState())
                .toString();
    }
}
