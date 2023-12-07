package com.hh.pms.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.domain.PpmFramePlan;
import com.hh.pms.service.IPpmFramePlanService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 框架计划Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/framework")
public class PpmFramePlanController extends BaseController
{
    @Autowired
    private IPpmFramePlanService ppmFramePlanService;

    /**
     * 查询框架计划列表
     */
    @RequiresPermissions("system:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmFramePlan ppmFramePlan)
    {
        startPage();
        List<PpmFramePlan> list = ppmFramePlanService.selectPpmFramePlanList(ppmFramePlan);
        return getDataTable(list);
    }

    /**
     * 导出框架计划列表
     */
    @RequiresPermissions("system:plan:export")
    @Log(title = "框架计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmFramePlan ppmFramePlan)
    {
        List<PpmFramePlan> list = ppmFramePlanService.selectPpmFramePlanList(ppmFramePlan);
        ExcelUtil<PpmFramePlan> util = new ExcelUtil<PpmFramePlan>(PpmFramePlan.class);
        util.exportExcel(response, list, "框架计划数据");
    }

    /**
     * 获取框架计划详细信息
     */
    @RequiresPermissions("system:plan:query")
    @GetMapping(value = "/{jhId}")
    public AjaxResult getInfo(@PathVariable("jhId") Long jhId)
    {
        return success(ppmFramePlanService.selectPpmFramePlanByJhId(jhId));
    }

    /**
     * 新增框架计划
     */
    @RequiresPermissions("system:plan:add")
    @Log(title = "框架计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmFramePlan ppmFramePlan)
    {
        return toAjax(ppmFramePlanService.insertPpmFramePlan(ppmFramePlan));
    }

    /**
     * 修改框架计划
     */
    @RequiresPermissions("system:plan:edit")
    @Log(title = "框架计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmFramePlan ppmFramePlan)
    {
        return toAjax(ppmFramePlanService.updatePpmFramePlan(ppmFramePlan));
    }

    /**
     * 删除框架计划
     */
    @RequiresPermissions("system:plan:remove")
    @Log(title = "框架计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jhIds}")
    public AjaxResult remove(@PathVariable Long[] jhIds)
    {
        return toAjax(ppmFramePlanService.deletePpmFramePlanByJhIds(jhIds));
    }
}
