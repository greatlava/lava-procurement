package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmProcurementPlan;
import com.hh.pms.mapper.PpmProcurementPlanMapper;
import com.hh.pms.service.IPpmProcurementPlanService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
@Service
public class PpmProcurementPlanServiceImpl implements IPpmProcurementPlanService
{
    @Autowired
    private PpmProcurementPlanMapper ppmProcurementPlanMapper;

    /**
     * 查询采购计划
     * 
     * @param aid 采购计划主键
     * @return 采购计划
     */
    @Override
    public PpmProcurementPlan selectPpmProcurementPlanByAid(Integer aid)
    {
        return ppmProcurementPlanMapper.selectPpmProcurementPlanByAid(aid);
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
        return ppmProcurementPlanMapper.selectPpmProcurementPlanList(ppmProcurementPlan);
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
        return ppmProcurementPlanMapper.insertPpmProcurementPlan(ppmProcurementPlan);
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
        return ppmProcurementPlanMapper.updatePpmProcurementPlan(ppmProcurementPlan);
    }

    /**
     * 批量删除采购计划
     * 
     * @param aids 需要删除的采购计划主键
     * @return 结果
     */
    @Override
    public int deletePpmProcurementPlanByAids(Integer[] aids)
    {
        return ppmProcurementPlanMapper.deletePpmProcurementPlanByAids(aids);
    }

    /**
     * 删除采购计划信息
     * 
     * @param aid 采购计划主键
     * @return 结果
     */
    @Override
    public int deletePpmProcurementPlanByAid(Integer aid)
    {
        return ppmProcurementPlanMapper.deletePpmProcurementPlanByAid(aid);
    }
}
