package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsAchievement;
import com.hh.pms.sae.service.IBsAchievementService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsAchievementMapper;

import javax.annotation.Resource;

/**
 * 业绩Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsAchievementServiceImpl implements IBsAchievementService {
    @Resource
    private BsAchievementMapper bsAchievementMapper;

    /**
     * 查询业绩
     *
     * @param yjId 业绩主键
     * @return 业绩
     */
    @Override
    public BsAchievement selectBsAchievementByYjId(Long yjId) {
        return bsAchievementMapper.selectBsAchievementByYjId(yjId);
    }

    /**
     * 查询业绩列表
     *
     * @param bsAchievement 业绩
     * @return 业绩
     */
    @Override
    public List<BsAchievement> selectBsAchievementList(BsAchievement bsAchievement) {
        return bsAchievementMapper.selectBsAchievementList(bsAchievement);
    }

    /**
     * 新增业绩
     *
     * @param bsAchievement 业绩
     * @return 结果
     */
    @Override
    public int insertBsAchievement(BsAchievement bsAchievement) {
        return bsAchievementMapper.insertBsAchievement(bsAchievement);
    }

    /**
     * 修改业绩
     *
     * @param bsAchievement 业绩
     * @return 结果
     */
    @Override
    public int updateBsAchievement(BsAchievement bsAchievement) {
        return bsAchievementMapper.updateBsAchievement(bsAchievement);
    }

    /**
     * 批量删除业绩
     *
     * @param yjIds 需要删除的业绩主键
     * @return 结果
     */
    @Override
    public int deleteBsAchievementByYjIds(Long[] yjIds) {
        return bsAchievementMapper.deleteBsAchievementByYjIds(yjIds);
    }

    /**
     * 删除业绩信息
     *
     * @param yjId 业绩主键
     * @return 结果
     */
    @Override
    public int deleteBsAchievementByYjId(Long yjId) {
        return bsAchievementMapper.deleteBsAchievementByYjId(yjId);
    }
}
