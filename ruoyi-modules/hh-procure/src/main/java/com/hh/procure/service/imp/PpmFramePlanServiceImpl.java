package com.hh.procure.service.imp;

import com.hh.procure.domain.PpmFramePlan;
import com.hh.procure.mapper.PpmFramePlanMapper;
import com.hh.procure.service.IPpmFramePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 框架计划Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class PpmFramePlanServiceImpl implements IPpmFramePlanService {
    @Resource
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

    @Override
    public int selectFarmeworkPlanCount() {
        return ppmFramePlanMapper.selectFarmeworkPlanCount();
    }

    //查询已完成并且未创建框架协议的框架计划
    @Override
    public List<PpmFramePlan> selectBsFramePlanList(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.selectBsFramePlanList(ppmFramePlan);
    }

    //(协议作废)修改oid为空
    @Override
    public int updatePpmFramePlanByOid(Long oid) {
        return ppmFramePlanMapper.updatePpmFramePlanbyOid(oid);
    }

    //框架协议新增后修改框架计划oid
    @Override
    public int updateOidbyOid(PpmFramePlan ppmFramePlan) {
        return ppmFramePlanMapper.updateOidbyOid(ppmFramePlan);
    }
}
