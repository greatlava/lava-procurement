package com.hh.procure.controller;

import com.hh.procure.Util.CodeRuleHelp;
import com.hh.procure.Util.CodeRuleUtil;
import com.hh.procure.domain.CodeRulesResult;
import com.hh.procure.domain.ComCodeRules;
import com.hh.procure.domain.PpmBudget;
import com.hh.procure.service.IPpmBudgetService;
import com.hh.procure.service.imp.ComCodeRulesServiceImpl;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 预算Controller
 *
 * @author ruoyi
 * @date 2023-11-28
 */
@RestController
@RequestMapping("/budget")
public class PpmBudgetController extends BaseController {
    @Autowired
    private IPpmBudgetService ppmBudgetService;

    @Autowired
    private ComCodeRulesServiceImpl comCodeRulesService;

    /**
     * 查询预算列表
     */
    @RequiresPermissions("system:budget:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmBudget ppmBudget) {
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
    public void export(HttpServletResponse response, PpmBudget ppmBudget) {
        List<PpmBudget> list = ppmBudgetService.selectPpmBudgetList(ppmBudget);
        ExcelUtil<PpmBudget> util = new ExcelUtil<PpmBudget>(PpmBudget.class);
        util.exportExcel(response, list, "预算数据");
    }

    /**
     * 获取预算详细信息
     */
    @RequiresPermissions("system:budget:query")
    @GetMapping(value = "/{duId}")
    public AjaxResult getInfo(@PathVariable("duId") String duId) {
        return success(ppmBudgetService.selectPpmBudgetByDuId(duId));
    }

    /**
     * 新增预算
     */
    @RequiresPermissions("system:budget:add")
    @Log(title = "预算", businessType = BusinessType.INSERT)
    @PostMapping
    @Transactional
    public AjaxResult add(@RequestBody PpmBudget ppmBudget) {
        ComCodeRules result = comCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.BUDGET);
        CodeRulesResult rule = CodeRuleHelp.GetCodeRule(result);
        ppmBudget.setDuId(rule.getCode());
        result.setMaxMantissa(rule.getMax());
        comCodeRulesService.updateComCodeRules(result);
        ppmBudgetService.insertPpmBudget(ppmBudget);
        return AjaxResult.success(rule.getCode());
    }

    /**
     * 修改预算
     */
    @RequiresPermissions("system:budget:edit")
    @Log(title = "预算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmBudget ppmBudget) {
        return toAjax(ppmBudgetService.updatePpmBudget(ppmBudget));
    }

    /**
     * 删除预算
     */
    @RequiresPermissions("system:budget:remove")
    @Log(title = "预算", businessType = BusinessType.DELETE)
    @DeleteMapping("/{duIds}")
    public AjaxResult remove(@PathVariable String[] duIds) {
        return toAjax(ppmBudgetService.deletePpmBudgetByDuIds(duIds));
    }

    @RequiresPermissions("system:budget:query")
    @PostMapping("/BudgetBy")
    public AjaxResult selectPpmBudgetByAid(String aid) {
        return AjaxResult.success(ppmBudgetService.selectPpmBudgetByAid(aid));
    }
}
