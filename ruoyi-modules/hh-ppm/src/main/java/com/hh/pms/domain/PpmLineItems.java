package com.hh.pms.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 行项目对象 ppm_line_items
 *
 * @author ruoyi
 * @date 2023-11-28
 */
public class PpmLineItems extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 行项目ID
     */
    private Integer vid;

    /**
     * 行项目编号
     */
    @Excel(name = "行项目编号")
    private String vCode;

    /**
     * 招标项目ID
     */
    private Integer sid;

    /**
     * 框架计划ID
     */
    private Integer jhId;

    /**
     * 采购计划ID
     */
    private Integer aid;

    /**
     * 设备编号
     */
    private String tid;

    /**
     * 非招标项目ID
     */
    private Integer gid;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer vCount;

    /**
     * 采购人
     */
    @Excel(name = "采购人")
    private String vPerson;

    /**
     * 交付时间
     */
    @Excel(name = "交付时间")
    private Date vDeliveryTime;

    /**
     * 交付地点
     */
    @Excel(name = "交付地点")
    private String vDeliveryArea;

    /**
     * 需求说明
     */
    @Excel(name = "需求说明")
    private String vIllustrate;

    /**
     * 采购方式
     */
    @Excel(name = "采购方式")
    private Integer procurementMethod;

    /**
     * 预算编号
     */
    @Excel(name = "预算编号")
    private String did;

    /**
     * 设备信息
     */
    private PpmDevice ppmDevice;

    private PpmBudget ppmBudget;

    private PpmFramePlan ppmFramePlan;

//    private BsSupplier bsSupplier;
//
//    public BsSupplier getBsSupplier() {
//        return bsSupplier;
//    }
//
//    public void setBsSupplier(BsSupplier bsSupplier) {
//        this.bsSupplier = bsSupplier;
//    }

    public PpmFramePlan getPpmFramePlan() {
        return ppmFramePlan;
    }

    public void setPpmFramePlan(PpmFramePlan ppmFramePlan) {
        this.ppmFramePlan = ppmFramePlan;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getvCode() {
        return vCode;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setJhId(Integer jhId) {
        this.jhId = jhId;
    }

    public Integer getJhId() {
        return jhId;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAid() {
        return aid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setvCount(Integer vCount) {
        this.vCount = vCount;
    }

    public Integer getvCount() {
        return vCount;
    }

    public void setvPerson(String vPerson) {
        this.vPerson = vPerson;
    }

    public String getvPerson() {
        return vPerson;
    }

    public Date getvDeliveryTime() {
        return vDeliveryTime;
    }

    public void setvDeliveryTime(Date vDeliveryTime) {
        this.vDeliveryTime = vDeliveryTime;
    }

    public void setvDeliveryArea(String vDeliveryArea) {
        this.vDeliveryArea = vDeliveryArea;
    }

    public String getvDeliveryArea() {
        return vDeliveryArea;
    }

    public void setvIllustrate(String vIllustrate) {
        this.vIllustrate = vIllustrate;
    }

    public String getvIllustrate() {
        return vIllustrate;
    }

    public void setProcurementMethod(Integer procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public PpmDevice getPpmDevice() {
        return ppmDevice;
    }

    public void setPpmDevice(PpmDevice ppmDevice) {
        this.ppmDevice = ppmDevice;
    }

    public PpmBudget getPpmBudget() {
        return ppmBudget;
    }

    public void setPpmBudget(PpmBudget ppmBudget) {
        this.ppmBudget = ppmBudget;
    }

    public Integer getProcurementMethod() {
        return procurementMethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("vid", getVid())
                .append("vCode", getvCode())
                .append("sid", getSid())
                .append("jhId", getJhId())
                .append("aid", getAid())
                .append("tid", getTid())
                .append("gid", getGid())
                .append("vCount", getvCount())
                .append("vPerson", getvPerson())
                .append("vDeliveryTime", getvDeliveryTime())
                .append("vDeliveryArea", getvDeliveryArea())
                .append("vIllustrate", getvIllustrate())
                .append("procurementMethod", getProcurementMethod())
                .append("ppmDevice", getPpmDevice())
                .append("ppmBudget", getPpmBudget())
                .append("did", getDid())
                .toString();
    }
}
