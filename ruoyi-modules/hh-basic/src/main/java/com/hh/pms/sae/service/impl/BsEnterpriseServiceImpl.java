package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsEnterprise;
import com.hh.pms.sae.service.IBsEnterpriseService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsEnterpriseMapper;

import javax.annotation.Resource;

/**
 * 企业资质Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsEnterpriseServiceImpl implements IBsEnterpriseService {
    @Resource
    private BsEnterpriseMapper bsEnterpriseMapper;

    /**
     * 查询企业资质
     *
     * @param zzId 企业资质主键
     * @return 企业资质
     */
    @Override
    public BsEnterprise selectBsEnterpriseByZzId(Long zzId) {
        return bsEnterpriseMapper.selectBsEnterpriseByZzId(zzId);
    }

    /**
     * 查询企业资质列表
     *
     * @param bsEnterprise 企业资质
     * @return 企业资质
     */
    @Override
    public List<BsEnterprise> selectBsEnterpriseList(BsEnterprise bsEnterprise) {
        return bsEnterpriseMapper.selectBsEnterpriseList(bsEnterprise);
    }

    /**
     * 新增企业资质
     *
     * @param bsEnterprise 企业资质
     * @return 结果
     */
    @Override
    public int insertBsEnterprise(BsEnterprise bsEnterprise) {
        return bsEnterpriseMapper.insertBsEnterprise(bsEnterprise);
    }

    /**
     * 修改企业资质
     *
     * @param bsEnterprise 企业资质
     * @return 结果
     */
    @Override
    public int updateBsEnterprise(BsEnterprise bsEnterprise) {
        return bsEnterpriseMapper.updateBsEnterprise(bsEnterprise);
    }

    /**
     * 批量删除企业资质
     *
     * @param zzIds 需要删除的企业资质主键
     * @return 结果
     */
    @Override
    public int deleteBsEnterpriseByZzIds(Long[] zzIds) {
        return bsEnterpriseMapper.deleteBsEnterpriseByZzIds(zzIds);
    }

    /**
     * 删除企业资质信息
     *
     * @param zzId 企业资质主键
     * @return 结果
     */
    @Override
    public int deleteBsEnterpriseByZzId(Long zzId) {
        return bsEnterpriseMapper.deleteBsEnterpriseByZzId(zzId);
    }
}
