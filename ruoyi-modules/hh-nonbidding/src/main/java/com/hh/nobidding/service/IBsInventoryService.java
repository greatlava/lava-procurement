package com.hh.nobidding.service;

import java.util.List;
import com.hh.nobidding.domain.BsInventory;

/**
 * 产品表Service接口
 *
 * @author ruoyi
 * @date 2023-12-25
 */
public interface IBsInventoryService
{
    /**
     * 查询产品表
     *
     * @param inId 产品表主键
     * @return 产品表
     */
    public BsInventory selectBsInventoryByInId(Long inId);

    /**
     * 查询产品表列表
     *
     * @param bsInventory 产品表
     * @return 产品表集合
     */
    public List<BsInventory> selectBsInventoryList(BsInventory bsInventory);

    /**
     * 新增产品表
     *
     * @param bsInventory 产品表
     * @return 结果
     */
    public int insertBsInventory(BsInventory bsInventory);

    /**
     * 修改产品表
     *
     * @param bsInventory 产品表
     * @return 结果
     */
    public int updateBsInventory(BsInventory bsInventory);

    /**
     * 批量删除产品表
     *
     * @param inIds 需要删除的产品表主键集合
     * @return 结果
     */
    public int deleteBsInventoryByInIds(Long[] inIds);

    /**
     * 删除产品表信息
     *
     * @param inId 产品表主键
     * @return 结果
     */
    public int deleteBsInventoryByInId(Long inId);
}
