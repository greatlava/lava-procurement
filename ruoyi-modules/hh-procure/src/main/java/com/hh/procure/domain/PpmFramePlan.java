package com.hh.procure.domain;


import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 框架计划对象 ppm_frame_plan
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class PpmFramePlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 框架计划ID
     */
    private Integer jhId;

    /**
     * 框架协议ID
     */
    @Excel(name = "框架协议ID")
    private Long oid;

    /**
     * 框架计划编码
     */
    @Excel(name = "框架计划编码")
    private String jhCode;

    /**
     * 计划名称
     */
    @Excel(name = "计划名称")
    private String jhName;

    /**
     * 计划预算
     */
    @Excel(name = "计划预算")
    private Long jhYu;

    /**
     * 创建部门
     */
    @Excel(name = "创建部门")
    private String dept;

    /**
     * 计划状态
     */
    @Excel(name = "计划状态")
    private Long jhStatus;

    /**
     * 审批人
     */
    @Excel(name = "审批人")
    private String jhPerson;

    /**
     * 采购方式
     */
    @Excel(name = "采购方式")
    private String jhPmethod;

    @Excel(name = "创建人")
    private String jhFounder;

    @Excel(name = "供应商id")
    private Integer hid;

    @Excel(name = "业务类型")
    private Integer businessType;

    private BsSupplier bsSupplier;

    private ComPubAttachments comPubAttachments;

    public ComPubAttachments getComPubAttachments() {
        return comPubAttachments;
    }

    public void setComPubAttachments(ComPubAttachments comPubAttachments) {
        this.comPubAttachments = comPubAttachments;
    }

    public BsSupplier getBsSupplier() {
        return bsSupplier;
    }
    public void setBsSupplier(BsSupplier bsSupplier) {
        this.bsSupplier = bsSupplier;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getJhFounder() {
        return jhFounder;
    }

    public void setJhFounder(String jhFounder) {
        this.jhFounder = jhFounder;
    }

    /**
     * 行项目集合
     */
    private List<PpmLineItems> items;

    public List<PpmLineItems> getItems() {
        return items;
    }

    public void setItems(List<PpmLineItems> items) {
        this.items = items;
    }

    public Integer getJhId() {
        return jhId;
    }

    public void setJhId(Integer jhId) {
        this.jhId = jhId;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getOid() {
        return oid;
    }

    public void setJhCode(String jhCode) {
        this.jhCode = jhCode;
    }

    public String getJhCode() {
        return jhCode;
    }

    public void setJhName(String jhName) {
        this.jhName = jhName;
    }

    public String getJhName() {
        return jhName;
    }

    public void setJhYu(Long jhYu) {
        this.jhYu = jhYu;
    }

    public Long getJhYu() {
        return jhYu;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    public void setJhStatus(Long jhStatus) {
        this.jhStatus = jhStatus;
    }

    public Long getJhStatus() {
        return jhStatus;
    }

    public void setJhPerson(String jhPerson) {
        this.jhPerson = jhPerson;
    }

    public String getJhPerson() {
        return jhPerson;
    }

    public void setJhPmethod(String jhPmethod) {
        this.jhPmethod = jhPmethod;
    }

    public String getJhPmethod() {
        return jhPmethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("jhId", getJhId())
                .append("oid", getOid())
                .append("jhCode", getJhCode())
                .append("jhName", getJhName())
                .append("jhYu", getJhYu())
                .append("dept", getDept())
                .append("jhStatus", getJhStatus())
                .append("jhPerson", getJhPerson())
                .append("jhPmethod", getJhPmethod())
                .append("items", getItems())
                .append("comPubAttachments",getComPubAttachments())
                .toString();
    }
}
