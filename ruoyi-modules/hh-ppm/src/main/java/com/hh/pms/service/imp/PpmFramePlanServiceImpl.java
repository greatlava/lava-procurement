package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmFramePlan;
import com.hh.pms.mapper.PpmFramePlanMapper;
import com.hh.pms.service.IPpmFramePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 框架计划Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class PpmFramePlanServiceImpl implements IPpmFramePlanService {
    @Autowired
    private PpmFramePlanMapper ppmFramePlanMapper;

    /**
     * 查询框架计划
     *
     * @param jhId 框架计划主键
     * @return 框架计划
     */
    @Override
    public PpmFramePlan selectPpmFramePlanByJhId(Long jhId) {
        return ppmFramePlanMapper.selectPpmFramePlanByJhId(jhId);
    }

    /**
     * 查询框架计划列表
     *
     * @param ppmFramePlan 框架计划
     * @return 框架计划
     */
    @Override
    public List<PpmFramePlan> selectPpmFramePlanList(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.selectPpmFramePlanList(ppmFramePlan);
    }

    /**
     * 新增框架计划
     *
     * @param ppmFramePlan 框架计划
     * @return 结果
     */
    @Override
    public int insertPpmFramePlan(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.insertPpmFramePlan(ppmFramePlan);
    }

    /**
     * 修改框架计划
     *
     * @param ppmFramePlan 框架计划
     * @return 结果
     */
    @Override
    public int updatePpmFramePlan(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.updatePpmFramePlan(ppmFramePlan);
    }

    /**
     * 批量删除框架计划
     *
     * @param jhIds 需要删除的框架计划主键
     * @return 结果
     */
    @Override
    public int deletePpmFramePlanByJhIds(Long[] jhIds) {
        return ppmFramePlanMapper.deletePpmFramePlanByJhIds(jhIds);
    }

    /**
     * 删除框架计划信息
     *
     * @param jhId 框架计划主键
     * @return 结果
     */
    @Override
    public int deletePpmFramePlanByJhId(Integer jhId) {
        return ppmFramePlanMapper.deletePpmFramePlanByJhId(jhId);
    }

    @Override
    public PpmFramePlan queryFrameworkPlanAndRelatedInformation(Integer jhId) {
        return ppmFramePlanMapper.queryFrameworkPlanAndRelatedInformation(jhId);
    }

    @Override
    public int updateFarmeworkPlanStatusByJhId(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.updateFarmeworkPlanStatusByJhId(ppmFramePlan);
    }
}
