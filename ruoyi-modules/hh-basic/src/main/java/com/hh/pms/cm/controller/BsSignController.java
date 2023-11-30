package com.hh.pms.cm.controller;

import com.hh.pms.cm.domain.BsSign;
import com.hh.pms.cm.service.IBsSignService;
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
 * 签署执行状态Controller
 *
 * @author ruoyi
 * @date 2023-11-30
 */
@RestController
@RequestMapping("/sign")
public class BsSignController extends BaseController
{
    @Autowired
    private IBsSignService bsSignService;

    /**
     * 查询签署执行状态列表
     */
    @RequiresPermissions("system:sign:list")
    @GetMapping("/list")
    public TableDataInfo list(BsSign bsSign)
    {
        startPage();
        List<BsSign> list = bsSignService.selectBsSignList(bsSign);
        return getDataTable(list);
    }

    /**
     * 导出签署执行状态列表
     */
    @RequiresPermissions("system:sign:export")
    @Log(title = "签署执行状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsSign bsSign)
    {
        List<BsSign> list = bsSignService.selectBsSignList(bsSign);
        ExcelUtil<BsSign> util = new ExcelUtil<BsSign>(BsSign.class);
        util.exportExcel(response, list, "签署执行状态数据");
    }

    /**
     * 获取签署执行状态详细信息
     */
    @RequiresPermissions("system:sign:query")
    @GetMapping(value = "/{gnId}")
    public AjaxResult getInfo(@PathVariable("gnId") Long gnId)
    {
        return success(bsSignService.selectBsSignByGnId(gnId));
    }

    /**
     * 新增签署执行状态
     */
    @RequiresPermissions("system:sign:add")
    @Log(title = "签署执行状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsSign bsSign)
    {
        return toAjax(bsSignService.insertBsSign(bsSign));
    }

    /**
     * 修改签署执行状态
     */
    @RequiresPermissions("system:sign:edit")
    @Log(title = "签署执行状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsSign bsSign)
    {
        return toAjax(bsSignService.updateBsSign(bsSign));
    }

    /**
     * 删除签署执行状态
     */
    @RequiresPermissions("system:sign:remove")
    @Log(title = "签署执行状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{gnIds}")
    public AjaxResult remove(@PathVariable Long[] gnIds)
    {
        return toAjax(bsSignService.deleteBsSignByGnIds(gnIds));
    }
}