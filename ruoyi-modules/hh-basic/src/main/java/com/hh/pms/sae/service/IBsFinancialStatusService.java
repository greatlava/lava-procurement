package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsFinancialStatus;

/**
 * 财务状态Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsFinancialStatusService {
    /**
     * 查询财务状态
     *
     * @param cId 财务状态主键
     * @return 财务状态
     */
    public BsFinancialStatus selectBsFinancialStatusByCId(Long cId);

    /**
     * 查询财务状态列表
     *
     * @param bsFinancialStatus 财务状态
     * @return 财务状态集合
     */
    public List<BsFinancialStatus> selectBsFinancialStatusList(BsFinancialStatus bsFinancialStatus);

    /**
     * 新增财务状态
     *
     * @param bsFinancialStatus 财务状态
     * @return 结果
     */
    public int insertBsFinancialStatus(BsFinancialStatus bsFinancialStatus);

    /**
     * 修改财务状态
     *
     * @param bsFinancialStatus 财务状态
     * @return 结果
     */
    public int updateBsFinancialStatus(BsFinancialStatus bsFinancialStatus);

    /**
     * 批量删除财务状态
     *
     * @param cIds 需要删除的财务状态主键集合
     * @return 结果
     */
    public int deleteBsFinancialStatusByCIds(Long[] cIds);

    /**
     * 删除财务状态信息
     *
     * @param cId 财务状态主键
     * @return 结果
     */
    public int deleteBsFinancialStatusByCId(Long cId);
}
