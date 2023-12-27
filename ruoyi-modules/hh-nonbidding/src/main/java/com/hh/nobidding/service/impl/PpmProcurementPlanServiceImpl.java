package com.hh.nobidding.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.nobidding.mapper.ProcurementPlanMapper;
import com.hh.nobidding.domain.PpmProcurementPlan;
import com.hh.nobidding.service.IPpmProcurementPlanService;

import javax.annotation.Resource;

/**
 * 采购计划Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-26
 */
@Service
public class PpmProcurementPlanServiceImpl implements IPpmProcurementPlanService
{
    @Resource
    private ProcurementPlanMapper procurementPlanMapper;

    /**
     * 查询采购计划
     *
     * @param aid 采购计划主键
     * @return 采购计划
     */
    @Override
    public PpmProcurementPlan selectPpmProcurementPlanByAid(Long aid)
    {
        return procurementPlanMapper.selectPpmProcurementPlanByAid(aid);
    }

    /**
     * 查询采购计划列表
     *
     * @param ppmProcurementPlan 采购计划
     * @return 采购计划
     */
    @Override
    public List<PpmProcurementPlan> selectPpmProcurementPlanList(PpmProcurementPlan ppmProcurementPlan)
    {
        return procurementPlanMapper.selectPpmProcurementPlanList(ppmProcurementPlan);
    }

    /**
     * 新增采购计划
     *
     * @param ppmProcurementPlan 采购计划
     * @return 结果
     */
    @Override
    public int insertPpmProcurementPlan(PpmProcurementPlan ppmProcurementPlan)
    {
        ppmProcurementPlan.setCreateTime(DateUtils.getNowDate());
        return procurementPlanMapper.insertPpmProcurementPlan(ppmProcurementPlan);
    }

    /**
     * 修改采购计划
     *
     * @param ppmProcurementPlan 采购计划
     * @return 结果
     */
    @Override
    public int updatePpmProcurementPlan(PpmProcurementPlan ppmProcurementPlan)
    {
        ppmProcurementPlan.setUpdateTime(DateUtils.getNowDate());
        return procurementPlanMapper.updatePpmProcurementPlan(ppmProcurementPlan);
    }

    /**
     * 批量删除采购计划
     *
     * @param aids 需要删除的采购计划主键
     * @return 结果
     */
    @Override
    public int deletePpmProcurementPlanByAids(Long[] aids)
    {
        return procurementPlanMapper.deletePpmProcurementPlanByAids(aids);
    }

    /**
     * 删除采购计划信息
     *
     * @param aid 采购计划主键
     * @return 结果
     */
    @Override
    public int deletePpmProcurementPlanByAid(Long aid)
    {
        return procurementPlanMapper.deletePpmProcurementPlanByAid(aid);
    }
}
