package com.hh.pms.cm.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 员工对象 bs_employee
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public class BsEmployee extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    private Long pid;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String pName;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String pPassword;

    /**
     * 员工性别
     */
    @Excel(name = "员工性别")
    private Long pSex;

    /**
     * 员工出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "员工出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pBorn;

    /**
     * 员工昵称
     */
    @Excel(name = "员工昵称")
    private String pNick;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpName() {
        return pName;
    }

    public void setpPassword(String pPassword) {
        this.pPassword = pPassword;
    }

    public String getpPassword() {
        return pPassword;
    }

    public void setpSex(Long pSex) {
        this.pSex = pSex;
    }

    public Long getpSex() {
        return pSex;
    }

    public void setpBorn(Date pBorn) {
        this.pBorn = pBorn;
    }

    public Date getpBorn() {
        return pBorn;
    }

    public void setpNick(String pNick) {
        this.pNick = pNick;
    }

    public String getpNick() {
        return pNick;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pid", getPid())
                .append("pName", getpName())
                .append("pPassword", getpPassword())
                .append("pSex", getpSex())
                .append("pBorn", getpBorn())
                .append("pNick", getpNick())
                .toString();
    }
}
