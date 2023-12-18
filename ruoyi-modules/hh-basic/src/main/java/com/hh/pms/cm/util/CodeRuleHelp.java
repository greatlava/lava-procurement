package com.hh.pms.cm.util;



import com.hh.pms.cm.domain.CodeRulesResult;
import com.hh.pms.cm.domain.ComCodeRules;
import com.hh.pms.cm.service.IComCodeRulesService;

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

    public static CodeRulesResult createCode(IComCodeRulesService codeRulesService,String formName){
        //根据目标表单查询当前规则
        ComCodeRules codeRules = codeRulesService.selectComCodeRulesByTargetForm(formName);
        //匹配规则
        CodeRulesResult result = CodeRuleHelp.GetCodeRule(codeRules);
        //设置最大尾数
        codeRules.setMaxMantissa(result.getMax());
        //修改
        codeRulesService.updateComCodeRules(codeRules);

        return  result;
    }
}
