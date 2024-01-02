package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.service.IBsRiskMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.sae.domain.BsRiskMonitor;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 供应商风险监控Controller
 *
 * @author ruoyi
 * @date 2023-11-28
 */
@RestController
@RequestMapping("/monitor")
public class BsRiskMonitorController extends BaseController {
    @Autowired
    private IBsRiskMonitorService bsRiskMonitorService;

    /**
     * 查询供应商风险监控列表
     */
    @RequiresPermissions("system:monitor:riskMonitor")
    @GetMapping("/list")
    public TableDataInfo list(BsRiskMonitor bsRiskMonitor) {
        startPage();
        List<BsRiskMonitor> list = bsRiskMonitorService.selectBsRiskMonitorList(bsRiskMonitor);
        return getDataTable(list);
    }

    /**
     * 导出供应商风险监控列表
     */
    @Log(title = "供应商风险监控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsRiskMonitor bsRiskMonitor) {
        List<BsRiskMonitor> list = bsRiskMonitorService.selectBsRiskMonitorList(bsRiskMonitor);
        ExcelUtil<BsRiskMonitor> util = new ExcelUtil<BsRiskMonitor>(BsRiskMonitor.class);
        util.exportExcel(response, list, "供应商风险监控数据");
    }

    /**
     * 获取供应商风险监控详细信息
     */
    @GetMapping(value = "/{rmId}")
    public AjaxResult getInfo(@PathVariable("rmId") Long rmId) {
        return success(bsRiskMonitorService.selectBsRiskMonitorByRmId(rmId));
    }

    /**
     * 新增供应商风险监控
     */
    @Log(title = "供应商风险监控", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsRiskMonitor bsRiskMonitor) {
        return toAjax(bsRiskMonitorService.insertBsRiskMonitor(bsRiskMonitor));
    }

    /**
     * 修改供应商风险监控
     */
    @Log(title = "供应商风险监控", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsRiskMonitor bsRiskMonitor) {
        return toAjax(bsRiskMonitorService.updateBsRiskMonitor(bsRiskMonitor));
    }

    /**
     * 删除供应商风险监控
     */
    @Log(title = "供应商风险监控", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rmIds}")
    public AjaxResult remove(@PathVariable Long[] rmIds) {
        return toAjax(bsRiskMonitorService.deleteBsRiskMonitorByRmIds(rmIds));
    }
}
