package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsAccess;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商准入Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Transactional
public interface IBsAccessService {
    /**
     * 查询供应商准入
     *
     * @param zrId 供应商准入主键
     * @return 供应商准入
     */
    public BsAccess selectBsAccessByZrId(Long zrId);

    /**
     * 查询供应商准入列表
     *
     * @param bsAccess 供应商准入
     * @return 供应商准入集合
     */
    public List<BsAccess> selectBsAccessList(BsAccess bsAccess);

    /**
     * 新增供应商准入
     *
     * @param bsAccess 供应商准入
     * @return 结果
     */
    public int insertBsAccess(BsAccess bsAccess);

    /**
     * 修改供应商准入
     *
     * @param bsAccess 供应商准入
     * @return 结果
     */
    public int updateBsAccess(BsAccess bsAccess);

    /**
     * 批量删除供应商准入
     *
     * @param zrIds 需要删除的供应商准入主键集合
     * @return 结果
     */
    public int deleteBsAccessByZrIds(Long[] zrIds);

    /**
     * 删除供应商准入信息
     *
     * @param zrId 供应商准入主键
     * @return 结果
     */
    public int deleteBsAccessByZrId(Long zrId);
}
