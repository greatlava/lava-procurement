package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.service.IBsFinancialStatusService;
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
import com.hh.pms.sae.domain.BsFinancialStatus;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 财务状态Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/status")
public class BsFinancialStatusController extends BaseController {
    @Autowired
    private IBsFinancialStatusService bsFinancialStatusService;

    /**
     * 查询财务状态列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsFinancialStatus bsFinancialStatus) {
        startPage();
        List<BsFinancialStatus> list = bsFinancialStatusService.selectBsFinancialStatusList(bsFinancialStatus);
        return getDataTable(list);
    }

    /**
     * 导出财务状态列表
     */
    @RequiresPermissions("system:status:export")
    @Log(title = "财务状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsFinancialStatus bsFinancialStatus) {
        List<BsFinancialStatus> list = bsFinancialStatusService.selectBsFinancialStatusList(bsFinancialStatus);
        ExcelUtil<BsFinancialStatus> util = new ExcelUtil<BsFinancialStatus>(BsFinancialStatus.class);
        util.exportExcel(response, list, "财务状态数据");
    }

    /**
     * 获取财务状态详细信息
     */
    @RequiresPermissions("system:status:query")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId) {
        return success(bsFinancialStatusService.selectBsFinancialStatusByCId(cId));
    }

    /**
     * 新增财务状态
     */
    @RequiresPermissions("system:status:add")
    @Log(title = "财务状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsFinancialStatus bsFinancialStatus) {
        return toAjax(bsFinancialStatusService.insertBsFinancialStatus(bsFinancialStatus));
    }

    /**
     * 修改财务状态
     */
    @RequiresPermissions("system:status:edit")
    @Log(title = "财务状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsFinancialStatus bsFinancialStatus) {
        return toAjax(bsFinancialStatusService.updateBsFinancialStatus(bsFinancialStatus));
    }

    /**
     * 删除财务状态
     */
    @RequiresPermissions("system:status:remove")
    @Log(title = "财务状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds) {
        return toAjax(bsFinancialStatusService.deleteBsFinancialStatusByCIds(cIds));
    }
}
