package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.service.IBsExperienceService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsExperienceMapper;
import com.hh.pms.sae.domain.BsExperience;

import javax.annotation.Resource;

/**
 * 任职经历Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsExperienceServiceImpl implements IBsExperienceService {
    @Resource
    private BsExperienceMapper bsExperienceMapper;

    /**
     * 查询任职经历
     *
     * @param qid 任职经历主键
     * @return 任职经历
     */
    @Override
    public BsExperience selectBsExperienceByQid(Long qid) {
        return bsExperienceMapper.selectBsExperienceByQid(qid);
    }

    /**
     * 查询任职经历列表
     *
     * @param bsExperience 任职经历
     * @return 任职经历
     */
    @Override
    public List<BsExperience> selectBsExperienceList(BsExperience bsExperience) {
        return bsExperienceMapper.selectBsExperienceList(bsExperience);
    }

    /**
     * 新增任职经历
     *
     * @param bsExperience 任职经历
     * @return 结果
     */
    @Override
    public int insertBsExperience(BsExperience bsExperience) {
        return bsExperienceMapper.insertBsExperience(bsExperience);
    }

    /**
     * 修改任职经历
     *
     * @param bsExperience 任职经历
     * @return 结果
     */
    @Override
    public int updateBsExperience(BsExperience bsExperience) {
        return bsExperienceMapper.updateBsExperience(bsExperience);
    }

    /**
     * 批量删除任职经历
     *
     * @param qids 需要删除的任职经历主键
     * @return 结果
     */
    @Override
    public int deleteBsExperienceByQids(Long[] qids) {
        return bsExperienceMapper.deleteBsExperienceByQids(qids);
    }

    /**
     * 删除任职经历信息
     *
     * @param qid 任职经历主键
     * @return 结果
     */
    @Override
    public int deleteBsExperienceByQid(Long qid) {
        return bsExperienceMapper.deleteBsExperienceByQid(qid);
    }
}
