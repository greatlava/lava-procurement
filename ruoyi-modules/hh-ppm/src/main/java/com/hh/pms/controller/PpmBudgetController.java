package com.hh.pms.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.domain.PpmBudget;
import com.hh.pms.service.IPpmBudgetService;
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
 * 预算Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/budget")
public class PpmBudgetController extends BaseController
{
    @Autowired
    private IPpmBudgetService ppmBudgetService;

    /**
     * 查询预算列表
     */
    @RequiresPermissions("system:budget:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmBudget ppmBudget)
    {
        startPage();
        List<PpmBudget> list = ppmBudgetService.selectPpmBudgetList(ppmBudget);
        return getDataTable(list);
    }

    /**
     * 导出预算列表
     */
    @RequiresPermissions("system:budget:export")
    @Log(title = "预算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmBudget ppmBudget)
    {
        List<PpmBudget> list = ppmBudgetService.selectPpmBudgetList(ppmBudget);
        ExcelUtil<PpmBudget> util = new ExcelUtil<PpmBudget>(PpmBudget.class);
        util.exportExcel(response, list, "预算数据");
    }

    /**
     * 获取预算详细信息
     */
    @RequiresPermissions("system:budget:query")
    @GetMapping(value = "/{duId}")
    public AjaxResult getInfo(@PathVariable("duId") Long duId)
    {
        return success(ppmBudgetService.selectPpmBudgetByDuId(duId));
    }

    /**
     * 新增预算
     */
    @RequiresPermissions("system:budget:add")
    @Log(title = "预算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmBudget ppmBudget)
    {
        return toAjax(ppmBudgetService.insertPpmBudget(ppmBudget));
    }

    /**
     * 修改预算
     */
    @RequiresPermissions("system:budget:edit")
    @Log(title = "预算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmBudget ppmBudget)
    {
        return toAjax(ppmBudgetService.updatePpmBudget(ppmBudget));
    }

    /**
     * 删除预算
     */
    @RequiresPermissions("system:budget:remove")
    @Log(title = "预算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{duIds}")
    public AjaxResult remove(@PathVariable Long[] duIds)
    {
        return toAjax(ppmBudgetService.deletePpmBudgetByDuIds(duIds));
    }
}
