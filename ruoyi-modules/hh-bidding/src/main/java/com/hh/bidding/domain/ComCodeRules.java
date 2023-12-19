package com.hh.bidding.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 com_code_rules
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
public class ComCodeRules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 目标表单 */
    @Excel(name = "目标表单")
    private String targetForm;

    /** 编号前缀 */
    @Excel(name = "编号前缀")
    private String prefix;

    /** 流水号 */
    @Excel(name = "流水号")
    private Integer serialNumber;

    /** 步长 */
    @Excel(name = "步长")
    private Integer step;

    /** 编号规则 */
    @Excel(name = "编号规则")
    private String codeRules;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 年 */
    @Excel(name = "年")
    private String year;

    /** 月 */
    @Excel(name = "月")
    private String mouth;

    /** 日 */
    @Excel(name = "日")
    private String day;

    /** 时 */
    @Excel(name = "时")
    private String hour;

    /** 分 */
    @Excel(name = "分")
    private String minute;

    /** 秒 */
    @Excel(name = "秒")
    private String second;

    /** 最大尾数 */
    @Excel(name = "最大尾数")
    private String maxMantissa;

    public String getMaxMantissa() {
        return maxMantissa;
    }

    public void setMaxMantissa(String maxMantissa) {
        this.maxMantissa = maxMantissa;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTargetForm(String targetForm)
    {
        this.targetForm = targetForm;
    }

    public String getTargetForm()
    {
        return targetForm;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }
    public void setSerialNumber(Integer serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Integer getSerialNumber()
    {
        return serialNumber;
    }
    public void setStep(Integer step)
    {
        this.step = step;
    }

    public Integer getStep()
    {
        return step;
    }
    public void setCodeRules(String codeRules)
    {
        this.codeRules = codeRules;
    }

    public String getCodeRules()
    {
        return codeRules;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }
    public void setYear(String year)
    {
        this.year = year;
    }

    public String getYear()
    {
        return year;
    }
    public void setMouth(String mouth)
    {
        this.mouth = mouth;
    }

    public String getMouth()
    {
        return mouth;
    }
    public void setDay(String day)
    {
        this.day = day;
    }

    public String getDay()
    {
        return day;
    }
    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public String getHour()
    {
        return hour;
    }
    public void setMinute(String minute)
    {
        this.minute = minute;
    }

    public String getMinute()
    {
        return minute;
    }
    public void setSecond(String second)
    {
        this.second = second;
    }

    public String getSecond()
    {
        return second;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("targetForm", getTargetForm())
                .append("prefix", getPrefix())
                .append("serialNumber", getSerialNumber())
                .append("step", getStep())
                .append("codeRules", getCodeRules())
                .append("operator", getOperator())
                .append("updateTime", getUpdateTime())
                .append("year", getYear())
                .append("mouth", getMouth())
                .append("day", getDay())
                .append("hour", getHour())
                .append("minute", getMinute())
                .append("second", getSecond())
                .append("maxMantissa",getMaxMantissa())
                .toString();
    }
}
