package com.hh.pms.cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.hh.pms.cm.domain.*;
import com.hh.pms.cm.service.IBsInventoryService;
import com.hh.pms.cm.service.IComCodeRulesService;
import com.hh.pms.cm.service.impl.ComCodeRulesServiceImpl;
import com.hh.pms.cm.util.CodeRuleHelp;
import com.hh.pms.cm.util.CodeRuleUtil;
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
import com.hh.pms.cm.service.IBSFrameManagementService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 框架协议管理Controller
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/management")
@Transactional
public class BSFrameManagementController extends BaseController {
    @Autowired
    private IBSFrameManagementService bSFrameManagementService;

    @Autowired
    private IComCodeRulesService codeRulesService;

    @Autowired
    private IBsInventoryService inventoryService;

    /**
     * 查询框架协议管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BSFrameManagement bSFrameManagement) {
        startPage();
        List<BSFrameManagement> list = bSFrameManagementService.selectBSFrameManagementList(bSFrameManagement);
        return getDataTable(list);
    }

    /**
     * 导出框架协议管理列表
     */
    @Log(title = "框架协议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BSFrameManagement bSFrameManagement) {
        List<BSFrameManagement> list = bSFrameManagementService.selectBSFrameManagementList(bSFrameManagement);
        ExcelUtil<BSFrameManagement> util = new ExcelUtil<BSFrameManagement>(BSFrameManagement.class);
        util.exportExcel(response, list, "框架协议管理数据");
    }

    /**
     * 获取框架协议管理详细信息
     */
    @GetMapping(value = "/{oid}")
    public AjaxResult getInfo(@PathVariable("oid") Long oid) {
        return success(bSFrameManagementService.selectBSFrameManagementByOid(oid));
    }

    /**
     * 新增框架协议管理
     */
    @Log(title = "框架协议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BSFrameManagement bSFrameManagement) {
        System.out.println(bSFrameManagement);
        //创建编码
        CodeRulesResult result = CodeRuleHelp.createCode(codeRulesService, CodeRuleUtil.FRAMEWORK_MANAHEMENT);
        //获取已经匹配的规则
        String oCode = result.getCode();
        //生成协议编号
        bSFrameManagement.setoCode(oCode);
        //协议创建人
        bSFrameManagement.setCreateBy("欧");
        int k = bSFrameManagementService.insertBSFrameManagement(bSFrameManagement);
        if (k != 0) {
            long oid = bSFrameManagement.getOid();
            List<BsInventory> list = bSFrameManagement.getBsInventoryList();
            for (BsInventory bsInventory : list) {
                bsInventory.setOid(oid);
                inventoryService.insertBsInventory(bsInventory);
            }
            BSFrameManagement frameManagement = bSFrameManagementService.selectBSFrameManagementByOid(oid);

            JSONObject object=new JSONObject();
            object.put("frameManagement",frameManagement);
            return AjaxResult.success(object);
        }
        return AjaxResult.error("添加异常");
    }

    /**
     * 修改框架协议管理
     */
    @Log(title = "框架协议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BSFrameManagement bSFrameManagement) {
        System.out.println("打印bSFrameManagement--------------------------------------");
        System.out.println(bSFrameManagement);
        int k = bSFrameManagementService.updateBSFrameManagement(bSFrameManagement);
        if (k > 0) {
            if (bSFrameManagement.getoOpinion() != null) {
                return AjaxResult.success("修改成功");
            }
            Long oid = bSFrameManagement.getOid();
            //删除设备信息
            inventoryService.deleteBsInventoryByOid(oid);
            //修改设备信息
            List<BsInventory> list = bSFrameManagement.getBsInventoryList();
            for (BsInventory bsInventory : list) {
                bsInventory.setOid(oid);
                int i = inventoryService.insertBsInventory(bsInventory);
                if (i == 0) {
                    return AjaxResult.error("修改异常");
                }
            }
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("添加异常");
//        return toAjax(bSFrameManagementService.updateBSFrameManagement(bSFrameManagement));
    }

    /**
     * 删除框架协议管理
     */
    @GetMapping("/delXy")
    @Transactional
    public AjaxResult remove(Long oid) {
        System.out.println(oid);
        int i = inventoryService.deleteBsInventoryByOid(oid);
        if (i > 0) {
            int i1 = bSFrameManagementService.deleteBSFrameManagementByOid(oid);
            if (i1 > 0) {
                return AjaxResult.success("删除成功");
            }
        }
        return AjaxResult.error("删除失败");
    }


    //协议作废
    @GetMapping("/XyCancel")
    public AjaxResult XyCancel(Long oid) {
        int i = bSFrameManagementService.updateXyCancel(oid);
        if (i > 0) {
            BSFrameManagement frameManagement = new BSFrameManagement();
            frameManagement.setOid(oid);
            frameManagement.setoHstatus(5L);
            int i1 = bSFrameManagementService.updateBSFrameManagement(frameManagement);
            if (i1 > 0) {
                return AjaxResult.success("修改成功");
            }
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.error("修改失败");
    }
}
