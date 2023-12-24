package com.hh.pms.cm.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.cm.domain.*;
import com.hh.pms.cm.service.*;
import com.hh.pms.cm.util.CodeRuleHelp;
import com.hh.pms.cm.util.CodeRuleUtil;
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
 * 合同Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/contract")
public class BsContractController extends BaseController {
    @Autowired
    private IBsContractService bsContractService;

    @Autowired
    private IComCodeRulesService codeRulesService;

    @Autowired
    private IBsInventoryService inventoryService;

    @Autowired
    private IBsPaymentService paymentService;

    @Autowired
    private IBsSignService signService;

    /**
     * 查询合同列表
     */
//    @RequiresPermissions("system:contract:list")
    @GetMapping("/list")
    public TableDataInfo list(BsContract bsContract) {
        startPage();
        List<BsContract> list = bsContractService.selectBsContractList(bsContract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @RequiresPermissions("system:contract:export")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsContract bsContract) {
        List<BsContract> list = bsContractService.selectBsContractList(bsContract);
        ExcelUtil<BsContract> util = new ExcelUtil<BsContract>(BsContract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @RequiresPermissions("system:contract:query")
    @GetMapping(value = "/{eid}")
    public AjaxResult getInfo(@PathVariable("eid") Long eid) {
        BsContract bsContract = bsContractService.selectBidTenderSid(eid);
        System.out.println(bsContract);
        return success(bsContract);
    }

    /**
     * 新增合同
     */
    @RequiresPermissions("system:contract:add")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsContract bsContract) {
        System.out.println(bsContract);
        CodeRulesResult result = CodeRuleHelp.createCode(codeRulesService, CodeRuleUtil.CONTROLLER);
        //获取已经匹配的规则
        String eHcode = result.getCode();
        bsContract.seteHcode(eHcode);
        bsContract.setCreateBy("欧");
        bsContract.seteStatus(2L);
        bsContract.setoHstatus(2L);
        //创建合同
        int k = bsContractService.insertBsContract(bsContract);
        if (k > 0) {
            Long eid = bsContract.getEid();
            BidTender bidTender = new BidTender();
            bidTender.setEid(eid);
            bidTender.setSid(bsContract.getSid());
            int i1 = bsContractService.updateBidTenderEid(bidTender);
            if (i1 == 0) {
                return AjaxResult.error("添加异常");
            }
            //添加设备信息
            List<BsInventory> list1 = bsContract.getBsInventoryList();
            if (list1 != null) {
                for (BsInventory bsInventory : list1) {
                    bsInventory.setEid(eid);
                    int i = inventoryService.insertBsInventory(bsInventory);
                    if (i == 0) {
                        return AjaxResult.error("添加异常");
                    }
                }
            }
            List<BsPayment> list2 = bsContract.getBsPaymentList();
            if (list2 != null) {
                for (BsPayment bsPayment : list2) {
                    bsPayment.setEid(eid);
                    int i = paymentService.insertBsPayment(bsPayment);
                    if (i == 0) {
                        return AjaxResult.error("添加异常");
                    }
                }
            }
            BsSign bsSign = bsContract.getBsSign();
            bsSign.setEid(eid);
            int i = signService.insertBsSign(bsSign);
            if (i == 0) {
                return AjaxResult.error("添加异常");
            }
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加异常");
    }

    /**
     * 修改合同
     */
    @RequiresPermissions("system:contract:edit")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsContract bsContract) {
        return toAjax(bsContractService.updateBsContract(bsContract));
    }

    /**
     * 删除合同
     */
    @RequiresPermissions("system:contract:remove")
    @Log(title = "合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{eids}")
    public AjaxResult remove(@PathVariable Long[] eids) {
        return toAjax(bsContractService.deleteBsContractByEids(eids));
    }
}
