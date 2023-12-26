package com.hh.nobidding.mapper;

import java.util.List;
import com.hh.nobidding.domain.PpmProcurementPlan;

/**
 * 采购计划Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-26
 */
public interface ProcurementPlanMapper
{
    /**
     * 查询采购计划
     *
     * @param aid 采购计划主键
     * @return 采购计划
     */
    public PpmProcurementPlan selectPpmProcurementPlanByAid(Long aid);

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
    public int deletePpmProcurementPlanByAid(Long aid);

    /**
     * 批量删除采购计划
     *
     * @param aids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePpmProcurementPlanByAids(Long[] aids);
}
