package com.hh.bidding.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 评标委员会对象 bid_committee
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public class BidCommittee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评标ID */
    private Long pbId;

    /** 抽取申请ID */
    @Excel(name = "抽取申请ID")
    private Long xid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String pbName;

    /** 性别 */
    @Excel(name = "性别")
    private String pbSex;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String pbIdCard;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String pbPhone;

    /** 专家类型 */
    @Excel(name = "专家类型")
    private String pbGenre;

    /** 专家类别 */
    @Excel(name = "专家类别")
    private String pbTypes;

    /** 专家ID */
    @Excel(name = "专家ID")
    private Long jid;

    @Excel(name = "招标项目Id")
    private Long sid;

    private BsExpert bsExpert;

    public void setPbId(Long pbId)
    {
        this.pbId = pbId;
    }

    public Long getPbId()
    {
        return pbId;
    }
    public void setXid(Long xid)
    {
        this.xid = xid;
    }

    public Long getXid()
    {
        return xid;
    }
    public void setPbName(String pbName)
    {
        this.pbName = pbName;
    }

    public String getPbName()
    {
        return pbName;
    }
    public void setPbSex(String pbSex)
    {
        this.pbSex = pbSex;
    }

    public String getPbSex()
    {
        return pbSex;
    }
    public void setPbIdCard(String pbIdCard)
    {
        this.pbIdCard = pbIdCard;
    }

    public String getPbIdCard()
    {
        return pbIdCard;
    }
    public void setPbPhone(String pbPhone)
    {
        this.pbPhone = pbPhone;
    }

    public String getPbPhone()
    {
        return pbPhone;
    }
    public void setPbGenre(String pbGenre)
    {
        this.pbGenre = pbGenre;
    }

    public String getPbGenre()
    {
        return pbGenre;
    }
    public void setPbTypes(String pbTypes)
    {
        this.pbTypes = pbTypes;
    }

    public String getPbTypes()
    {
        return pbTypes;
    }
    public void setJid(Long jid)
    {
        this.jid = jid;
    }

    public Long getJid()
    {
        return jid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("pbId", getPbId())
                .append("xid", getXid())
                .append("pbName", getPbName())
                .append("pbSex", getPbSex())
                .append("pbIdCard", getPbIdCard())
                .append("pbPhone", getPbPhone())
                .append("pbGenre", getPbGenre())
                .append("pbTypes", getPbTypes())
                .append("jid", getJid())
                .append("sid", getSid())
                .toString();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public BsExpert getBsExpert() {
        return bsExpert;
    }

    public void setBsExpert(BsExpert bsExpert) {
        this.bsExpert = bsExpert;
    }
}
