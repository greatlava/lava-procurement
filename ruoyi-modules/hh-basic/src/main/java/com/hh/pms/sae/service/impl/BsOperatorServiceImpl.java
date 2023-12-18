package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.service.IBsOperatorService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsOperatorMapper;
import com.hh.pms.sae.domain.BsOperator;

import javax.annotation.Resource;

/**
 * 业务经办人信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsOperatorServiceImpl implements IBsOperatorService {
    @Resource
    private BsOperatorMapper bsOperatorMapper;

    /**
     * 查询业务经办人信息
     *
     * @param ywId 业务经办人信息主键
     * @return 业务经办人信息
     */
    @Override
    public BsOperator selectBsOperatorByYwId(Long ywId) {
        return bsOperatorMapper.selectBsOperatorByYwId(ywId);
    }

    @Override
    public BsOperator selectBsOperatorByHid(Long hid) {
        return bsOperatorMapper.selectBsOperatorByHid(hid);
    }

    /**
     * 查询业务经办人信息列表
     *
     * @param bsOperator 业务经办人信息
     * @return 业务经办人信息
     */
    @Override
    public List<BsOperator> selectBsOperatorList(BsOperator bsOperator) {
        return bsOperatorMapper.selectBsOperatorList(bsOperator);
    }

    /**
     * 新增业务经办人信息
     *
     * @param bsOperator 业务经办人信息
     * @return 结果
     */
    @Override
    public int insertBsOperator(BsOperator bsOperator) {
        return bsOperatorMapper.insertBsOperator(bsOperator);
    }

    /**
     * 修改业务经办人信息
     *
     * @param bsOperator 业务经办人信息
     * @return 结果
     */
    @Override
    public int updateBsOperator(BsOperator bsOperator) {
        return bsOperatorMapper.updateBsOperator(bsOperator);
    }

    /**
     * 批量删除业务经办人信息
     *
     * @param ywIds 需要删除的业务经办人信息主键
     * @return 结果
     */
    @Override
    public int deleteBsOperatorByYwIds(Long[] ywIds) {
        return bsOperatorMapper.deleteBsOperatorByYwIds(ywIds);
    }

    /**
     * 删除业务经办人信息信息
     *
     * @param ywId 业务经办人信息主键
     * @return 结果
     */
    @Override
    public int deleteBsOperatorByYwId(Long ywId) {
        return bsOperatorMapper.deleteBsOperatorByYwId(ywId);
    }
}
