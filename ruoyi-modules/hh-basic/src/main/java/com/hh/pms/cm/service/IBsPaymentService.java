package com.hh.pms.cm.service;

import java.util.List;
import com.hh.pms.cm.domain.BsPayment;

/**
 * 支付约定Service接口
 *
 * @author ruoyi
 * @date 2023-11-30
 */
public interface IBsPaymentService
{
    /**
     * 查询支付约定
     *
     * @param payId 支付约定主键
     * @return 支付约定
     */
    public BsPayment selectBsPaymentByPayId(Long payId);

    /**
     * 查询支付约定列表
     *
     * @param bsPayment 支付约定
     * @return 支付约定集合
     */
    public List<BsPayment> selectBsPaymentList(BsPayment bsPayment);

    /**
     * 新增支付约定
     *
     * @param bsPayment 支付约定
     * @return 结果
     */
    public int insertBsPayment(BsPayment bsPayment);

    /**
     * 修改支付约定
     *
     * @param bsPayment 支付约定
     * @return 结果
     */
    public int updateBsPayment(BsPayment bsPayment);

    /**
     * 批量删除支付约定
     *
     * @param payIds 需要删除的支付约定主键集合
     * @return 结果
     */
    public int deleteBsPaymentByPayIds(Long[] payIds);

    /**
     * 删除支付约定信息
     *
     * @param payId 支付约定主键
     * @return 结果
     */
    public int deleteBsPaymentByPayId(Long payId);

    //根据id删除支付约定
    public int deleteBsPaymentByEid(Long eid);
}
