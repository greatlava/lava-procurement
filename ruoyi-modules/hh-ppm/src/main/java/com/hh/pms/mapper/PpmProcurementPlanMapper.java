package com.hh.pms.mapper;

import com.hh.pms.domain.PpmProcurementPlan;

import java.util.List;

/**
 * 采购计划Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface PpmProcurementPlanMapper 
{
    /**
     * 查询采购计划
     * 
     * @param aid 采购计划主键
     * @return 采购计划
     */
    public PpmProcurementPlan selectPpmProcurementPlanByAid(Integer aid);

    /**
     * 查询采购计划列表
     * 
     * @param ppmProcurementPlan 采购计划
     * @return 采购计划集合
     */
    public List<PpmProcurementPlan> selectPpmProcurementPlanList(PpmProcurementPlan ppmProcurementPlan);

    /**
     * 新增采购计划
     * 
     * @param ppmProcurementPlan 采购计划
     * @return 结果
     */
    public int insertPpmProcurementPlan(PpmProcurementPlan ppmProcurementPlan);

    /**
     * 修改采购计划
     * 
     * @param ppmProcurementPlan 采购计划
     * @return 结果
     */
    public int updatePpmProcurementPlan(PpmProcurementPlan ppmProcurementPlan);

    /**
     * 删除采购计划
     * 
     * @param aid 采购计划主键
     * @return 结果
     */
    public int deletePpmProcurementPlanByAid(Integer aid);

    /**
     * 批量删除采购计划
     * 
     * @param aids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePpmProcurementPlanByAids(Integer[] aids);

    public PpmProcurementPlan selectProcurementPlanByIdForThreeTables(Integer aid);


}
