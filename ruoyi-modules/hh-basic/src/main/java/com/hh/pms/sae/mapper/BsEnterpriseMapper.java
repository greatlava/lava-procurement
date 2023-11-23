package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsEnterprise;

/**
 * 企业资质Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsEnterpriseMapper {
    /**
     * 查询企业资质
     *
     * @param zzId 企业资质主键
     * @return 企业资质
     */
    public BsEnterprise selectBsEnterpriseByZzId(Long zzId);

    /**
     * 查询企业资质列表
     *
     * @param bsEnterprise 企业资质
     * @return 企业资质集合
     */
    public List<BsEnterprise> selectBsEnterpriseList(BsEnterprise bsEnterprise);

    /**
     * 新增企业资质
     *
     * @param bsEnterprise 企业资质
     * @return 结果
     */
    public int insertBsEnterprise(BsEnterprise bsEnterprise);

    /**
     * 修改企业资质
     *
     * @param bsEnterprise 企业资质
     * @return 结果
     */
    public int updateBsEnterprise(BsEnterprise bsEnterprise);

    /**
     * 删除企业资质
     *
     * @param zzId 企业资质主键
     * @return 结果
     */
    public int deleteBsEnterpriseByZzId(Long zzId);

    /**
     * 批量删除企业资质
     *
     * @param zzIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsEnterpriseByZzIds(Long[] zzIds);
}
