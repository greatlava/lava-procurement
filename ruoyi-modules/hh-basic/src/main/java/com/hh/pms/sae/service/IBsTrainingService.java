package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsTraining;

/**
 * 培训Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsTrainingService {
    /**
     * 查询培训
     *
     * @param lid 培训主键
     * @return 培训
     */
    public BsTraining selectBsTrainingByLid(Long lid);

    /**
     * 查询培训列表
     *
     * @param bsTraining 培训
     * @return 培训集合
     */
    public List<BsTraining> selectBsTrainingList(BsTraining bsTraining);

    /**
     * 新增培训
     *
     * @param bsTraining 培训
     * @return 结果
     */
    public int insertBsTraining(BsTraining bsTraining);

    /**
     * 修改培训
     *
     * @param bsTraining 培训
     * @return 结果
     */
    public int updateBsTraining(BsTraining bsTraining);

    /**
     * 批量删除培训
     *
     * @param lids 需要删除的培训主键集合
     * @return 结果
     */
    public int deleteBsTrainingByLids(Long[] lids);

    /**
     * 删除培训信息
     *
     * @param lid 培训主键
     * @return 结果
     */
    public int deleteBsTrainingByLid(Long lid);
}
