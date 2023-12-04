package com.hh.pms.sae.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 专家对象 bs_expert
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsExpert extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 专家ID
     */
    private Long jid;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String jName;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private Long jSex;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    private String jIdentity;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String jPhone;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 健康状况
     */
    @Excel(name = "健康状况")
    private String jState;

    /**
     * 工作单位
     */
    @Excel(name = "工作单位")
    private String jUnit;

    /**
     * 是否退休
     */
    @Excel(name = "是否退休")
    private Long jRetire;

    /**
     * 通讯地址
     */
    @Excel(name = "通讯地址")
    private String jAddress;

    /**
     * 银行卡号
     */
    @Excel(name = "银行卡号")
    private String jCard;

    /**
     * 身份证照
     */
    @Excel(name = "身份证照")
    private String jIdentityPhoto;

    /**
     * 证件照
     */
    @Excel(name = "证件照")
    private String jDocumentsPhoto;

    /**
     * 开户行
     */
    @Excel(name = "开户行")
    private String jBank;

    /**
     * 专家审核状态
     */
    @Excel(name = "专家审核状态")
    private Long jShState;

    /**
     * 意见
     */
    @Excel(name = "意见")
    private String jOpinion;

    private BsExperience experience;

    public BsExperience getExperience() {
        return experience;
    }

    public void setExperience(BsExperience experience) {
        this.experience = experience;
    }

    public void setJid(Long jid) {
        this.jid = jid;
    }

    public Long getJid() {
        return jid;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public String getjName() {
        return jName;
    }

    public void setjSex(Long jSex) {
        this.jSex = jSex;
    }

    public Long getjSex() {
        return jSex;
    }

    public void setjIdentity(String jIdentity) {
        this.jIdentity = jIdentity;
    }

    public String getjIdentity() {
        return jIdentity;
    }

    public void setjPhone(String jPhone) {
        this.jPhone = jPhone;
    }

    public String getjPhone() {
        return jPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setjState(String jState) {
        this.jState = jState;
    }

    public String getjState() {
        return jState;
    }

    public void setjUnit(String jUnit) {
        this.jUnit = jUnit;
    }

    public String getjUnit() {
        return jUnit;
    }

    public void setjRetire(Long jRetire) {
        this.jRetire = jRetire;
    }

    public Long getjRetire() {
        return jRetire;
    }

    public void setjAddress(String jAddress) {
        this.jAddress = jAddress;
    }

    public String getjAddress() {
        return jAddress;
    }

    public void setjCard(String jCard) {
        this.jCard = jCard;
    }

    public String getjCard() {
        return jCard;
    }

    public void setjIdentityPhoto(String jIdentityPhoto) {
        this.jIdentityPhoto = jIdentityPhoto;
    }

    public String getjIdentityPhoto() {
        return jIdentityPhoto;
    }

    public void setjDocumentsPhoto(String jDocumentsPhoto) {
        this.jDocumentsPhoto = jDocumentsPhoto;
    }

    public String getjDocumentsPhoto() {
        return jDocumentsPhoto;
    }

    public void setjBank(String jBank) {
        this.jBank = jBank;
    }

    public String getjBank() {
        return jBank;
    }

    public void setjShState(Long jShState) {
        this.jShState = jShState;
    }

    public Long getjShState() {
        return jShState;
    }

    public void setjOpinion(String jOpinion) {
        this.jOpinion = jOpinion;
    }

    public String getjOpinion() {
        return jOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("jid", getJid())
                .append("jName", getjName())
                .append("jSex", getjSex())
                .append("jIdentity", getjIdentity())
                .append("jPhone", getjPhone())
                .append("email", getEmail())
                .append("jState", getjState())
                .append("jUnit", getjUnit())
                .append("jRetire", getjRetire())
                .append("jAddress", getjAddress())
                .append("jCard", getjCard())
                .append("jIdentityPhoto", getjIdentityPhoto())
                .append("jDocumentsPhoto", getjDocumentsPhoto())
                .append("jBank", getjBank())
                .append("jShState", getjShState())
                .append("jOpinion", getjOpinion())
                .toString();
    }
}
