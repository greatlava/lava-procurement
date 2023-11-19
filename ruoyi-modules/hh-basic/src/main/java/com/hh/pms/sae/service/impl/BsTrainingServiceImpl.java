package com.hh.pms.sae.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsTrainingMapper;
import com.hh.pms.sae.domain.BsTraining;
import com.hh.pms.sae.service.IBsTrainingService;

import javax.annotation.Resource;

/**
 * 培训Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsTrainingServiceImpl implements IBsTrainingService {
    @Resource
    private BsTrainingMapper bsTrainingMapper;

    /**
     * 查询培训
     *
     * @param lid 培训主键
     * @return 培训
     */
    @Override
    public BsTraining selectBsTrainingByLid(Long lid) {
        return bsTrainingMapper.selectBsTrainingByLid(lid);
    }

    /**
     * 查询培训列表
     *
     * @param bsTraining 培训
     * @return 培训
     */
    @Override
    public List<BsTraining> selectBsTrainingList(BsTraining bsTraining) {
        return bsTrainingMapper.selectBsTrainingList(bsTraining);
    }

    /**
     * 新增培训
     *
     * @param bsTraining 培训
     * @return 结果
     */
    @Override
    public int insertBsTraining(BsTraining bsTraining) {
        return bsTrainingMapper.insertBsTraining(bsTraining);
    }

    /**
     * 修改培训
     *
     * @param bsTraining 培训
     * @return 结果
     */
    @Override
    public int updateBsTraining(BsTraining bsTraining) {
        return bsTrainingMapper.updateBsTraining(bsTraining);
    }

    /**
     * 批量删除培训
     *
     * @param lids 需要删除的培训主键
     * @return 结果
     */
    @Override
    public int deleteBsTrainingByLids(Long[] lids) {
        return bsTrainingMapper.deleteBsTrainingByLids(lids);
    }

    /**
     * 删除培训信息
     *
     * @param lid 培训主键
     * @return 结果
     */
    @Override
    public int deleteBsTrainingByLid(Long lid) {
        return bsTrainingMapper.deleteBsTrainingByLid(lid);
    }
}
