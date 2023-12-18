package com.hh.pms.service;

import com.hh.pms.domain.NobidNonPro;
import com.hh.pms.domain.PpmProcurementPlan;
import com.ruoyi.system.api.domain.BidTender;

import java.util.List;

/**
 * 采购计划Service接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface IPpmProcurementPlanService 
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
     * 批量删除采购计划
     * 
     * @param aids 需要删除的采购计划主键集合
     * @return 结果
     */
    public int deletePpmProcurementPlanByAids(Integer[] aids);

    /**
     * 删除采购计划信息
     * 
     * @param aid 采购计划主键
     * @return 结果
     */
    public int deletePpmProcurementPlanByAid(Integer aid);

    /**
     * 根据id多表查询
     * @param aid
     * @return
     */
    public PpmProcurementPlan selectProcurementPlanByIdForThreeTables(Integer aid);

    public int insertTenders(BidTender bidTender);

    public List<PpmProcurementPlan> FindProcurementPlanBy(PpmProcurementPlan ppmProcurementPlan);

    public List<PpmProcurementPlan> selectePpmProcurementPlanAndComPubAttamentByAid(PpmProcurementPlan ppmProcurementPlan);

    public int insertNoBidPro(NobidNonPro nobidNonPro);
}
