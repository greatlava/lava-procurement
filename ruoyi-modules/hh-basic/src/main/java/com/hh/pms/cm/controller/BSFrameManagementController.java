package com.hh.pms.cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.cm.domain.CodeRulesResult;
import com.hh.pms.cm.domain.ComCodeRules;
import com.hh.pms.cm.service.IComCodeRulesService;
import com.hh.pms.cm.service.impl.ComCodeRulesServiceImpl;
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
import com.hh.pms.cm.domain.BSFrameManagement;
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
public class BSFrameManagementController extends BaseController {
    @Autowired
    private IBSFrameManagementService bSFrameManagementService;

    @Autowired
    private IComCodeRulesService codeRulesService;

    /**
     * 查询框架协议管理列表
     */
    @RequiresPermissions("system:management:list")
    @GetMapping("/list")
    public TableDataInfo list(BSFrameManagement bSFrameManagement) {
        startPage();
        List<BSFrameManagement> list = bSFrameManagementService.selectBSFrameManagementList(bSFrameManagement);
        return getDataTable(list);
    }

    /**
     * 导出框架协议管理列表
     */
    @RequiresPermissions("system:management:export")
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
    @RequiresPermissions("system:management:query")
    @GetMapping(value = "/{oid}")
    public AjaxResult getInfo(@PathVariable("oid") Long oid) {
        return success(bSFrameManagementService.selectBSFrameManagementByOid(oid));
    }

    /**
     * 新增框架协议管理
     */
    @RequiresPermissions("system:management:add")
    @Log(title = "框架协议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BSFrameManagement bSFrameManagement) {

        CodeRulesResult result = CodeRuleHelp.createCode(codeRulesService, CodeRuleUtil.FRAMEWORK_MANAHEMENT);

        //获取已经匹配的规则
        String oCode = result.getCode();
        BSFrameManagement management = new BSFrameManagement();
        management.setoCode(oCode);
        System.out.println(oCode);
        return null;
//        return toAjax(bSFrameManagementService.insertBSFrameManagement(bSFrameManagement));
    }

    /**
     * 修改框架协议管理
     */
    @RequiresPermissions("system:management:edit")
    @Log(title = "框架协议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BSFrameManagement bSFrameManagement) {
        return toAjax(bSFrameManagementService.updateBSFrameManagement(bSFrameManagement));
    }

    /**
     * 删除框架协议管理
     */
    @RequiresPermissions("system:management:remove")
    @Log(title = "框架协议管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{oids}")
    public AjaxResult remove(@PathVariable Long[] oids) {
        return toAjax(bSFrameManagementService.deleteBSFrameManagementByOids(oids));
    }
}
