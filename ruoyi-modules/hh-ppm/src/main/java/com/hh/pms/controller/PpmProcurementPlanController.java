package com.hh.pms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.Util.CodeRuleHelp;
import com.hh.pms.Util.CodeRuleUtil;
import com.hh.pms.Util.StringPathUtils;
import com.hh.pms.service.IComPubAttachmentsService;
import com.hh.pms.service.IPpmApprovalRecordService;
import com.hh.pms.service.IPpmLineItemsService;
import com.hh.pms.service.IPpmProcurementPlanService;
import com.hh.pms.service.imp.ComCodeRulesServiceImpl;
import com.hh.pms.domain.*;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.domain.BidTender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private IComPubAttachmentsService comPubAttachmentsService;


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
        ComPubAttachments comPubAttachments = ppmProcurementPlan.getFile();
        int i = ppmProcurementPlanService.insertPpmProcurementPlan(ppmProcurementPlan);
        if (comPubAttachments != null) {
            comPubAttachments.setAnName(StringPathUtils.cutToTheEndStr(comPubAttachments.getAnName()));
            comPubAttachments.setAnUrl(StringPathUtils.cutToTheEndStr(comPubAttachments.getAnUrl()));
            comPubAttachments.setAid(ppmProcurementPlan.getAid());
            return toAjax(comPubAttachmentsService.insertComPubAttachments(comPubAttachments));
        }
        return toAjax(i);
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
    @Transactional
    @DeleteMapping("/{aids}")
    public AjaxResult remove(@PathVariable Integer[] aids) {
        ppmLineItemsService.deletePpmLineItemsByAid(aids[0]);
        comPubAttachmentsService.deleteComPubAttamentsByAid(aids[0]);
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

    @PostMapping("/updateStateAndAddBidWinning")
    @Transactional
    public AjaxResult updateStateAndAddBidWinning(@RequestBody List<PpmProcurementPlan> ppmProcurementPlan, Integer type, Integer noBidType) {
        System.out.println("noBidType:" + noBidType);
        BidTender bidTender = new BidTender();
        NobidNonPro nobidNonPro = new NobidNonPro();
        for (PpmProcurementPlan item : ppmProcurementPlan) {
            item.setaAstate(3);
            ppmProcurementPlanService.updatePpmProcurementPlan(item);
            switch (type) {
                case 1:
                case 2:
                    ComCodeRules rules = comCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.INVITETENDERS);
                    CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
                    rules.setMaxMantissa(result.getMax());
                    comCodeRulesService.updateComCodeRules(rules);
                    bidTender.setXyId(item.getAid());
                    bidTender.setsCode(result.getCode());
                    bidTender.setsName(item.getaName());
                    bidTender.setsWay(type);
                    bidTender.setsProjectState(2);
                    bidTender.setsType(item.getaBtype());
                    bidTender.setCreateBy(item.getCreateBy());
                    ppmProcurementPlanService.insertTenders(bidTender);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    rules = comCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.NOTINVITETENDERS);
                    result = CodeRuleHelp.GetCodeRule(rules);
                    rules.setMaxMantissa(result.getMax());
                    comCodeRulesService.updateComCodeRules(rules);
                    nobidNonPro.setgCode(result.getCode());
                    nobidNonPro.setXyId(item.getAid());
                    nobidNonPro.setgName(item.getaName());
                    nobidNonPro.setgIsPublic(noBidType);
                    nobidNonPro.setTendertype(item.getaBtype());
                    ppmProcurementPlanService.insertNoBidPro(nobidNonPro);
                    break;
            }

        }
        return AjaxResult.success();
    }

    @RequestMapping("/FindProcurementPlanBy")
    public TableDataInfo FindProcurementPlanBy(PpmProcurementPlan ppmProcurementPlan) {
        return getDataTable(ppmProcurementPlanService.FindProcurementPlanBy(ppmProcurementPlan));
    }

    @RequiresPermissions("system:plan:list")
    @PostMapping("/PpmProcurementPlanAndComPubAttament")
    public TableDataInfo selectePpmProcurementPlanAndComPubAttamentByAid(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        System.out.println("执行了PpmProcurementPlanAndComPubAttament：" + ppmProcurementPlan);
        startPage();
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectePpmProcurementPlanAndComPubAttamentByAid(ppmProcurementPlan);
        return getDataTable(list);
    }
}
