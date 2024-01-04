package com.hh.pms.cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.hh.pms.cm.domain.BsInventory;
import com.hh.pms.cm.service.IBsInventoryService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/inventory")
public class BsInventoryController extends BaseController
{
    @Autowired
    private IBsInventoryService bsInventoryService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsInventory bsInventory)
    {
        startPage();
        List<BsInventory> list = bsInventoryService.selectBsInventoryList(bsInventory);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsInventory bsInventory)
    {
        List<BsInventory> list = bsInventoryService.selectBsInventoryList(bsInventory);
        ExcelUtil<BsInventory> util = new ExcelUtil<BsInventory>(BsInventory.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{inId}")
    public AjaxResult getInfo(@PathVariable("inId") Long inId)
    {
        return success(bsInventoryService.selectBsInventoryByInId(inId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsInventory bsInventory)
    {
        return toAjax(bsInventoryService.insertBsInventory(bsInventory));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsInventory bsInventory)
    {
        return toAjax(bsInventoryService.updateBsInventory(bsInventory));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inIds}")
    public AjaxResult remove(@PathVariable Long[] inIds)
    {
        return toAjax(bsInventoryService.deleteBsInventoryByInIds(inIds));
    }
}
