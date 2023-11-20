package com.hh.pms.sae.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsSupplierMapper;
import com.hh.pms.sae.domain.BsSupplier;
import com.hh.pms.sae.service.IBsSupplierService;

import javax.annotation.Resource;

/**
 * 供应商Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsSupplierServiceImpl implements IBsSupplierService {
    @Resource
    private BsSupplierMapper bsSupplierMapper;

    /**
     * 查询供应商
     *
     * @param hid 供应商主键
     * @return 供应商
     */
    @Override
    public BsSupplier selectBsSupplierByHid(Long hid) {
        return bsSupplierMapper.selectBsSupplierByHid(hid);
    }

    /**
     * 查询供应商列表
     *
     * @param bsSupplier 供应商
     * @return 供应商
     */
    @Override
    public List<BsSupplier> selectBsSupplierList(BsSupplier bsSupplier) {
        return bsSupplierMapper.selectBsSupplierList(bsSupplier);
    }

    /**
     * 新增供应商
     *
     * @param bsSupplier 供应商
     * @return 结果
     */
    @Override
    public int insertBsSupplier(BsSupplier bsSupplier) {
        return bsSupplierMapper.insertBsSupplier(bsSupplier);
    }

    /**
     * 修改供应商
     *
     * @param bsSupplier 供应商
     * @return 结果
     */
    @Override
    public int updateBsSupplier(BsSupplier bsSupplier) {
        return bsSupplierMapper.updateBsSupplier(bsSupplier);
    }

    /**
     * 批量删除供应商
     *
     * @param hids 需要删除的供应商主键
     * @return 结果
     */
    @Override
    public int deleteBsSupplierByHids(Long[] hids) {
        return bsSupplierMapper.deleteBsSupplierByHids(hids);
    }

    /**
     * 删除供应商信息
     *
     * @param hid 供应商主键
     * @return 结果
     */
    @Override
    public int deleteBsSupplierByHid(Long hid) {
        return bsSupplierMapper.deleteBsSupplierByHid(hid);
    }
}
