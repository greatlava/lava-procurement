package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsExperience;

/**
 * 任职经历Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsExperienceService {
    /**
     * 查询任职经历
     *
     * @param qid 任职经历主键
     * @return 任职经历
     */
    public BsExperience selectBsExperienceByQid(Long qid);

    /**
     * 查询任职经历列表
     *
     * @param bsExperience 任职经历
     * @return 任职经历集合
     */
    public List<BsExperience> selectBsExperienceList(BsExperience bsExperience);

    /**
     * 新增任职经历
     *
     * @param bsExperience 任职经历
     * @return 结果
     */
    public int insertBsExperience(BsExperience bsExperience);

    /**
     * 修改任职经历
     *
     * @param bsExperience 任职经历
     * @return 结果
     */
    public int updateBsExperience(BsExperience bsExperience);

    /**
     * 批量删除任职经历
     *
     * @param qids 需要删除的任职经历主键集合
     * @return 结果
     */
    public int deleteBsExperienceByQids(Long[] qids);

    /**
     * 删除任职经历信息
     *
     * @param qid 任职经历主键
     * @return 结果
     */
    public int deleteBsExperienceByQid(Long qid);
}
