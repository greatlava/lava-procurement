package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsOperator;

/**
 * 业务经办人信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsOperatorMapper {
    /**
     * 查询业务经办人信息
     *
     * @param ywId 业务经办人信息主键
     * @return 业务经办人信息
     */
    public BsOperator selectBsOperatorByYwId(Long ywId);

    public BsOperator selectBsOperatorByHid(Long hid);

    /**
     * 查询业务经办人信息列表
     *
     * @param bsOperator 业务经办人信息
     * @return 业务经办人信息集合
     */
    public List<BsOperator> selectBsOperatorList(BsOperator bsOperator);

    /**
     * 新增业务经办人信息
     *
     * @param bsOperator 业务经办人信息
     * @return 结果
     */
    public int insertBsOperator(BsOperator bsOperator);

    /**
     * 修改业务经办人信息
     *
     * @param bsOperator 业务经办人信息
     * @return 结果
     */
    public int updateBsOperator(BsOperator bsOperator);

    /**
     * 删除业务经办人信息
     *
     * @param ywId 业务经办人信息主键
     * @return 结果
     */
    public int deleteBsOperatorByYwId(Long ywId);

    /**
     * 批量删除业务经办人信息
     *
     * @param ywIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsOperatorByYwIds(Long[] ywIds);
}
