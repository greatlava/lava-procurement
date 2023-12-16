package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.mapper.BsRiskMonitorMapper;
import com.hh.pms.sae.service.IBsRiskMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.domain.BsRiskMonitor;

/**
 * 供应商风险监控Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-28
 */
@Service
public class BsRiskMonitorServiceImpl implements IBsRiskMonitorService {
    @Autowired
    private BsRiskMonitorMapper bsRiskMonitorMapper;

    /**
     * 查询供应商风险监控
     *
     * @param rmId 供应商风险监控主键
     * @return 供应商风险监控
     */
    @Override
    public BsRiskMonitor selectBsRiskMonitorByRmId(Long rmId) {
        return bsRiskMonitorMapper.selectBsRiskMonitorByRmId(rmId);
    }

    /**
     * 查询供应商风险监控列表
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 供应商风险监控
     */
    @Override
    public List<BsRiskMonitor> selectBsRiskMonitorList(BsRiskMonitor bsRiskMonitor) {
        return bsRiskMonitorMapper.selectBsRiskMonitorList(bsRiskMonitor);
    }

    /**
     * 新增供应商风险监控
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 结果
     */
    @Override
    public int insertBsRiskMonitor(BsRiskMonitor bsRiskMonitor) {
        return bsRiskMonitorMapper.insertBsRiskMonitor(bsRiskMonitor);
    }

    /**
     * 修改供应商风险监控
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 结果
     */
    @Override
    public int updateBsRiskMonitor(BsRiskMonitor bsRiskMonitor) {
        return bsRiskMonitorMapper.updateBsRiskMonitor(bsRiskMonitor);
    }

    /**
     * 批量删除供应商风险监控
     *
     * @param rmIds 需要删除的供应商风险监控主键
     * @return 结果
     */
    @Override
    public int deleteBsRiskMonitorByRmIds(Long[] rmIds) {
        return bsRiskMonitorMapper.deleteBsRiskMonitorByRmIds(rmIds);
    }

    /**
     * 删除供应商风险监控信息
     *
     * @param rmId 供应商风险监控主键
     * @return 结果
     */
    @Override
    public int deleteBsRiskMonitorByRmId(Long rmId) {
        return bsRiskMonitorMapper.deleteBsRiskMonitorByRmId(rmId);
    }
}
