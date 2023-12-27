package com.hh.procure.service;

import com.hh.procure.domain.NobidNonPro;
import com.hh.procure.domain.PpmProcurementPlan;
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

    /**
     * 添加招标项目
     * @param bidTender
     * @return
     */
    public int insertTenders(BidTender bidTender);

    /**
     *
     * @param ppmProcurementPlan
     * @return
     */
    public List<PpmProcurementPlan> FindProcurementPlanBy(PpmProcurementPlan ppmProcurementPlan);

    /**
     *
     * @param ppmProcurementPlan
     * @return
     */
    public List<PpmProcurementPlan> selectePpmProcurementPlanAndComPubAttamentByAid(PpmProcurementPlan ppmProcurementPlan);

    public int insertNoBidPro(NobidNonPro nobidNonPro);

    public int selectPpmpProcurementCount(PpmProcurementPlan state);

    public int selectContractCount();

    /**
     * 查询采购计划总金额
     * @return
     */
    public Double queryTotalPurchaseAmount();

    /**
     * 查询招标项目数量
     * @return
     */
    public int selectTenderCount();

    /**
     * 查询招标阶段的项目
     * @param bidTender
     * @return
     */
    public List<BidTender> selectTenderByState(BidTender bidTender);


    public int selectTenderByStateCount(BidTender bidTender);
}
