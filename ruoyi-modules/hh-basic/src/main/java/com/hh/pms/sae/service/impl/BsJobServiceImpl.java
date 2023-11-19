package com.hh.pms.sae.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsJobMapper;
import com.hh.pms.sae.domain.BsJob;
import com.hh.pms.sae.service.IBsJobService;

import javax.annotation.Resource;

/**
 * 职称Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsJobServiceImpl implements IBsJobService {
    @Resource
    private BsJobMapper bsJobMapper;

    /**
     * 查询职称
     *
     * @param mid 职称主键
     * @return 职称
     */
    @Override
    public BsJob selectBsJobByMid(Long mid) {
        return bsJobMapper.selectBsJobByMid(mid);
    }

    /**
     * 查询职称列表
     *
     * @param bsJob 职称
     * @return 职称
     */
    @Override
    public List<BsJob> selectBsJobList(BsJob bsJob) {
        return bsJobMapper.selectBsJobList(bsJob);
    }

    /**
     * 新增职称
     *
     * @param bsJob 职称
     * @return 结果
     */
    @Override
    public int insertBsJob(BsJob bsJob) {
        return bsJobMapper.insertBsJob(bsJob);
    }

    /**
     * 修改职称
     *
     * @param bsJob 职称
     * @return 结果
     */
    @Override
    public int updateBsJob(BsJob bsJob) {
        return bsJobMapper.updateBsJob(bsJob);
    }

    /**
     * 批量删除职称
     *
     * @param mids 需要删除的职称主键
     * @return 结果
     */
    @Override
    public int deleteBsJobByMids(Long[] mids) {
        return bsJobMapper.deleteBsJobByMids(mids);
    }

    /**
     * 删除职称信息
     *
     * @param mid 职称主键
     * @return 结果
     */
    @Override
    public int deleteBsJobByMid(Long mid) {
        return bsJobMapper.deleteBsJobByMid(mid);
    }
}
