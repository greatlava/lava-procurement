package com.hh.pms.service;

import com.hh.pms.domain.PpmBudget;

import java.util.List;

/**
 * 预算Service接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface IPpmBudgetService 
{
    /**
     * 查询预算
     * 
     * @param duId 预算主键
     * @return 预算
     */
    public PpmBudget selectPpmBudgetByDuId(String duId);

    /**
     * 查询预算列表
     * 
     * @param ppmBudget 预算
     * @return 预算集合
     */
    public List<PpmBudget> selectPpmBudgetList(PpmBudget ppmBudget);

    /**
     * 新增预算
     * 
     * @param ppmBudget 预算
     * @return 结果
     */
    public int insertPpmBudget(PpmBudget ppmBudget);

    /**
     * 修改预算
     * 
     * @param ppmBudget 预算
     * @return 结果
     */
    public int updatePpmBudget(PpmBudget ppmBudget);

    /**
     * 批量删除预算
     * 
     * @param duIds 需要删除的预算主键集合
     * @return 结果
     */
    public int deletePpmBudgetByDuIds(String[] duIds);

    /**
     * 删除预算信息
     * 
     * @param duId 预算主键
     * @return 结果
     */
    public int deletePpmBudgetByDuId(String duId);

    public List<PpmBudget> selectPpmBudgetByAid(String aid);
}
