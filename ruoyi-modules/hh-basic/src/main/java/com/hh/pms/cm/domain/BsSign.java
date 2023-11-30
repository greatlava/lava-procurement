package com.hh.pms.cm.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 签署执行状态对象 bs_sign
 *
 * @author ruoyi
 * @date 2023-11-30
 */
public class BsSign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同签署ID */
    @Excel(name = "合同签署ID")
    private Long gnId;

    /** 签署方数 */
    @Excel(name = "签署方数")
    private Long gnSignatorycount;

    /** 我方主体 */
    @Excel(name = "我方主体")
    private String gnSub;

    /** 乙方供应商ID */
    @Excel(name = "乙方供应商ID")
    private Long gnPbid;

    /** 乙方名称 */
    @Excel(name = "乙方名称")
    private String gnPbname;

    /** 乙方地址 */
    @Excel(name = "乙方地址")
    private String gnPbaddress;

    /** 乙方联系人 */
    @Excel(name = "乙方联系人")
    private String gnPbcontact;

    /** 乙方联系方式 */
    @Excel(name = "乙方联系方式")
    private String gnPbcif;

    /** 乙方开户行 */
    @Excel(name = "乙方开户行")
    private String gnPbbank;

    /** 乙方开户账号 */
    @Excel(name = "乙方开户账号")
    private String gnPbaccount;

    /** 合同方金额 */
    @Excel(name = "合同方金额")
    private Long gnPbamount;

    /** 币别 */
    @Excel(name = "币别")
    private String gnPbcurrency;

    /** 已支付金额 */
    @Excel(name = "已支付金额")
    private Long gnPbpayment;

    /** 锁定金额 */
    @Excel(name = "锁定金额")
    private Long gnPbfixedprice;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private Long gnPbbalance;

    /** 丙方供应商ID */
    @Excel(name = "丙方供应商ID")
    private Long gnPcid;

    /** 丙方名称 */
    @Excel(name = "丙方名称")
    private String gnPcname;

    /** 丙方地址 */
    @Excel(name = "丙方地址")
    private String gnPcaddress;

    /** 丙方联系人 */
    @Excel(name = "丙方联系人")
    private String gnPccontact;

    /** 丙方联系方式 */
    @Excel(name = "丙方联系方式")
    private String gnPccif;

    /** 丙方开户行 */
    @Excel(name = "丙方开户行")
    private String gnPcbank;

    /** 丙方开户账号 */
    @Excel(name = "丙方开户账号")
    private String gnPcaccount;

    /** 合同方金额 */
    @Excel(name = "合同方金额")
    private Long gnPcamount;

    /** 币别 */
    @Excel(name = "币别")
    private String gnPccurrency;

    /** 已支付金额 */
    @Excel(name = "已支付金额")
    private Long gnPcpayment;

    /** 锁定金额 */
    @Excel(name = "锁定金额")
    private Long gnPcfixedprice;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private Long gnPcbalance;

    public void setGnId(Long gnId)
    {
        this.gnId = gnId;
    }

    public Long getGnId()
    {
        return gnId;
    }
    public void setGnSignatorycount(Long gnSignatorycount)
    {
        this.gnSignatorycount = gnSignatorycount;
    }

    public Long getGnSignatorycount()
    {
        return gnSignatorycount;
    }
    public void setGnSub(String gnSub)
    {
        this.gnSub = gnSub;
    }

    public String getGnSub()
    {
        return gnSub;
    }
    public void setGnPbid(Long gnPbid)
    {
        this.gnPbid = gnPbid;
    }

    public Long getGnPbid()
    {
        return gnPbid;
    }
    public void setGnPbname(String gnPbname)
    {
        this.gnPbname = gnPbname;
    }

    public String getGnPbname()
    {
        return gnPbname;
    }
    public void setGnPbaddress(String gnPbaddress)
    {
        this.gnPbaddress = gnPbaddress;
    }

    public String getGnPbaddress()
    {
        return gnPbaddress;
    }
    public void setGnPbcontact(String gnPbcontact)
    {
        this.gnPbcontact = gnPbcontact;
    }

    public String getGnPbcontact()
    {
        return gnPbcontact;
    }
    public void setGnPbcif(String gnPbcif)
    {
        this.gnPbcif = gnPbcif;
    }

    public String getGnPbcif()
    {
        return gnPbcif;
    }
    public void setGnPbbank(String gnPbbank)
    {
        this.gnPbbank = gnPbbank;
    }

    public String getGnPbbank()
    {
        return gnPbbank;
    }
    public void setGnPbaccount(String gnPbaccount)
    {
        this.gnPbaccount = gnPbaccount;
    }

    public String getGnPbaccount()
    {
        return gnPbaccount;
    }
    public void setGnPbamount(Long gnPbamount)
    {
        this.gnPbamount = gnPbamount;
    }

    public Long getGnPbamount()
    {
        return gnPbamount;
    }
    public void setGnPbcurrency(String gnPbcurrency)
    {
        this.gnPbcurrency = gnPbcurrency;
    }

    public String getGnPbcurrency()
    {
        return gnPbcurrency;
    }
    public void setGnPbpayment(Long gnPbpayment)
    {
        this.gnPbpayment = gnPbpayment;
    }

    public Long getGnPbpayment()
    {
        return gnPbpayment;
    }
    public void setGnPbfixedprice(Long gnPbfixedprice)
    {
        this.gnPbfixedprice = gnPbfixedprice;
    }

    public Long getGnPbfixedprice()
    {
        return gnPbfixedprice;
    }
    public void setGnPbbalance(Long gnPbbalance)
    {
        this.gnPbbalance = gnPbbalance;
    }

    public Long getGnPbbalance()
    {
        return gnPbbalance;
    }
    public void setGnPcid(Long gnPcid)
    {
        this.gnPcid = gnPcid;
    }

    public Long getGnPcid()
    {
        return gnPcid;
    }
    public void setGnPcname(String gnPcname)
    {
        this.gnPcname = gnPcname;
    }

    public String getGnPcname()
    {
        return gnPcname;
    }
    public void setGnPcaddress(String gnPcaddress)
    {
        this.gnPcaddress = gnPcaddress;
    }

    public String getGnPcaddress()
    {
        return gnPcaddress;
    }
    public void setGnPccontact(String gnPccontact)
    {
        this.gnPccontact = gnPccontact;
    }

    public String getGnPccontact()
    {
        return gnPccontact;
    }
    public void setGnPccif(String gnPccif)
    {
        this.gnPccif = gnPccif;
    }

    public String getGnPccif()
    {
        return gnPccif;
    }
    public void setGnPcbank(String gnPcbank)
    {
        this.gnPcbank = gnPcbank;
    }

    public String getGnPcbank()
    {
        return gnPcbank;
    }
    public void setGnPcaccount(String gnPcaccount)
    {
        this.gnPcaccount = gnPcaccount;
    }

    public String getGnPcaccount()
    {
        return gnPcaccount;
    }
    public void setGnPcamount(Long gnPcamount)
    {
        this.gnPcamount = gnPcamount;
    }

    public Long getGnPcamount()
    {
        return gnPcamount;
    }
    public void setGnPccurrency(String gnPccurrency)
    {
        this.gnPccurrency = gnPccurrency;
    }

    public String getGnPccurrency()
    {
        return gnPccurrency;
    }
    public void setGnPcpayment(Long gnPcpayment)
    {
        this.gnPcpayment = gnPcpayment;
    }

    public Long getGnPcpayment()
    {
        return gnPcpayment;
    }
    public void setGnPcfixedprice(Long gnPcfixedprice)
    {
        this.gnPcfixedprice = gnPcfixedprice;
    }

    public Long getGnPcfixedprice()
    {
        return gnPcfixedprice;
    }
    public void setGnPcbalance(Long gnPcbalance)
    {
        this.gnPcbalance = gnPcbalance;
    }

    public Long getGnPcbalance()
    {
        return gnPcbalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gnId", getGnId())
                .append("gnSignatorycount", getGnSignatorycount())
                .append("gnSub", getGnSub())
                .append("gnPbid", getGnPbid())
                .append("gnPbname", getGnPbname())
                .append("gnPbaddress", getGnPbaddress())
                .append("gnPbcontact", getGnPbcontact())
                .append("gnPbcif", getGnPbcif())
                .append("gnPbbank", getGnPbbank())
                .append("gnPbaccount", getGnPbaccount())
                .append("gnPbamount", getGnPbamount())
                .append("gnPbcurrency", getGnPbcurrency())
                .append("gnPbpayment", getGnPbpayment())
                .append("gnPbfixedprice", getGnPbfixedprice())
                .append("gnPbbalance", getGnPbbalance())
                .append("gnPcid", getGnPcid())
                .append("gnPcname", getGnPcname())
                .append("gnPcaddress", getGnPcaddress())
                .append("gnPccontact", getGnPccontact())
                .append("gnPccif", getGnPccif())
                .append("gnPcbank", getGnPcbank())
                .append("gnPcaccount", getGnPcaccount())
                .append("gnPcamount", getGnPcamount())
                .append("gnPccurrency", getGnPccurrency())
                .append("gnPcpayment", getGnPcpayment())
                .append("gnPcfixedprice", getGnPcfixedprice())
                .append("gnPcbalance", getGnPcbalance())
                .toString();
    }
}