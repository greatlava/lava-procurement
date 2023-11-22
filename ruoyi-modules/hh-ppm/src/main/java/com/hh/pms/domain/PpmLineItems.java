package com.hh.pms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 行项目对象 ppm_line_items
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class PpmLineItems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行项目ID */
    private Long vid;

    /** 行项目编号 */
    @Excel(name = "行项目编号")
    private String vCode;

    /** 数量 */
    @Excel(name = "数量")
    private Long vCount;

    /** 采购人 */
    @Excel(name = "采购人")
    private String vPerson;

    /** 预算单价 */
    @Excel(name = "预算单价")
    private Long vPrice;

    /** 预算总价 */
    @Excel(name = "预算总价")
    private Long vTotal;

    /** 交付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vDeliveryTime;

    /** 交付地点 */
    @Excel(name = "交付地点")
    private String vDeliveryArea;

    /** 需求说明 */
    @Excel(name = "需求说明")
    private String vIllustrate;

    public void setVid(Long vid)
    {
        this.vid = vid;
    }

    public Long getVid()
    {
        return vid;
    }
    public void setvCode(String vCode)
    {
        this.vCode = vCode;
    }

    public String getvCode()
    {
        return vCode;
    }
    public void setvCount(Long vCount)
    {
        this.vCount = vCount;
    }

    public Long getvCount()
    {
        return vCount;
    }
    public void setvPerson(String vPerson)
    {
        this.vPerson = vPerson;
    }

    public String getvPerson()
    {
        return vPerson;
    }
    public void setvPrice(Long vPrice)
    {
        this.vPrice = vPrice;
    }

    public Long getvPrice()
    {
        return vPrice;
    }
    public void setvTotal(Long vTotal)
    {
        this.vTotal = vTotal;
    }

    public Long getvTotal()
    {
        return vTotal;
    }
    public void setvDeliveryTime(Date vDeliveryTime)
    {
        this.vDeliveryTime = vDeliveryTime;
    }

    public Date getvDeliveryTime()
    {
        return vDeliveryTime;
    }
    public void setvDeliveryArea(String vDeliveryArea)
    {
        this.vDeliveryArea = vDeliveryArea;
    }

    public String getvDeliveryArea()
    {
        return vDeliveryArea;
    }
    public void setvIllustrate(String vIllustrate)
    {
        this.vIllustrate = vIllustrate;
    }

    public String getvIllustrate()
    {
        return vIllustrate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("vid", getVid())
                .append("vCode", getvCode())
                .append("vCount", getvCount())
                .append("vPerson", getvPerson())
                .append("vPrice", getvPrice())
                .append("vTotal", getvTotal())
                .append("vDeliveryTime", getvDeliveryTime())
                .append("vDeliveryArea", getvDeliveryArea())
                .append("vIllustrate", getvIllustrate())
                .toString();
    }
}
