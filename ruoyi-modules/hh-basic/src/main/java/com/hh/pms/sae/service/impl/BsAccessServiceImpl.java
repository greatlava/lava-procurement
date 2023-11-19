package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsAccess;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsAccessMapper;
import com.hh.pms.sae.service.IBsAccessService;

import javax.annotation.Resource;

/**
 * 供应商准入Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsAccessServiceImpl implements IBsAccessService {
    @Resource
    private BsAccessMapper bsAccessMapper;

    /**
     * 查询供应商准入
     *
     * @param zrId 供应商准入主键
     * @return 供应商准入
     */
    @Override
    public BsAccess selectBsAccessByZrId(Long zrId) {
        return bsAccessMapper.selectBsAccessByZrId(zrId);
    }

    /**
     * 查询供应商准入列表
     *
     * @param bsAccess 供应商准入
     * @return 供应商准入
     */
    @Override
    public List<BsAccess> selectBsAccessList(BsAccess bsAccess) {
        return bsAccessMapper.selectBsAccessList(bsAccess);
    }

    /**
     * 新增供应商准入
     *
     * @param bsAccess 供应商准入
     * @return 结果
     */
    @Override
    public int insertBsAccess(BsAccess bsAccess) {
        return bsAccessMapper.insertBsAccess(bsAccess);
    }

    /**
     * 修改供应商准入
     *
     * @param bsAccess 供应商准入
     * @return 结果
     */
    @Override
    public int updateBsAccess(BsAccess bsAccess) {
        return bsAccessMapper.updateBsAccess(bsAccess);
    }

    /**
     * 批量删除供应商准入
     *
     * @param zrIds 需要删除的供应商准入主键
     * @return 结果
     */
    @Override
    public int deleteBsAccessByZrIds(Long[] zrIds) {
        return bsAccessMapper.deleteBsAccessByZrIds(zrIds);
    }

    /**
     * 删除供应商准入信息
     *
     * @param zrId 供应商准入主键
     * @return 结果
     */
    @Override
    public int deleteBsAccessByZrId(Long zrId) {
        return bsAccessMapper.deleteBsAccessByZrId(zrId);
    }
}
