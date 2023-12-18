package com.hh.procure.Util;

import com.hh.procure.domain.CodeRulesResult;
import com.hh.procure.domain.ComCodeRules;

import java.util.Calendar;

public class CodeRuleHelp {

    public static CodeRulesResult GetCodeRule(ComCodeRules comCodeRules) {
        String maxMantissa = comCodeRules.getMaxMantissa();
        int num = Integer.parseInt(maxMantissa);
        num++;
        String reslut = String.format("%0" + comCodeRules.getSerialNumber() + "d", num);
        return computingTime(comCodeRules, reslut);
    }

    public static CodeRulesResult computingTime(ComCodeRules comCodeRules, String maxNumber) {
        String prefix = comCodeRules.getPrefix();
        Calendar calendar = Calendar.getInstance();
        if (!"".equals(comCodeRules.getYear()) && comCodeRules.getYear() != null) {
            prefix += calendar.get(Calendar.YEAR);
        }
        if (!"".equals(comCodeRules.getMouth()) && comCodeRules.getMouth() != null) {
            prefix += calendar.get(Calendar.MONTH) + 1;
        }
        if (!"".equals(comCodeRules.getDay()) && comCodeRules.getDay() != null) {
            prefix += calendar.get(Calendar.DAY_OF_MONTH);
        }
        if (!"".equals(comCodeRules.getHour()) && comCodeRules.getHour() != null) {
            prefix += calendar.get(Calendar.HOUR_OF_DAY);
        }
        if (!"".equals(comCodeRules.getMinute()) && comCodeRules.getMinute() != null) {
            prefix += calendar.get(Calendar.MINUTE);
        }
        if (!"".equals(comCodeRules.getSecond()) && comCodeRules.getSecond() != null) {
            prefix += calendar.get(Calendar.SECOND);
        }
        prefix += maxNumber;
        CodeRulesResult codeRulesResult = new CodeRulesResult();
        codeRulesResult.setCode(prefix);
        codeRulesResult.setMax(maxNumber);
        return codeRulesResult;
    }


}
