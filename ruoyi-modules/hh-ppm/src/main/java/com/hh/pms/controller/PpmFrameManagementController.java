package com.hh.pms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.domain.PpmFrameManagement;
import com.hh.pms.service.IPpmFrameManagementService;
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
 * 框架协议管理Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/management")
public class PpmFrameManagementController extends BaseController
{
    @Autowired
    private IPpmFrameManagementService ppmFrameManagementService;

    /**
     * 查询框架协议管理列表
     */
    @RequiresPermissions("system:management:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmFrameManagement ppmFrameManagement)
    {
        startPage();
        List<PpmFrameManagement> list = ppmFrameManagementService.selectPpmFrameManagementList(ppmFrameManagement);
        return getDataTable(list);
    }

    /**
     * 导出框架协议管理列表
     */
    @RequiresPermissions("system:management:export")
    @Log(title = "框架协议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmFrameManagement ppmFrameManagement)
    {
        List<PpmFrameManagement> list = ppmFrameManagementService.selectPpmFrameManagementList(ppmFrameManagement);
        ExcelUtil<PpmFrameManagement> util = new ExcelUtil<PpmFrameManagement>(PpmFrameManagement.class);
        util.exportExcel(response, list, "框架协议管理数据");
    }

    /**
     * 获取框架协议管理详细信息
     */
    @RequiresPermissions("system:management:query")
    @GetMapping(value = "/{oid}")
    public AjaxResult getInfo(@PathVariable("oid") Long oid)
    {
        return success(ppmFrameManagementService.selectPpmFrameManagementByOid(oid));
    }

    /**
     * 新增框架协议管理
     */
    @RequiresPermissions("system:management:add")
    @Log(title = "框架协议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmFrameManagement ppmFrameManagement)
    {
        return toAjax(ppmFrameManagementService.insertPpmFrameManagement(ppmFrameManagement));
    }

    /**
     * 修改框架协议管理
     */
    @RequiresPermissions("system:management:edit")
    @Log(title = "框架协议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmFrameManagement ppmFrameManagement)
    {
        return toAjax(ppmFrameManagementService.updatePpmFrameManagement(ppmFrameManagement));
    }

    /**
     * 删除框架协议管理
     */
    @RequiresPermissions("system:management:remove")
    @Log(title = "框架协议管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oids}")
    public AjaxResult remove(@PathVariable Long[] oids)
    {
        return toAjax(ppmFrameManagementService.deletePpmFrameManagementByOids(oids));
    }


}
