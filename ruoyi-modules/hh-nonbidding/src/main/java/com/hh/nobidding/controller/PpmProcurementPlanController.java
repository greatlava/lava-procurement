package com.hh.nobidding.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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
import com.hh.nobidding.domain.PpmProcurementPlan;
import com.hh.nobidding.service.IPpmProcurementPlanService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 采购计划Controller
 *
 * @author ruoyi
 * @date 2023-12-26
 */
@RestController
@RequestMapping("/plan")
public class PpmProcurementPlanController extends BaseController {
    @Autowired
    private IPpmProcurementPlanService ppmProcurementPlanService;

    /**
     * 查询采购计划列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PpmProcurementPlan ppmProcurementPlan) {
        startPage();
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectPpmProcurementPlanList(ppmProcurementPlan);
        return getDataTable(list);
    }

    /**
     * 导出采购计划列表
     */
    @Log(title = "采购计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmProcurementPlan ppmProcurementPlan) {
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectPpmProcurementPlanList(ppmProcurementPlan);
        ExcelUtil<PpmProcurementPlan> util = new ExcelUtil<PpmProcurementPlan>(PpmProcurementPlan.class);
        util.exportExcel(response, list, "采购计划数据");
    }

    /**
     * 获取采购计划详细信息
     */
    @GetMapping(value = "/{aid}")
    public AjaxResult getInfo(@PathVariable("aid") Long aid) {
        return success(ppmProcurementPlanService.selectPpmProcurementPlanByAid(aid));
    }

    /**
     * 新增采购计划
     */
    @Log(title = "采购计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        return toAjax(ppmProcurementPlanService.insertPpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 修改采购计划
     */
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        return toAjax(ppmProcurementPlanService.updatePpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 删除采购计划
     */
    @Log(title = "采购计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aids}")
    public AjaxResult remove(@PathVariable Long[] aids) {
        return toAjax(ppmProcurementPlanService.deletePpmProcurementPlanByAids(aids));
    }
}
