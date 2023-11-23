package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsSupplier;

/**
 * 供应商Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsSupplierService {
    /**
     * 查询供应商
     *
     * @param hid 供应商主键
     * @return 供应商
     */
    public BsSupplier selectBsSupplierByHid(Long hid);

    /**
     * 查询合格供应商列表
     *
     * @param bsSupplier 供应商
     * @return 供应商集合
     */
    public List<BsSupplier> selectBsSupplierList(BsSupplier bsSupplier);

    /**
     * 查询不合格供应商列表
     *
     * @param bsSupplier 供应商
     * @return 供应商集合
     */
    public List<BsSupplier> selectNoBsSupplierList(BsSupplier bsSupplier);

    /**
     * 查询供应商不良记录列表
     *
     * @param bsSupplier 供应商
     * @return 供应商集合
     */
    public List<BsSupplier> selectBsSupplierBadList(BsSupplier bsSupplier);

    /**
     * 新增供应商
     *
     * @param bsSupplier 供应商
     * @return 结果
     */
    public int insertBsSupplier(BsSupplier bsSupplier);

    /**
     * 修改供应商
     *
     * @param bsSupplier 供应商
     * @return 结果
     */
    public int updateBsSupplier(BsSupplier bsSupplier);

    /**
     * 批量删除供应商
     *
     * @param hids 需要删除的供应商主键集合
     * @return 结果
     */
    public int deleteBsSupplierByHids(Long[] hids);

    /**
     * 删除供应商信息
     *
     * @param hid 供应商主键
     * @return 结果
     */
    public int deleteBsSupplierByHid(Long hid);
}
