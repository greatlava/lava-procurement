package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsJob;

/**
 * 职称Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsJobService {
    /**
     * 查询职称
     *
     * @param mid 职称主键
     * @return 职称
     */
    public BsJob selectBsJobByMid(Long mid);

    /**
     * 查询职称列表
     *
     * @param bsJob 职称
     * @return 职称集合
     */
    public List<BsJob> selectBsJobList(BsJob bsJob);

    /**
     * 新增职称
     *
     * @param bsJob 职称
     * @return 结果
     */
    public int insertBsJob(BsJob bsJob);

    /**
     * 修改职称
     *
     * @param bsJob 职称
     * @return 结果
     */
    public int updateBsJob(BsJob bsJob);

    /**
     * 批量删除职称
     *
     * @param mids 需要删除的职称主键集合
     * @return 结果
     */
    public int deleteBsJobByMids(Long[] mids);

    /**
     * 删除职称信息
     *
     * @param mid 职称主键
     * @return 结果
     */
    public int deleteBsJobByMid(Long mid);
}
