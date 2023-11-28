package com.hh.pms.sae.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 相关附件对象 bs_accessories
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsAccessories extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    private Long fjId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String fjName;

    /**
     * 附件
     */
    @Excel(name = "附件")
    private String fjAnnex;

    @Excel(name = "供应商ID")
    private Long hid;

    public void setFjId(Long fjId) {
        this.fjId = fjId;
    }

    public Long getFjId() {
        return fjId;
    }

    public void setFjName(String fjName) {
        this.fjName = fjName;
    }

    public String getFjName() {
        return fjName;
    }

    public void setFjAnnex(String fjAnnex) {
        this.fjAnnex = fjAnnex;
    }

    public String getFjAnnex() {
        return fjAnnex;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fjId", getFjId())
                .append("fjName", getFjName())
                .append("fjAnnex", getFjAnnex())
                .toString();
    }
}
