package com.hh.procure.mapper;

import com.hh.procure.domain.PpmFramePlan;

import java.util.List;

/**
 * 框架计划Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface PpmFramePlanMapper 
{
    /**
     * 查询框架计划
     * 
     * @param jhId 框架计划主键
     * @return 框架计划
     */
    public PpmFramePlan selectPpmFramePlanByJhId(Long jhId);

    /**
     * 查询框架计划列表
     * 
     * @param ppmFramePlan 框架计划
     * @return 框架计划集合
     */
    public List<PpmFramePlan> selectPpmFramePlanList(PpmFramePlan ppmFramePlan);

    /**
     * 新增框架计划
     * 
     * @param ppmFramePlan 框架计划
     * @return 结果
     */
    public int insertPpmFramePlan(PpmFramePlan ppmFramePlan);

    /**
     * 修改框架计划
     * 
     * @param ppmFramePlan 框架计划
     * @return 结果
     */
    public int updatePpmFramePlan(PpmFramePlan ppmFramePlan);

    /**
     * 删除框架计划
     * 
     * @param jhId 框架计划主键
     * @return 结果
     */
    public int deletePpmFramePlanByJhId(Integer jhId);

    /**
     * 批量删除框架计划
     * 
     * @param jhIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePpmFramePlanByJhIds(Long[] jhIds);


   public PpmFramePlan queryFrameworkPlanAndRelatedInformation(Integer jhId);


   public int updateFarmeworkPlanStatusByJhId(PpmFramePlan ppmFramePlan);

   public int selectFarmeworkPlanCount();

    /**
     * 查询已完成并且未创建框架协议的框架计划
     *
     * @param ppmFramePlan 框架计划
     * @return 框架计划集合
     */
    public List<PpmFramePlan> selectBsFramePlanList(PpmFramePlan ppmFramePlan);

    //(协议作废)修改oid为空
    public int updatePpmFramePlanbyOid(Long oid);

    //框架协议新增后修改框架计划oid
    public int updateOidbyOid(PpmFramePlan ppmFramePlan);
}
