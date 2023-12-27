package com.hh.pms.sae.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 供应商准入对象 bs_access
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsAccess extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 准入ID
     */
    private Long zrId;

    /**
     * 业务编号
     */
    @Excel(name = "业务编号")
    private String zrBnumber;

    /**
     * 发起人
     */
    @Excel(name = "发起人")
    private String zrPromoter;

    /**
     * 提交时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd kk:mm:ss")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date zrTime;

    private BsSupplier bsSupplier;

    public BsSupplier getBsSupplier() {
        return bsSupplier;
    }

    public void setBsSupplier(BsSupplier bsSupplier) {
        this.bsSupplier = bsSupplier;
    }

    public void setZrId(Long zrId) {
        this.zrId = zrId;
    }

    public Long getZrId() {
        return zrId;
    }

    public void setZrBnumber(String zrBnumber) {
        this.zrBnumber = zrBnumber;
    }

    public String getZrBnumber() {
        return zrBnumber;
    }

    public void setZrPromoter(String zrPromoter) {
        this.zrPromoter = zrPromoter;
    }

    public String getZrPromoter() {
        return zrPromoter;
    }

    public void setZrTime(Date zrTime) {
        this.zrTime = zrTime;
    }

    public Date getZrTime() {
        return zrTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("zrId", getZrId())
                .append("zrBnumber", getZrBnumber())
                .append("zrPromoter", getZrPromoter())
                .append("zrTime", getZrTime())
                .toString();
    }
}
