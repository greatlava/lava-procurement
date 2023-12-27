package com.hh.procure.mapper;

import com.hh.procure.domain.NobidNonPro;
import com.hh.procure.domain.PpmProcurementPlan;
import com.ruoyi.system.api.domain.BidTender;
import com.ruoyi.system.api.domain.BidWinningResults;

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

    /**
     * 采购计划根据采购id四表联查
     * @param aid
     * @return
     */
    public PpmProcurementPlan selectProcurementPlanByIdForThreeTables(Integer aid);

    /**
     * 新增招标项目
     * @param bidTender
     * @return
     */
    public int insertTenders(BidTender bidTender);

    /**
     * 根据条件查询采购计划
     * @param ppmProcurementPlan
     * @return
     */
    public List<PpmProcurementPlan> FindProcurementPlanBy(PpmProcurementPlan ppmProcurementPlan);

    /**
     * 查询采购计划和附件
     * @param ppmProcurementPlan
     * @return
     */
    public List<PpmProcurementPlan> selectePpmProcurementPlanAndComPubAttamentByAid(PpmProcurementPlan ppmProcurementPlan);

    /**
     * 新增非招标
     * @param nobidNonPro
     * @return
     */
    public int insertNoBidPro(NobidNonPro nobidNonPro);

    /**
     * 查询采购计划数量
     * @param state
     * @return
     */
    public int selectPpmpProcurementCount(PpmProcurementPlan state);

    /**
     * 查询合同数量
     * @return
     */
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
