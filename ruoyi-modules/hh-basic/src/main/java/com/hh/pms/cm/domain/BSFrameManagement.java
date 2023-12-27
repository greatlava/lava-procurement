package com.hh.pms.cm.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 框架协议管理对象 bs_frame_management
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public class BSFrameManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 框架协议ID */
    private Long oid;

    /** 框架计划ID */
    @Excel(name = "框架计划ID")
    private Long jhId;

    /** 框架协议编号 */
    @Excel(name = "框架协议编号")
    private String oCode;

    /** 框架协议名称 */
    @Excel(name = "框架协议名称")
    private String oName;

    /** 签署主体 */
    @Excel(name = "签署主体")
    private String oSubject;

    /** 相对方ID */
    @Excel(name = "相对方ID")
    private Long hid;

    /** 相对方名称 */
    @Excel(name = "相对方名称")
    private String hName;

    /** 签署时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签署时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oStartdate;

    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oEnddate;

    /** 协议文件 */
    @Excel(name = "协议文件")
    private String oFile;

    /** 协议状态 */
    @Excel(name = "协议状态")
    private Long oHstatus;

    /** 协议类型 */
    @Excel(name = "协议类型")
    private String oType;

    /** 说明 */
    @Excel(name = "说明")
    private String oDescribe;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String oOpinion;

    /** 是否作废 */
    @Excel(name = "是否作废")
    private Long oCancel;

    public Long getoCancel() {
        return oCancel;
    }

    public void setoCancel(Long oCancel) {
        this.oCancel = oCancel;
    }

    private List<BsInventory> bsInventoryList;

    public List<BsInventory> getBsInventoryList() {
        return bsInventoryList;
    }

    public void setBsInventoryList(List<BsInventory> bsInventoryList) {
        this.bsInventoryList = bsInventoryList;
    }

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal oTotalprice;

    public void setOid(Long oid)
    {
        this.oid = oid;
    }

    public Long getOid()
    {
        return oid;
    }
    public void setJhId(Long jhId)
    {
        this.jhId = jhId;
    }

    public Long getJhId()
    {
        return jhId;
    }
    public void setoCode(String oCode)
    {
        this.oCode = oCode;
    }

    public String getoCode()
    {
        return oCode;
    }
    public void setoName(String oName)
    {
        this.oName = oName;
    }

    public String getoName()
    {
        return oName;
    }
    public void setoSubject(String oSubject)
    {
        this.oSubject = oSubject;
    }

    public String getoSubject()
    {
        return oSubject;
    }
    public void setHid(Long hid)
    {
        this.hid = hid;
    }

    public Long getHid()
    {
        return hid;
    }
    public void sethName(String hName)
    {
        this.hName = hName;
    }

    public String gethName()
    {
        return hName;
    }
    public void setoStartdate(Date oStartdate)
    {
        this.oStartdate = oStartdate;
    }

    public Date getoStartdate()
    {
        return oStartdate;
    }
    public void setoEnddate(Date oEnddate)
    {
        this.oEnddate = oEnddate;
    }

    public Date getoEnddate()
    {
        return oEnddate;
    }
    public void setoFile(String oFile)
    {
        this.oFile = oFile;
    }

    public String getoFile()
    {
        return oFile;
    }
    public void setoHstatus(Long oHstatus)
    {
        this.oHstatus = oHstatus;
    }

    public Long getoHstatus()
    {
        return oHstatus;
    }
    public void setoType(String oType)
    {
        this.oType = oType;
    }

    public String getoType()
    {
        return oType;
    }
    public void setoDescribe(String oDescribe)
    {
        this.oDescribe = oDescribe;
    }

    public String getoDescribe()
    {
        return oDescribe;
    }
    public void setoOpinion(String oOpinion)
    {
        this.oOpinion = oOpinion;
    }

    public String getoOpinion()
    {
        return oOpinion;
    }
    public void setoTotalprice(BigDecimal oTotalprice)
    {
        this.oTotalprice = oTotalprice;
    }

    public BigDecimal getoTotalprice()
    {
        return oTotalprice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("oid", getOid())
                .append("jhId", getJhId())
                .append("oCode", getoCode())
                .append("oName", getoName())
                .append("oSubject", getoSubject())
                .append("hid", getHid())
                .append("hName", gethName())
                .append("oStartdate", getoStartdate())
                .append("oEnddate", getoEnddate())
                .append("oFile", getoFile())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("oHstatus", getoHstatus())
                .append("oType", getoType())
                .append("oDescribe", getoDescribe())
                .append("oOpinion", getoOpinion())
                .append("oCancel", getoCancel())
                .append("oTotalprice", getoTotalprice())
                .append("bsInventoryList", getBsInventoryList())
                .toString();
    }
}
