package com.hh.pms.sae.service;

import java.util.List;
import com.hh.pms.sae.domain.BsRiskMonitor;

/**
 * 供应商风险监控Service接口
 *
 * @author ruoyi
 * @date 2023-11-28
 */
public interface IBsRiskMonitorService
{
    /**
     * 查询供应商风险监控
     *
     * @param rmId 供应商风险监控主键
     * @return 供应商风险监控
     */
    public BsRiskMonitor selectBsRiskMonitorByRmId(Long rmId);

    /**
     * 查询供应商风险监控列表
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 供应商风险监控集合
     */
    public List<BsRiskMonitor> selectBsRiskMonitorList(BsRiskMonitor bsRiskMonitor);

    /**
     * 新增供应商风险监控
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 结果
     */
    public int insertBsRiskMonitor(BsRiskMonitor bsRiskMonitor);

    /**
     * 修改供应商风险监控
     *
     * @param bsRiskMonitor 供应商风险监控
     * @return 结果
     */
    public int updateBsRiskMonitor(BsRiskMonitor bsRiskMonitor);

    /**
     * 批量删除供应商风险监控
     *
     * @param rmIds 需要删除的供应商风险监控主键集合
     * @return 结果
     */
    public int deleteBsRiskMonitorByRmIds(Long[] rmIds);

    /**
     * 删除供应商风险监控信息
     *
     * @param rmId 供应商风险监控主键
     * @return 结果
     */
    public int deleteBsRiskMonitorByRmId(Long rmId);
}
