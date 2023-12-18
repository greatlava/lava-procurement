package com.hh.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.util.List;

/**
 * 采购计划对象 ppm_procurement_plan
 *
 * @author ruoyi
 * @date 2023-11-28
 */
public class PpmProcurementPlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 采购计划ID
     */
    private Integer aid;

    /**
     * 采购计划编号
     */
    @Excel(name = "采购计划编号")
    private String aCode;

    /**
     * 计划名称
     */
    @Excel(name = "计划名称")
    private String aName;

    /**
     * 创建部门
     */
    private String aCreateDept;

    /**
     * 附件
     */
    private String fjAnnex;

    /**
     * 采购计划审核意见
     */
    @Excel(name = "采购计划审核意见")
    private String aOpinion;

    /**
     * 行项目数量
     */
    @Excel(name = "行项目数量")
    private Integer aProjectCount;

    /**
     * 采购业务类型名称
     */
    @Excel(name = "采购业务类型名称")
    private Integer aBtype;

    /**
     * 采购审批状态
     */
    @Excel(name = "采购审批状态")
    private Integer aAstate;

    /**
     * 行项目集合
     */
    private List<PpmLineItems> items;

    private ComPubAttachments file;

    public ComPubAttachments getFile() {
        return file;
    }

    public void setFile(ComPubAttachments file) {
        this.file = file;
    }

    public List<PpmLineItems> getItems() {
        return items;
    }

    public void setItems(List<PpmLineItems> items) {
        this.items = items;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getaBtype() {
        return aBtype;
    }

    public void setaBtype(Integer aBtype) {
        this.aBtype = aBtype;
    }

    public Integer getAid() {
        return aid;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaName() {
        return aName;
    }

    public void setaCreateDept(String aCreateDept) {
        this.aCreateDept = aCreateDept;
    }

    public String getaCreateDept() {
        return aCreateDept;
    }

    public void setFjAnnex(String fjAnnex) {
        this.fjAnnex = fjAnnex;
    }

    public String getFjAnnex() {
        return fjAnnex;
    }

    public void setaOpinion(String aOpinion) {
        this.aOpinion = aOpinion;
    }

    public String getaOpinion() {
        return aOpinion;
    }

    public void setaProjectCount(Integer aProjectCount) {
        this.aProjectCount = aProjectCount;
    }

    public Integer getaProjectCount() {
        return aProjectCount;
    }

    public void setaAstate(Integer aAstate) {
        this.aAstate = aAstate;
    }

    public Integer getaAstate() {
        return aAstate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("aid", getAid())
                .append("aCode", getaCode())
                .append("aName", getaName())
                .append("createBy", getCreateBy())
                .append("aCreateDept", getaCreateDept())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("fjAnnex", getFjAnnex())
                .append("aOpinion", getaOpinion())
                .append("aProjectCount", getaProjectCount())
                .append("aBtype", getaBtype())
                .append("aAstate", getaAstate())
                .append("items", getItems())
                .append("file", getFile())
                .toString();
    }
}
