package com.hh.pms.cm.mapper;

import com.hh.pms.cm.domain.BsInventory;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-15
 */
public interface BsInventoryMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param inId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public BsInventory selectBsInventoryByInId(Long inId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param bsInventory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BsInventory> selectBsInventoryList(BsInventory bsInventory);

    /**
     * 新增【请填写功能名称】
     *
     * @param bsInventory 【请填写功能名称】
     * @return 结果
     */
    public int insertBsInventory(BsInventory bsInventory);

    /**
     * 修改【请填写功能名称】
     *
     * @param bsInventory 【请填写功能名称】
     * @return 结果
     */
    public int updateBsInventory(BsInventory bsInventory);

    /**
     * 删除【请填写功能名称】
     *
     * @param inId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteBsInventoryByInId(Long inId);

    //根据oid删除设备信息
    public int deleteBsInventoryByOid(Long oid);

    //根据eid删除设备信息
    public int deleteBsInventoryByEid(Long eid);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param inIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsInventoryByInIds(Long[] inIds);
}

