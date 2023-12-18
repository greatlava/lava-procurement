package com.hh.pms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.Util.CodeRuleHelp;
import com.hh.pms.Util.CodeRuleUtil;
import com.hh.pms.domain.CodeRulesResult;
import com.hh.pms.domain.ComCodeRules;
import com.hh.pms.domain.PpmFramePlan;
import com.hh.pms.domain.PpmLineItems;
import com.hh.pms.service.IComCodeRulesService;
import com.hh.pms.service.IPpmFramePlanService;
import com.hh.pms.service.IPpmLineItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
public class PpmFramePlanController extends BaseController {
    @Autowired
    private IPpmFramePlanService ppmFramePlanService;

    @Autowired
    private IComCodeRulesService iComCodeRulesService;

    @Autowired
    private IPpmLineItemsService iPpmLineItemsService;

    /**
     * 查询框架计划列表
     */
    @RequiresPermissions("system:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmFramePlan ppmFramePlan) {
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
    public void export(HttpServletResponse response, PpmFramePlan ppmFramePlan) {
        List<PpmFramePlan> list = ppmFramePlanService.selectPpmFramePlanList(ppmFramePlan);
        ExcelUtil<PpmFramePlan> util = new ExcelUtil<PpmFramePlan>(PpmFramePlan.class);
        util.exportExcel(response, list, "框架计划数据");
    }

    /**
     * 获取框架计划详细信息
     */
    @RequiresPermissions("system:plan:query")
    @GetMapping(value = "/{jhId}")
    public AjaxResult getInfo(@PathVariable("jhId") Long jhId) {
        return success(ppmFramePlanService.selectPpmFramePlanByJhId(jhId));
    }

    /**
     * 新增框架计划
     */
    @RequiresPermissions("system:plan:add")
    @Log(title = "框架计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmFramePlan ppmFramePlan) {
        return toAjax(ppmFramePlanService.insertPpmFramePlan(ppmFramePlan));
    }

    /**
     * 修改框架计划
     */
    @RequiresPermissions("system:plan:edit")
    @Log(title = "框架计划", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping
    public AjaxResult edit(@RequestBody PpmFramePlan ppmFramePlan) {
        iPpmLineItemsService.deletePpmLineItemByJhId(ppmFramePlan.getJhId());
        List<PpmLineItems> items = ppmFramePlan.getItems();
        for (PpmLineItems item : items) {
            if (item.getvCode() == null) {
                ComCodeRules rules = iComCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.LINEITEM);
                CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
                rules.setMaxMantissa(result.getMax());
                iComCodeRulesService.updateComCodeRules(rules);
                item.setvCode(result.getCode());
            }
            item.setJhId(ppmFramePlan.getJhId());
            iPpmLineItemsService.insertPpmLineItems(item);
        }
        return toAjax(ppmFramePlanService.updatePpmFramePlan(ppmFramePlan));
    }

    /**
     * 删除框架计划
     */
    @RequiresPermissions("system:plan:remove")
    @Log(title = "框架计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jhIds}")
    public AjaxResult remove(@PathVariable Long[] jhIds) {
        return toAjax(ppmFramePlanService.deletePpmFramePlanByJhIds(jhIds));
    }

    @PostMapping("/AddPlanAndOther")
    @Transactional
    public AjaxResult AddFrameworkPlanAndOtherInformation(@RequestBody PpmFramePlan ppmFramePlan) {
        List<PpmLineItems> items = ppmFramePlan.getItems();
        ComCodeRules rules = iComCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.FRAMEWORK);
        CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
        ppmFramePlan.setJhCode(result.getCode());
        ppmFramePlanService.insertPpmFramePlan(ppmFramePlan);
        rules.setMaxMantissa(result.getMax());
        iComCodeRulesService.updateComCodeRules(rules);
        for (PpmLineItems item : items) {
            rules = iComCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.LINEITEM);
            result = CodeRuleHelp.GetCodeRule(rules);
            item.setvCode(result.getCode());
            item.setJhId(ppmFramePlan.getJhId());
            rules.setMaxMantissa(result.getMax());
            iComCodeRulesService.updateComCodeRules(rules);
            iPpmLineItemsService.insertPpmLineItems(item);
        }
        return AjaxResult.success();
    }

    @PostMapping("/PlanAndRelatedInformation")
    public AjaxResult queryFrameworkPlanAndRelatedInformation(Integer jhId) {
        return AjaxResult.success(ppmFramePlanService.queryFrameworkPlanAndRelatedInformation(jhId));
    }

    @PostMapping("/deleteByJhId")
    @Transactional
    public AjaxResult deleteByJhId(Integer jhid) {
        ppmFramePlanService.deletePpmFramePlanByJhId(jhid);
        iPpmLineItemsService.deletePpmLineItemByJhId(jhid);
        return AjaxResult.success();
    }

    @PostMapping("/updateFarmeworkPlanStatus")
    public AjaxResult updateFarmeworkPlanStatusByJhId(PpmFramePlan ppmFramePlan) {
        ppmFramePlanService.updateFarmeworkPlanStatusByJhId(ppmFramePlan);
        return AjaxResult.success();
    }
}
