package com.hh.pms.cm.service.impl;

import java.util.List;

import com.hh.pms.cm.domain.BsInventory;
import com.hh.pms.cm.mapper.BsInventoryMapper;
import com.hh.pms.cm.service.IBsInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-15
 */
@Service
public class BsInventoryServiceImpl implements IBsInventoryService {
    @Resource
    private BsInventoryMapper bsInventoryMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param inId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public BsInventory selectBsInventoryByInId(Long inId) {
        return bsInventoryMapper.selectBsInventoryByInId(inId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param bsInventory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BsInventory> selectBsInventoryList(BsInventory bsInventory) {
        return bsInventoryMapper.selectBsInventoryList(bsInventory);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param bsInventory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBsInventory(BsInventory bsInventory) {
        return bsInventoryMapper.insertBsInventory(bsInventory);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param bsInventory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBsInventory(BsInventory bsInventory) {
        return bsInventoryMapper.updateBsInventory(bsInventory);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param inIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBsInventoryByInIds(Long[] inIds) {
        return bsInventoryMapper.deleteBsInventoryByInIds(inIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param inId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteBsInventoryByInId(Long inId) {
        return bsInventoryMapper.deleteBsInventoryByInId(inId);
    }

    //根据oid删除设备信息
    @Override
    public int deleteBsInventoryByOid(Long oid) {
        return bsInventoryMapper.deleteBsInventoryByOid(oid);
    }

    //根据eid删除设备信息
    @Override
    public int deleteBsInventoryByEid(Long eid) {
        return bsInventoryMapper.deleteBsInventoryByEid(eid);
    }
}
