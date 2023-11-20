package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsTraining;

/**
 * 培训Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsTrainingMapper {
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
     * 删除培训
     *
     * @param lid 培训主键
     * @return 结果
     */
    public int deleteBsTrainingByLid(Long lid);

    /**
     * 批量删除培训
     *
     * @param lids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsTrainingByLids(Long[] lids);
}
