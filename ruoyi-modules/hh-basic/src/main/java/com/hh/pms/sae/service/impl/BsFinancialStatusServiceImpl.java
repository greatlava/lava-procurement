package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.service.IBsFinancialStatusService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsFinancialStatusMapper;
import com.hh.pms.sae.domain.BsFinancialStatus;

import javax.annotation.Resource;

/**
 * 财务状态Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsFinancialStatusServiceImpl implements IBsFinancialStatusService {
    @Resource
    private BsFinancialStatusMapper bsFinancialStatusMapper;

    /**
     * 查询财务状态
     *
     * @param cId 财务状态主键
     * @return 财务状态
     */
    @Override
    public BsFinancialStatus selectBsFinancialStatusByCId(Long cId) {
        return bsFinancialStatusMapper.selectBsFinancialStatusByCId(cId);
    }

    /**
     * 查询财务状态列表
     *
     * @param bsFinancialStatus 财务状态
     * @return 财务状态
     */
    @Override
    public List<BsFinancialStatus> selectBsFinancialStatusList(BsFinancialStatus bsFinancialStatus) {
        return bsFinancialStatusMapper.selectBsFinancialStatusList(bsFinancialStatus);
    }

    /**
     * 新增财务状态
     *
     * @param bsFinancialStatus 财务状态
     * @return 结果
     */
    @Override
    public int insertBsFinancialStatus(BsFinancialStatus bsFinancialStatus) {
        return bsFinancialStatusMapper.insertBsFinancialStatus(bsFinancialStatus);
    }

    /**
     * 修改财务状态
     *
     * @param bsFinancialStatus 财务状态
     * @return 结果
     */
    @Override
    public int updateBsFinancialStatus(BsFinancialStatus bsFinancialStatus) {
        return bsFinancialStatusMapper.updateBsFinancialStatus(bsFinancialStatus);
    }

    /**
     * 批量删除财务状态
     *
     * @param cIds 需要删除的财务状态主键
     * @return 结果
     */
    @Override
    public int deleteBsFinancialStatusByCIds(Long[] cIds) {
        return bsFinancialStatusMapper.deleteBsFinancialStatusByCIds(cIds);
    }

    /**
     * 删除财务状态信息
     *
     * @param cId 财务状态主键
     * @return 结果
     */
    @Override
    public int deleteBsFinancialStatusByCId(Long cId) {
        return bsFinancialStatusMapper.deleteBsFinancialStatusByCId(cId);
    }
}
