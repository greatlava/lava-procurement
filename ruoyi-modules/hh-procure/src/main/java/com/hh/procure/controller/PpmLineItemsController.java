package com.hh.procure.controller;

import com.hh.procure.domain.PpmLineItems;
import com.hh.procure.service.IPpmLineItemsService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 行项目Controller
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
@RestController
@RequestMapping("/items")
public class PpmLineItemsController extends BaseController
{
    @Autowired
    private IPpmLineItemsService ppmLineItemsService;

    /**
     * 查询行项目列表
     */
    @RequiresPermissions("system:items:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmLineItems ppmLineItems)
    {
        startPage();
        List<PpmLineItems> list = ppmLineItemsService.selectPpmLineItemsList(ppmLineItems);
        return getDataTable(list);
    }

    /**
     * 导出行项目列表
     */
    @RequiresPermissions("system:items:export")
    @Log(title = "行项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmLineItems ppmLineItems)
    {
        List<PpmLineItems> list = ppmLineItemsService.selectPpmLineItemsList(ppmLineItems);
        ExcelUtil<PpmLineItems> util = new ExcelUtil<PpmLineItems>(PpmLineItems.class);
        util.exportExcel(response, list, "行项目数据");
    }

    /**
     * 获取行项目详细信息
     */
    @RequiresPermissions("system:items:query")
    @GetMapping(value = "/{vid}")
    public AjaxResult getInfo(@PathVariable("vid") Integer vid)
    {
        return success(ppmLineItemsService.selectPpmLineItemsByVid(vid));
    }

    /**
     * 新增行项目
     */
    @RequiresPermissions("system:items:add")
    @Log(title = "行项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmLineItems ppmLineItems)
    {
        return toAjax(ppmLineItemsService.insertPpmLineItems(ppmLineItems));
    }

    /**
     * 修改行项目
     */
    @RequiresPermissions("system:items:edit")
    @Log(title = "行项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmLineItems ppmLineItems)
    {
        return toAjax(ppmLineItemsService.updatePpmLineItems(ppmLineItems));
    }

    /**
     * 删除行项目
     */
    @RequiresPermissions("system:items:remove")
    @Log(title = "行项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vids}")
    public AjaxResult remove(@PathVariable Integer[] vids)
    {
        return toAjax(ppmLineItemsService.deletePpmLineItemsByVids(vids));
    }

    //查询框架计划内的设备信息
    @GetMapping("/selectItemsDevice")
    public TableDataInfo selectItemsDevice(Long jhId) {
        return getDataTable(ppmLineItemsService.selectItemsDevice(jhId));
    }
}
