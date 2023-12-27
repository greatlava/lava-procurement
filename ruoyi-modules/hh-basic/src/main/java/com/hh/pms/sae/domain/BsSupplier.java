package com.hh.pms.sae.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 供应商对象 bs_supplier
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsSupplier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     */
    private Long hid;

    /**
     * 准入ID
     */
    @Excel(name = "准入ID")
    private Long zrId;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String hName;

    /**
     * 统一社会信用代码
     */
    @Excel(name = "统一社会信用代码")
    private String hCreditCode;

    /**
     * 单位注册地
     */
    @Excel(name = "单位注册地")
    private String hIncorporation;

    /**
     * 机构类型
     */
    @Excel(name = "机构类型")
    private String hInstitution;

    /**
     * 企业性质
     */
    @Excel(name = "企业性质")
    private String hQuality;

    /**
     * 成立日期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "成立日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hStartTime;

    /**
     * 法人/负责人
     */
    @Excel(name = "法人/负责人")
    private String hJuridical;

    /**
     * 法人/负责人身份证号
     */
    @Excel(name = "法人/负责人身份证号")
    private String hJuridicalIdentity;

    /**
     * 单位联系地址
     */
    @Excel(name = "单位联系地址")
    private String hAddress;

    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    private String hRange;

    /**
     * 公司简介
     */
    @Excel(name = "公司简介")
    private String hDesc;

    /**
     * 营业执照扫描件
     */
    @Excel(name = "营业执照扫描件")
    private String hCopies;

    /**
     * 法人/负责人身份证扫描件
     */
    @Excel(name = "法人/负责人身份证扫描件")
    private String hJuridicalCopies;

    /**
     * 法人/负责人身份证扫描件数组
     */
    private String[] IdCardCopies;

    /**
     * 营业执照有效期
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Excel(name = "营业执照有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hExpiration;

    /**
     * 开户行
     */
    @Excel(name = "开户行")
    private String hBank;

    /**
     * 银行账号
     */
    @Excel(name = "银行账号")
    private String hAccount;

    /**
     * 开户行地址
     */
    @Excel(name = "开户行地址")
    private String hBankAddress;

    /**
     * 单位注册电话
     */
    @Excel(name = "单位注册电话")
    private String hSignPhone;

    /**
     * 单位注册地址
     */
    @Excel(name = "单位注册地址")
    private String hSignAddress;

    /**
     * 注册资本
     */
    @Excel(name = "注册资本")
    private Long hCapital;

    /**
     * 实缴资本
     */
    @Excel(name = "实缴资本")
    private Long hActualCapital;

    /**
     * 开户许可证/基本户存款信息证明
     */
    @Excel(name = "开户许可证/基本户存款信息证明")
    private String hProve;

    /**
     * 供应商审核状态
     */
    @Excel(name = "供应商审核状态")
    private Long fStatus;

    /**
     * 供应商审核意见
     */
    @Excel(name = "供应商审核意见")
    private String fOpinion;

    /**
     * 供应商状态
     */
    @Excel(name = "供应商状态")
    private Long fState;

    /**
     * 供应商分类
     */
    @Excel(name = "供应商分类")
    private Long fClassify;

    @Excel(name = "供应商账号")
    private String hLoginAccount;

    @Excel(name = "供应商密码")
    private String hPassword;

    public String gethLoginAccount() {
        return hLoginAccount;
    }

    public void sethLoginAccount(String hLoginAccount) {
        this.hLoginAccount = hLoginAccount;
    }

    public String gethPassword() {
        return hPassword;
    }

    public void sethPassword(String hPassword) {
        this.hPassword = hPassword;
    }

    public String[] getIdCardCopies() {
        return IdCardCopies;
    }

    public void setIdCardCopies(String[] idCardCopies) {
        IdCardCopies = idCardCopies;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getHid() {
        return hid;
    }

    public void setZrId(Long zrId) {
        this.zrId = zrId;
    }

    public Long getZrId() {
        return zrId;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethName() {
        return hName;
    }

    public void sethCreditCode(String hCreditCode) {
        this.hCreditCode = hCreditCode;
    }

    public String gethCreditCode() {
        return hCreditCode;
    }

    public void sethIncorporation(String hIncorporation) {
        this.hIncorporation = hIncorporation;
    }

    public String gethIncorporation() {
        return hIncorporation;
    }

    public void sethInstitution(String hInstitution) {
        this.hInstitution = hInstitution;
    }

    public String gethInstitution() {
        return hInstitution;
    }

    public void sethQuality(String hQuality) {
        this.hQuality = hQuality;
    }

    public String gethQuality() {
        return hQuality;
    }

    public void sethStartTime(Date hStartTime) {
        this.hStartTime = hStartTime;
    }

    public Date gethStartTime() {
        return hStartTime;
    }

    public void sethJuridical(String hJuridical) {
        this.hJuridical = hJuridical;
    }

    public String gethJuridical() {
        return hJuridical;
    }

    public void sethJuridicalIdentity(String hJuridicalIdentity) {
        this.hJuridicalIdentity = hJuridicalIdentity;
    }

    public String gethJuridicalIdentity() {
        return hJuridicalIdentity;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethRange(String hRange) {
        this.hRange = hRange;
    }

    public String gethRange() {
        return hRange;
    }

    public void sethDesc(String hDesc) {
        this.hDesc = hDesc;
    }

    public String gethDesc() {
        return hDesc;
    }

    public void sethCopies(String hCopies) {
        this.hCopies = hCopies;
    }

    public String gethCopies() {
        return hCopies;
    }

    public void sethJuridicalCopies(String hJuridicalCopies) {
        this.hJuridicalCopies = hJuridicalCopies;
    }

    public String gethJuridicalCopies() {
        return hJuridicalCopies;
    }

    public void sethExpiration(Date hExpiration) {
        this.hExpiration = hExpiration;
    }

    public Date gethExpiration() {
        return hExpiration;
    }

    public void sethBank(String hBank) {
        this.hBank = hBank;
    }

    public String gethBank() {
        return hBank;
    }

    public void sethAccount(String hAccount) {
        this.hAccount = hAccount;
    }

    public String gethAccount() {
        return hAccount;
    }

    public void sethBankAddress(String hBankAddress) {
        this.hBankAddress = hBankAddress;
    }

    public String gethBankAddress() {
        return hBankAddress;
    }

    public void sethSignPhone(String hSignPhone) {
        this.hSignPhone = hSignPhone;
    }

    public String gethSignPhone() {
        return hSignPhone;
    }

    public void sethSignAddress(String hSignAddress) {
        this.hSignAddress = hSignAddress;
    }

    public String gethSignAddress() {
        return hSignAddress;
    }

    public void sethCapital(Long hCapital) {
        this.hCapital = hCapital;
    }

    public Long gethCapital() {
        return hCapital;
    }

    public void sethActualCapital(Long hActualCapital) {
        this.hActualCapital = hActualCapital;
    }

    public Long gethActualCapital() {
        return hActualCapital;
    }

    public void sethProve(String hProve) {
        this.hProve = hProve;
    }

    public String gethProve() {
        return hProve;
    }

    public void setfStatus(Long fStatus) {
        this.fStatus = fStatus;
    }

    public Long getfStatus() {
        return fStatus;
    }

    public void setfOpinion(String fOpinion) {
        this.fOpinion = fOpinion;
    }

    public String getfOpinion() {
        return fOpinion;
    }

    public void setfState(Long fState) {
        this.fState = fState;
    }

    public Long getfState() {
        return fState;
    }

    public void setfClassify(Long fClassify) {
        this.fClassify = fClassify;
    }

    public Long getfClassify() {
        return fClassify;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("hid", getHid())
                .append("zrId", getZrId())
                .append("hName", gethName())
                .append("hCreditCode", gethCreditCode())
                .append("hIncorporation", gethIncorporation())
                .append("hInstitution", gethInstitution())
                .append("hQuality", gethQuality())
                .append("hStartTime", gethStartTime())
                .append("hJuridical", gethJuridical())
                .append("hJuridicalIdentity", gethJuridicalIdentity())
                .append("hAddress", gethAddress())
                .append("hRange", gethRange())
                .append("hDesc", gethDesc())
                .append("hCopies", gethCopies())
                .append("hJuridicalCopies", gethJuridicalCopies())
                .append("hExpiration", gethExpiration())
                .append("hBank", gethBank())
                .append("hAccount", gethAccount())
                .append("hBankAddress", gethBankAddress())
                .append("hSignPhone", gethSignPhone())
                .append("hSignAddress", gethSignAddress())
                .append("hCapital", gethCapital())
                .append("hActualCapital", gethActualCapital())
                .append("hProve", gethProve())
                .append("fStatus", getfStatus())
                .append("fOpinion", getfOpinion())
                .append("fState", getfState())
                .append("fClassify", getfClassify())
                .append("hLoginAccount", gethLoginAccount())
                .append("hPassword", gethPassword())
                .toString();
    }
}
