package com.hh.procure;

import com.ruoyi.common.core.utils.DateUtils;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        System.out.println("date:" + DateUtils.parseDate(DateUtils.getTime()) + "\n dateNow:" + DateUtils.getNowDate());
    }
}
