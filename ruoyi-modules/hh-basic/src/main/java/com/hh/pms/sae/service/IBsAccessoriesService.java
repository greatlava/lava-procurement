package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsAccessories;

/**
 * 相关附件Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsAccessoriesService {
    /**
     * 查询相关附件
     *
     * @param fjId 相关附件主键
     * @return 相关附件
     */
    public BsAccessories selectBsAccessoriesByFjId(Long fjId);

    /**
     * 查询相关附件列表
     *
     * @param bsAccessories 相关附件
     * @return 相关附件集合
     */
    public List<BsAccessories> selectBsAccessoriesList(BsAccessories bsAccessories);

    /**
     * 新增相关附件
     *
     * @param bsAccessories 相关附件
     * @return 结果
     */
    public int insertBsAccessories(BsAccessories bsAccessories);

    /**
     * 修改相关附件
     *
     * @param bsAccessories 相关附件
     * @return 结果
     */
    public int updateBsAccessories(BsAccessories bsAccessories);

    /**
     * 批量删除相关附件
     *
     * @param fjIds 需要删除的相关附件主键集合
     * @return 结果
     */
    public int deleteBsAccessoriesByFjIds(Long[] fjIds);

    /**
     * 删除相关附件信息
     *
     * @param fjId 相关附件主键
     * @return 结果
     */
    public int deleteBsAccessoriesByFjId(Long fjId);
}
