package com.hh.bidding.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务经办人信息对象 bs_operator
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsOperator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 经办人ID */
    private Long ywId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long hid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String ywName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String ywPhone;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String ywIdcrad;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String ywMailbox;

    /** 身份证扫描件 */
    @Excel(name = "身份证扫描件")
    private String ywScanIdcard;

    /** 授权书扫描件 */
    @Excel(name = "授权书扫描件")
    private String ywScanEmpower;

    /** 座机 */
    @Excel(name = "座机")
    private String ywLandline;

    /** 授权书内容 */
    @Excel(name = "授权书内容")
    private String ywEmpowerContent;

    public void setYwId(Long ywId) 
    {
        this.ywId = ywId;
    }

    public Long getYwId() 
    {
        return ywId;
    }
    public void setHid(Long hid) 
    {
        this.hid = hid;
    }

    public Long getHid() 
    {
        return hid;
    }
    public void setYwName(String ywName) 
    {
        this.ywName = ywName;
    }

    public String getYwName() 
    {
        return ywName;
    }
    public void setYwPhone(String ywPhone) 
    {
        this.ywPhone = ywPhone;
    }

    public String getYwPhone() 
    {
        return ywPhone;
    }
    public void setYwIdcrad(String ywIdcrad) 
    {
        this.ywIdcrad = ywIdcrad;
    }

    public String getYwIdcrad() 
    {
        return ywIdcrad;
    }
    public void setYwMailbox(String ywMailbox) 
    {
        this.ywMailbox = ywMailbox;
    }

    public String getYwMailbox() 
    {
        return ywMailbox;
    }
    public void setYwScanIdcard(String ywScanIdcard) 
    {
        this.ywScanIdcard = ywScanIdcard;
    }

    public String getYwScanIdcard() 
    {
        return ywScanIdcard;
    }
    public void setYwScanEmpower(String ywScanEmpower) 
    {
        this.ywScanEmpower = ywScanEmpower;
    }

    public String getYwScanEmpower() 
    {
        return ywScanEmpower;
    }
    public void setYwLandline(String ywLandline) 
    {
        this.ywLandline = ywLandline;
    }

    public String getYwLandline() 
    {
        return ywLandline;
    }
    public void setYwEmpowerContent(String ywEmpowerContent) 
    {
        this.ywEmpowerContent = ywEmpowerContent;
    }

    public String getYwEmpowerContent() 
    {
        return ywEmpowerContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ywId", getYwId())
            .append("hid", getHid())
            .append("ywName", getYwName())
            .append("ywPhone", getYwPhone())
            .append("ywIdcrad", getYwIdcrad())
            .append("ywMailbox", getYwMailbox())
            .append("ywScanIdcard", getYwScanIdcard())
            .append("ywScanEmpower", getYwScanEmpower())
            .append("ywLandline", getYwLandline())
            .append("ywEmpowerContent", getYwEmpowerContent())
            .toString();
    }
}
