package com.hh.procure.controller;

import com.hh.procure.Util.CodeRuleHelp;
import com.hh.procure.Util.CodeRuleUtil;
import com.hh.procure.domain.CodeRulesResult;
import com.hh.procure.domain.ComCodeRules;
import com.hh.procure.domain.PpmFramePlan;
import com.hh.procure.domain.PpmLineItems;
import com.hh.procure.service.IComCodeRulesService;
import com.hh.procure.service.IPpmFramePlanService;
import com.hh.procure.service.IPpmLineItemsService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.domain.BidTender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        // 获取当前的用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<PpmLineItems> items = ppmFramePlan.getItems();
        ComCodeRules rules = iComCodeRulesService.selectComCodeRulesByTargetForm(CodeRuleUtil.FRAMEWORK);
        CodeRulesResult result = CodeRuleHelp.GetCodeRule(rules);
        ppmFramePlan.setJhCode(result.getCode());
        ppmFramePlan.setDept(loginUser.getSysUser().getDept().getDeptName());
        ppmFramePlan.setJhFounder(loginUser.getSysUser().getNickName());
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
        if (ppmFramePlan.getJhStatus() == 2) {
            ppmFramePlan.setJhPerson(SecurityUtils.getLoginUser().getSysUser().getNickName());
        }
        System.out.println("ppmFramePlan:" + ppmFramePlan + "\n");
        return AjaxResult.success(ppmFramePlanService.updateFarmeworkPlanStatusByJhId(ppmFramePlan));
    }

    @PostMapping("/selectFarmeworkPlanCount")
    public R selectFarmeworkPlanCount() {
        return R.ok(ppmFramePlanService.selectFarmeworkPlanCount());
    }


    //查询已完成并且未创建框架协议的框架计划
    @GetMapping("/list1")
    public TableDataInfo list1(PpmFramePlan ppmFramePlan) {
        startPage();
        List<PpmFramePlan> list = ppmFramePlanService.selectBsFramePlanList(ppmFramePlan);
        return getDataTable(list);
    }

    //(协议作废)修改oid为空
    @GetMapping("/XyCancel")
    public AjaxResult XyCancel(Long oid) {
        int i = ppmFramePlanService.updatePpmFramePlanByOid(oid);
        if (i > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    //框架协议新增后修改框架计划oid
    @PutMapping("/upOidbyOid")
    public AjaxResult upOidbyOid(@RequestBody PpmFramePlan ppmFramePlan) {
        System.out.println(ppmFramePlan);
        int i = ppmFramePlanService.updateOidbyOid(ppmFramePlan);
        if (i > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }
}
