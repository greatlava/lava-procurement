package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsAchievement;

/**
 * 业绩Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsAchievementService {
    /**
     * 查询业绩
     *
     * @param yjId 业绩主键
     * @return 业绩
     */
    public BsAchievement selectBsAchievementByYjId(Long yjId);

    /**
     * 查询业绩列表
     *
     * @param bsAchievement 业绩
     * @return 业绩集合
     */
    public List<BsAchievement> selectBsAchievementList(BsAchievement bsAchievement);

    /**
     * 新增业绩
     *
     * @param bsAchievement 业绩
     * @return 结果
     */
    public int insertBsAchievement(BsAchievement bsAchievement);

    /**
     * 修改业绩
     *
     * @param bsAchievement 业绩
     * @return 结果
     */
    public int updateBsAchievement(BsAchievement bsAchievement);

    /**
     * 批量删除业绩
     *
     * @param yjIds 需要删除的业绩主键集合
     * @return 结果
     */
    public int deleteBsAchievementByYjIds(Long[] yjIds);

    /**
     * 删除业绩信息
     *
     * @param yjId 业绩主键
     * @return 结果
     */
    public int deleteBsAchievementByYjId(Long yjId);
}
