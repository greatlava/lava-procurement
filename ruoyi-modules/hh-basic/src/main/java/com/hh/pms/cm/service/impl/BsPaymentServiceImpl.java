package com.hh.pms.cm.service.impl;

import com.hh.pms.cm.domain.BsPayment;
import com.hh.pms.cm.mapper.BsPaymentMapper;
import com.hh.pms.cm.service.IBsPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付约定Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-30
 */
@Service
public class BsPaymentServiceImpl implements IBsPaymentService {
    @Resource
    private BsPaymentMapper bsPaymentMapper;

    /**
     * 查询支付约定
     *
     * @param payId 支付约定主键
     * @return 支付约定
     */
    @Override
    public BsPayment selectBsPaymentByPayId(Long payId) {
        return bsPaymentMapper.selectBsPaymentByPayId(payId);
    }

    /**
     * 查询支付约定列表
     *
     * @param bsPayment 支付约定
     * @return 支付约定
     */
    @Override
    public List<BsPayment> selectBsPaymentList(BsPayment bsPayment) {
        return bsPaymentMapper.selectBsPaymentList(bsPayment);
    }

    /**
     * 新增支付约定
     *
     * @param bsPayment 支付约定
     * @return 结果
     */
    @Override
    public int insertBsPayment(BsPayment bsPayment) {
        return bsPaymentMapper.insertBsPayment(bsPayment);
    }

    /**
     * 修改支付约定
     *
     * @param bsPayment 支付约定
     * @return 结果
     */
    @Override
    public int updateBsPayment(BsPayment bsPayment) {
        return bsPaymentMapper.updateBsPayment(bsPayment);
    }

    /**
     * 批量删除支付约定
     *
     * @param payIds 需要删除的支付约定主键
     * @return 结果
     */
    @Override
    public int deleteBsPaymentByPayIds(Long[] payIds) {
        return bsPaymentMapper.deleteBsPaymentByPayIds(payIds);
    }

    /**
     * 删除支付约定信息
     *
     * @param payId 支付约定主键
     * @return 结果
     */
    @Override
    public int deleteBsPaymentByPayId(Long payId) {
        return bsPaymentMapper.deleteBsPaymentByPayId(payId);
    }

    @Override
    public int deleteBsPaymentByEid(Long eid) {
        return bsPaymentMapper.deleteBsPaymentByEid(eid);
    }
}