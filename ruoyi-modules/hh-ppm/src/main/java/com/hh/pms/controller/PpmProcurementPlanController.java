package com.hh.pms.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.Util.CodeRuleHelp;
import com.hh.pms.Util.CodeRuleUtil;
import com.hh.pms.domain.*;
import com.hh.pms.service.IPpmApprovalRecordService;
import com.hh.pms.service.IPpmLineItemsService;
import com.hh.pms.service.IPpmProcurementPlanService;
import com.hh.pms.service.imp.ComCodeRulesServiceImpl;
import com.ruoyi.common.security.service.TokenService;
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
 * 采购计划Controller
 *
 * @author ruoyi
 * @date 2023-11-28
 */
@RestController
@RequestMapping("/plan")
public class PpmProcurementPlanController extends BaseController {
    @Autowired
    private IPpmProcurementPlanService ppmProcurementPlanService;

    @Autowired
    private IPpmApprovalRecordService PpmApprovalRecordService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ComCodeRulesServiceImpl comCodeRulesService;

    @Autowired
    private IPpmLineItemsService ppmLineItemsService;

    /**
     * 查询采购计划列表
     */
    @RequiresPermissions("system:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmProcurementPlan ppmProcurementPlan) {
        startPage();
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectPpmProcurementPlanList(ppmProcurementPlan);
        return getDataTable(list);
    }

    /**
     * 导出采购计划列表
     */
    @RequiresPermissions("system:plan:export")
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
    @RequiresPermissions("system:plan:query")
    @GetMapping(value = "/{aid}")
    public AjaxResult getInfo(@PathVariable("aid") Integer aid) {
        return success(ppmProcurementPlanService.selectPpmProcurementPlanByAid(aid));
    }

    /**
     * 新增采购计划
     */
    @RequiresPermissions("system:plan:add")
    @Log(title = "采购计划", businessType = BusinessType.INSERT)
    @Transactional
    @PostMapping
    public AjaxResult add(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        return toAjax(ppmProcurementPlanService.insertPpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 修改采购计划
     */
    @RequiresPermissions("system:plan:edit")
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @PutMapping
    @Transactional
    public AjaxResult edit(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        PpmProcurementPlan result = ppmProcurementPlanService.selectPpmProcurementPlanByAid(ppmProcurementPlan.getAid());
        if (!result.getaAstate().equals(ppmProcurementPlan.getaAstate())) {
            PpmApprovalRecord obj = new PpmApprovalRecord();
            obj.setProcessedBy(tokenService.getLoginUser().getUsername());
            obj.setAid(ppmProcurementPlan.getAid());
            obj.setDepnt("采购部");
            switch (ppmProcurementPlan.getaAstate()) {
                case 0:
                    obj.setNode("部门主管审批");
                    obj.setOpinion("驳回");
                    obj.setOpinionDetails("计划不合格，请改正");
                    break;
                case 1:
                    obj.setNode("提交采购计划");
                    obj.setOpinion("发起申请");
                    obj.setOpinionDetails("采购计划已准备，需审核");
                    break;
                case 2:
                    obj.setNode("采购计划通过审核");
                    obj.setOpinion("通过");
                    obj.setOpinionDetails("你的采购计划审核已通过");
                    break;
            }
            PpmApprovalRecordService.insertPpmApprovalRecord(obj);
        }
        return toAjax(ppmProcurementPlanService.updatePpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 删除采购计划
     */
    @RequiresPermissions("system:plan:remove")
    @Log(title = "采购计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aids}")
    public AjaxResult remove(@PathVariable Integer[] aids) {
        return toAjax(ppmProcurementPlanService.deletePpmProcurementPlanByAids(aids));
    }

    @RequiresPermissions("system:plan:query")
    @GetMapping("/many")
    public AjaxResult selectProcurementPlanByIdForThreeTables(Integer aid) {
        return success(ppmProcurementPlanService.selectProcurementPlanByIdForThreeTables(aid));
    }

    @RequiresPermissions("system:plan:query")
    @GetMapping("/generatePlanID")
    public String generatePlanID() {
        ComCodeRules rules = comCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.PROCUREMENTNAME);
        CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
        rules.setMaxMantissa(result.getMax());
        comCodeRulesService.updateComCodeRules(rules);
        return result.getCode();
    }

    @RequiresPermissions("system:plan:edit")
    @PostMapping("/otherInformation")
    @Transactional
    public AjaxResult ModifyPlanAndOtherInformation(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        List<PpmLineItems> list = ppmProcurementPlan.getItems();
        ppmLineItemsService.deletePpmLineItemsByAid(ppmProcurementPlan.getAid());
        for (PpmLineItems item : list) {
            if (item.getvCode() == null) {
                ComCodeRules rules = comCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.LINEITEM);
                CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
                rules.setMaxMantissa(result.getMax());
                comCodeRulesService.updateComCodeRules(rules);
                item.setvCode(result.getCode());
                if (item.getAid() == null) {
                    item.setAid(ppmProcurementPlan.getAid());
                }
            }
            ppmLineItemsService.insertPpmLineItems(item);
        }
        return AjaxResult.success(ppmProcurementPlanService.updatePpmProcurementPlan(ppmProcurementPlan));
    }
}
