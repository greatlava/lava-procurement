package com.hh.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 审批记录对象 ppm_approval_record
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public class PpmApprovalRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Integer rid;

    /** 采购计划id */
    @Excel(name = "采购计划id")
    private Integer aid;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String node;

    /** 处理人 */
    @Excel(name = "处理人")
    private String processedBy;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private String depnt;

    /** 处理意见 */
    @Excel(name = "处理意见")
    private String opinion;

    /** 意见详情 */
    @Excel(name = "意见详情")
    private String opinionDetails;

    public void setRid(Integer rid) 
    {
        this.rid = rid;
    }

    public Integer getRid() 
    {
        return rid;
    }
    public void setAid(Integer aid) 
    {
        this.aid = aid;
    }

    public Integer getAid() 
    {
        return aid;
    }
    public void setNode(String node) 
    {
        this.node = node;
    }

    public String getNode() 
    {
        return node;
    }
    public void setProcessedBy(String processedBy) 
    {
        this.processedBy = processedBy;
    }

    public String getProcessedBy() 
    {
        return processedBy;
    }
    public void setDepnt(String depnt) 
    {
        this.depnt = depnt;
    }

    public String getDepnt() 
    {
        return depnt;
    }
    public void setOpinion(String opinion) 
    {
        this.opinion = opinion;
    }

    public String getOpinion() 
    {
        return opinion;
    }
    public void setOpinionDetails(String opinionDetails) 
    {
        this.opinionDetails = opinionDetails;
    }

    public String getOpinionDetails() 
    {
        return opinionDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("aid", getAid())
            .append("node", getNode())
            .append("processedBy", getProcessedBy())
            .append("depnt", getDepnt())
            .append("updateTime", getUpdateTime())
            .append("opinion", getOpinion())
            .append("opinionDetails", getOpinionDetails())
            .toString();
    }
}
