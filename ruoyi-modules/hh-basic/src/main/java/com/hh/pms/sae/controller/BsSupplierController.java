package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.hh.pms.sae.domain.BsSupplier;
import com.hh.pms.sae.service.IBsSupplierService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 供应商Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/supplier")
public class BsSupplierController extends BaseController {
    @Autowired
    private IBsSupplierService bsSupplierService;

    /**
     * 查询供应商列表
     */
    @RequiresPermissions("system:supplier:list")
    @GetMapping("/list")
    public TableDataInfo list(BsSupplier bsSupplier) {
        startPage();
        List<BsSupplier> list = bsSupplierService.selectBsSupplierList(bsSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @RequiresPermissions("system:supplier:export")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsSupplier bsSupplier) {
        List<BsSupplier> list = bsSupplierService.selectBsSupplierList(bsSupplier);
        ExcelUtil<BsSupplier> util = new ExcelUtil<BsSupplier>(BsSupplier.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @RequiresPermissions("system:supplier:query")
    @GetMapping(value = "/{hid}")
    public AjaxResult getInfo(@PathVariable("hid") Long hid) {
        return success(bsSupplierService.selectBsSupplierByHid(hid));
    }

    /**
     * 新增供应商
     */
    @RequiresPermissions("system:supplier:add")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsSupplier bsSupplier) {
        return toAjax(bsSupplierService.insertBsSupplier(bsSupplier));
    }

    /**
     * 修改供应商
     */
    @RequiresPermissions("system:supplier:edit")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsSupplier bsSupplier) {
        return toAjax(bsSupplierService.updateBsSupplier(bsSupplier));
    }

    /**
     * 删除供应商
     */
    @RequiresPermissions("system:supplier:remove")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{hids}")
    public AjaxResult remove(@PathVariable Long[] hids) {
        return toAjax(bsSupplierService.deleteBsSupplierByHids(hids));
    }
}
