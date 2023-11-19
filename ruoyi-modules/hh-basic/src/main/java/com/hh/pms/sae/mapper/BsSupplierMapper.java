package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsSupplier;

/**
 * 供应商Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsSupplierMapper {
    /**
     * 查询供应商
     *
     * @param hid 供应商主键
     * @return 供应商
     */
    public BsSupplier selectBsSupplierByHid(Long hid);

    /**
     * 查询供应商列表
     *
     * @param bsSupplier 供应商
     * @return 供应商集合
     */
    public List<BsSupplier> selectBsSupplierList(BsSupplier bsSupplier);

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
     * 删除供应商
     *
     * @param hid 供应商主键
     * @return 结果
     */
    public int deleteBsSupplierByHid(Long hid);

    /**
     * 批量删除供应商
     *
     * @param hids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsSupplierByHids(Long[] hids);
}
