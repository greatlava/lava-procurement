package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsAccessories;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsAccessoriesMapper;
import com.hh.pms.sae.service.IBsAccessoriesService;

import javax.annotation.Resource;

/**
 * 相关附件Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsAccessoriesServiceImpl implements IBsAccessoriesService {
    @Resource
    private BsAccessoriesMapper bsAccessoriesMapper;

    /**
     * 查询相关附件
     *
     * @param fjId 相关附件主键
     * @return 相关附件
     */
    @Override
    public BsAccessories selectBsAccessoriesByFjId(Long fjId) {
        return bsAccessoriesMapper.selectBsAccessoriesByFjId(fjId);
    }

    /**
     * 查询相关附件列表
     *
     * @param bsAccessories 相关附件
     * @return 相关附件
     */
    @Override
    public List<BsAccessories> selectBsAccessoriesList(BsAccessories bsAccessories) {
        return bsAccessoriesMapper.selectBsAccessoriesList(bsAccessories);
    }

    /**
     * 新增相关附件
     *
     * @param bsAccessories 相关附件
     * @return 结果
     */
    @Override
    public int insertBsAccessories(BsAccessories bsAccessories) {
        return bsAccessoriesMapper.insertBsAccessories(bsAccessories);
    }

    /**
     * 修改相关附件
     *
     * @param bsAccessories 相关附件
     * @return 结果
     */
    @Override
    public int updateBsAccessories(BsAccessories bsAccessories) {
        return bsAccessoriesMapper.updateBsAccessories(bsAccessories);
    }

    /**
     * 批量删除相关附件
     *
     * @param fjIds 需要删除的相关附件主键
     * @return 结果
     */
    @Override
    public int deleteBsAccessoriesByFjIds(Long[] fjIds) {
        return bsAccessoriesMapper.deleteBsAccessoriesByFjIds(fjIds);
    }

    /**
     * 删除相关附件信息
     *
     * @param fjId 相关附件主键
     * @return 结果
     */
    @Override
    public int deleteBsAccessoriesByFjId(Long fjId) {
        return bsAccessoriesMapper.deleteBsAccessoriesByFjId(fjId);
    }
}
