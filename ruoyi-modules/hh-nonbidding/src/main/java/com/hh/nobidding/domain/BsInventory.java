package com.hh.nobidding.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 产品表对象 bs_inventory
 *
 * @author ruoyi
 * @date 2023-12-25
 */
public class BsInventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标的ID */
    private Long inId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String tid;

    /** 合同ID */
    @Excel(name = "合同ID")
    private Long eid;

    /** 非招标ID */
    @Excel(name = "非招标ID")
    private Long gid;

    /** 框架协议ID */
    @Excel(name = "框架协议ID")
    private Long oid;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String inName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String inModel;

    /** 单位 */
    @Excel(name = "单位")
    private String inUnit;

    /** 含税单价 */
    @Excel(name = "含税单价")
    private BigDecimal inVat;

    /** 数量 */
    @Excel(name = "数量")
    private Long inCount;

    /** 小计 */
    @Excel(name = "小计")
    private BigDecimal inSubtotal;

    public void setInId(Long inId)
    {
        this.inId = inId;
    }

    public Long getInId()
    {
        return inId;
    }
    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getTid()
    {
        return tid;
    }
    public void setEid(Long eid)
    {
        this.eid = eid;
    }

    public Long getEid()
    {
        return eid;
    }
    public void setGid(Long gid)
    {
        this.gid = gid;
    }

    public Long getGid()
    {
        return gid;
    }
    public void setOid(Long oid)
    {
        this.oid = oid;
    }

    public Long getOid()
    {
        return oid;
    }
    public void setInName(String inName)
    {
        this.inName = inName;
    }

    public String getInName()
    {
        return inName;
    }
    public void setInModel(String inModel)
    {
        this.inModel = inModel;
    }

    public String getInModel()
    {
        return inModel;
    }
    public void setInUnit(String inUnit)
    {
        this.inUnit = inUnit;
    }

    public String getInUnit()
    {
        return inUnit;
    }
    public void setInVat(BigDecimal inVat)
    {
        this.inVat = inVat;
    }

    public BigDecimal getInVat()
    {
        return inVat;
    }
    public void setInCount(Long inCount)
    {
        this.inCount = inCount;
    }

    public Long getInCount()
    {
        return inCount;
    }
    public void setInSubtotal(BigDecimal inSubtotal)
    {
        this.inSubtotal = inSubtotal;
    }

    public BigDecimal getInSubtotal()
    {
        return inSubtotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("inId", getInId())
                .append("tid", getTid())
                .append("eid", getEid())
                .append("gid", getGid())
                .append("oid", getOid())
                .append("inName", getInName())
                .append("inModel", getInModel())
                .append("inUnit", getInUnit())
                .append("inVat", getInVat())
                .append("inCount", getInCount())
                .append("inSubtotal", getInSubtotal())
                .toString();
    }
}
