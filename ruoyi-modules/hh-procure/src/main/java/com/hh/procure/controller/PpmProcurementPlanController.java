package com.hh.procure.controller;

import com.hh.procure.Util.CodeRuleHelp;
import com.hh.procure.Util.CodeRuleUtil;
import com.hh.procure.Util.StringPathUtils;
import com.hh.procure.domain.*;
import com.hh.procure.service.IComPubAttachmentsService;
import com.hh.procure.service.IPpmApprovalRecordService;
import com.hh.procure.service.IPpmLineItemsService;
import com.hh.procure.service.IPpmProcurementPlanService;
import com.hh.procure.service.imp.ComCodeRulesServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.BidTender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
        ppmProcurementPlan.setCreateBy(SecurityUtils.getLoginUser().getSysUser().getNickName());
        ppmProcurementPlan.setaCreateDept(SecurityUtils.getLoginUser().getSysUser().getDept().getDeptName());
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
//    @RequiresPermissions("system:plan:edit")
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @PutMapping
    @Transactional
    public AjaxResult edit(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        System.out.println("ppmProcurementPlan:" + ppmProcurementPlan);
        PpmProcurementPlan result = ppmProcurementPlanService.selectPpmProcurementPlanByAid(ppmProcurementPlan.getAid());
        if (!result.getaAstate().equals(ppmProcurementPlan.getaAstate())) {
            PpmApprovalRecord obj = ppmProcurementPlan.getEditor();
            obj.setProcessedBy(tokenService.getLoginUser().getSysUser().getNickName());
            obj.setAid(ppmProcurementPlan.getAid());
            obj.setDepnt(SecurityUtils.getLoginUser().getSysUser().getDept().getDeptName());
            obj.setUpdateTime(DateUtils.getNowDate());
            ppmProcurementPlan.setUpdateTime(DateUtils.getNowDate());
            switch (ppmProcurementPlan.getaAstate()) {
                case 0:
                    obj.setNode("部门主管审批");
                    obj.setOpinion("驳回");
                    break;
                case 1:
                    obj.setNode("提交采购计划");
                    obj.setOpinion("发起申请");
                    break;
                case 2:
                    obj.setNode("采购计划通过审核");
                    obj.setOpinion("通过");
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

    @RequiresPermissions("system:attachments:list")
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
        BidTender bidTender = new BidTender();
        NobidNonPro nobidNonPro = new NobidNonPro();
        for (PpmProcurementPlan item : ppmProcurementPlan) {
            item.setaAstate(3);
            item.setUpdateTime(DateUtils.getNowDate());
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
                    bidTender.setsProjectState(1);
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
                    nobidNonPro.setTendertype(type);
                    ppmProcurementPlanService.insertNoBidPro(nobidNonPro);
                    break;
            }

        }
        return AjaxResult.success();
    }

    @RequestMapping("/FindProcurementPlanBy")
    public TableDataInfo FindProcurementPlanBy(PpmProcurementPlan ppmProcurementPlan) {
        startPage();
        return getDataTable(ppmProcurementPlanService.FindProcurementPlanBy(ppmProcurementPlan));
    }

    @RequiresPermissions("system:plan:list")
    @GetMapping("/PpmProcurementPlanAndComPubAttament")
    public TableDataInfo selectePpmProcurementPlanAndComPubAttamentByAid( PpmProcurementPlan ppmProcurementPlan) {
        System.out.println("执行了PpmProcurementPlanAndComPubAttament：" + ppmProcurementPlan);
        startPage();
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectePpmProcurementPlanAndComPubAttamentByAid(ppmProcurementPlan);
        return getDataTable(list);
    }


    @PostMapping("/selectPpmpProcurementCount")
    public int selectPpmpProcurementCount(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        System.out.printf("state:" + ppmProcurementPlan + "\n");
        return ppmProcurementPlanService.selectPpmpProcurementCount(ppmProcurementPlan);
    }

    @RequiresPermissions
    @PostMapping("/selectContractCount")
    public R selectContractCount() {
        return R.ok(ppmProcurementPlanService.selectContractCount());
    }

    /**
     * 查询采购计划总金额
     *
     * @return
     */
    @PostMapping("/queryTotalPurchaseAmount")
    public R queryTotalPurchaseAmount() {
        return R.ok(ppmProcurementPlanService.queryTotalPurchaseAmount());
    }

    /**
     * 查询招标项目数量
     *
     * @return
     */
    @PostMapping("/selectTenderCount")
    public R selectTenderCount() {
        return R.ok(ppmProcurementPlanService.selectTenderCount());
    }


    /**
     * 查询招标阶段的项目
     *
     * @param bidTender
     * @return
     */
    @PostMapping("/selectTenderByState")
    public List<BidTender> selectTenderByState(@RequestBody BidTender bidTender) {
        List<BidTender> bidTenders = ppmProcurementPlanService.selectTenderByState(bidTender);
        System.out.println("create_time:" + bidTenders + "\n)");
        return bidTenders;
    }

    @PostMapping(value = "/selectTenderByStateCount")
    public R selectTenderByStateCount(@RequestBody BidTender bidTender) {
        return R.ok(ppmProcurementPlanService.selectTenderByStateCount(bidTender));
    }
}
