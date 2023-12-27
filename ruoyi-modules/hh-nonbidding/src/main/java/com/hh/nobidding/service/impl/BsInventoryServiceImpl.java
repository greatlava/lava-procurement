package com.hh.nobidding.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.nobidding.mapper.BsInventoryMapper;
import com.hh.nobidding.domain.BsInventory;
import com.hh.nobidding.service.IBsInventoryService;

import javax.annotation.Resource;

/**
 * 产品表Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-25
 */
@Service
public class BsInventoryServiceImpl implements IBsInventoryService
{
    @Resource
    private BsInventoryMapper bsInventoryMapper;

    /**
     * 查询产品表
     *
     * @param inId 产品表主键
     * @return 产品表
     */
    @Override
    public BsInventory selectBsInventoryByInId(Long inId)
    {
        return bsInventoryMapper.selectBsInventoryByInId(inId);
    }

    /**
     * 查询产品表列表
     *
     * @param bsInventory 产品表
     * @return 产品表
     */
    @Override
    public List<BsInventory> selectBsInventoryList(BsInventory bsInventory)
    {
        return bsInventoryMapper.selectBsInventoryList(bsInventory);
    }

    /**
     * 新增产品表
     *
     * @param bsInventory 产品表
     * @return 结果
     */
    @Override
    public int insertBsInventory(BsInventory bsInventory)
    {
        return bsInventoryMapper.insertBsInventory(bsInventory);
    }

    /**
     * 修改产品表
     *
     * @param bsInventory 产品表
     * @return 结果
     */
    @Override
    public int updateBsInventory(BsInventory bsInventory)
    {
        return bsInventoryMapper.updateBsInventory(bsInventory);
    }

    /**
     * 批量删除产品表
     *
     * @param inIds 需要删除的产品表主键
     * @return 结果
     */
    @Override
    public int deleteBsInventoryByInIds(Long[] inIds)
    {
        return bsInventoryMapper.deleteBsInventoryByInIds(inIds);
    }

    /**
     * 删除产品表信息
     *
     * @param inId 产品表主键
     * @return 结果
     */
    @Override
    public int deleteBsInventoryByInId(Long inId)
    {
        return bsInventoryMapper.deleteBsInventoryByInId(inId);
    }
}
