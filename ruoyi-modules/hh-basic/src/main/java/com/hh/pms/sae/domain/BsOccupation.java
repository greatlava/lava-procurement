package com.hh.pms.sae.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 职业对象 bs_occupation
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsOccupation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职业ID */
    private Long nid;

    /** 专家ID */
    @Excel(name = "专家ID")
    private Long jid;

    /** 职业资格证书 */
    @Excel(name = "职业资格证书")
    private String nCertificate;

    /** 取得时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "取得时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nAcquireDate;

    /** 职业证书编号 */
    @Excel(name = "职业证书编号")
    private String nCode;

    /** 职业颁发机构 */
    @Excel(name = "职业颁发机构")
    private String nInstitution;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nExpirationDate;

    public void setNid(Long nid) 
    {
        this.nid = nid;
    }

    public Long getNid() 
    {
        return nid;
    }
    public void setJid(Long jid) 
    {
        this.jid = jid;
    }

    public Long getJid() 
    {
        return jid;
    }
    public void setnCertificate(String nCertificate) 
    {
        this.nCertificate = nCertificate;
    }

    public String getnCertificate() 
    {
        return nCertificate;
    }
    public void setnAcquireDate(Date nAcquireDate) 
    {
        this.nAcquireDate = nAcquireDate;
    }

    public Date getnAcquireDate() 
    {
        return nAcquireDate;
    }
    public void setnCode(String nCode) 
    {
        this.nCode = nCode;
    }

    public String getnCode() 
    {
        return nCode;
    }
    public void setnInstitution(String nInstitution) 
    {
        this.nInstitution = nInstitution;
    }

    public String getnInstitution() 
    {
        return nInstitution;
    }
    public void setnExpirationDate(Date nExpirationDate) 
    {
        this.nExpirationDate = nExpirationDate;
    }

    public Date getnExpirationDate() 
    {
        return nExpirationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("nid", getNid())
            .append("jid", getJid())
            .append("nCertificate", getnCertificate())
            .append("nAcquireDate", getnAcquireDate())
            .append("nCode", getnCode())
            .append("nInstitution", getnInstitution())
            .append("nExpirationDate", getnExpirationDate())
            .toString();
    }
}
