package com.hh.pms.domain;

import java.math.BigDecimal;
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
    @Excel(name = "产品编码")
    private String tid;

    /** 采购计划ID */
    private Long aid;

    /** 招标项目ID */
    private Long tSid;

    /** 框架计划ID */
    private Long jhId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String tName;

    /** 大类 */
    private Long tDa;

    /** 中类 */
    private Long tZhong;

    /** 小类 */
    private Long tXiao;

    /** 数量 */
    @Excel(name = "数量")
    private String tAmount;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String tUnit;

    /** 税率 */
    @Excel(name = "税率")
    private Long shui;

    /** 预算单价 */
    @Excel(name = "预算单价")
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
    private Long aWay;

    /** 采购计划状态 */
    private Long aState;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String tModel;

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getTid()
    {
        return tid;
    }
    public void setAid(Long aid)
    {
        this.aid = aid;
    }

    public Long getAid()
    {
        return aid;
    }
    public void settSid(Long tSid)
    {
        this.tSid = tSid;
    }

    public Long gettSid()
    {
        return tSid;
    }
    public void setJhId(Long jhId)
    {
        this.jhId = jhId;
    }

    public Long getJhId()
    {
        return jhId;
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
    public void settAmount(String tAmount)
    {
        this.tAmount = tAmount;
    }

    public String gettAmount()
    {
        return tAmount;
    }
    public void settUnit(String tUnit)
    {
        this.tUnit = tUnit;
    }

    public String gettUnit()
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
    public void settModel(String tModel)
    {
        this.tModel = tModel;
    }

    public String gettModel()
    {
        return tModel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tid", getTid())
                .append("aid", getAid())
                .append("tSid", gettSid())
                .append("jhId", getJhId())
                .append("tName", gettName())
                .append("tDa", gettDa())
                .append("tZhong", gettZhong())
                .append("tXiao", gettXiao())
                .append("tAmount", gettAmount())
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
                .append("tModel", gettModel())
                .toString();
    }
}
