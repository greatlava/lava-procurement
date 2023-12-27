package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsAccessories;
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
import com.hh.pms.sae.service.IBsAccessoriesService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 相关附件Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/accessories")
public class BsAccessoriesController extends BaseController {
    @Autowired
    private IBsAccessoriesService bsAccessoriesService;

    /**
     * 查询相关附件列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsAccessories bsAccessories) {
        startPage();
        List<BsAccessories> list = bsAccessoriesService.selectBsAccessoriesList(bsAccessories);
        return getDataTable(list);
    }

    /**
     * 导出相关附件列表
     */
    @RequiresPermissions("system:accessories:export")
    @Log(title = "相关附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsAccessories bsAccessories) {
        List<BsAccessories> list = bsAccessoriesService.selectBsAccessoriesList(bsAccessories);
        ExcelUtil<BsAccessories> util = new ExcelUtil<BsAccessories>(BsAccessories.class);
        util.exportExcel(response, list, "相关附件数据");
    }

    /**
     * 获取相关附件详细信息
     */
    @RequiresPermissions("system:accessories:query")
    @GetMapping(value = "/{fjId}")
    public AjaxResult getInfo(@PathVariable("fjId") Long fjId) {
        return success(bsAccessoriesService.selectBsAccessoriesByFjId(fjId));
    }

    /**
     * 新增相关附件
     */
    @RequiresPermissions("system:accessories:add")
    @Log(title = "相关附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsAccessories bsAccessories) {
        return toAjax(bsAccessoriesService.insertBsAccessories(bsAccessories));
    }

    /**
     * 修改相关附件
     */
    @RequiresPermissions("system:accessories:edit")
    @Log(title = "相关附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsAccessories bsAccessories) {
        return toAjax(bsAccessoriesService.updateBsAccessories(bsAccessories));
    }

    /**
     * 删除相关附件
     */
    @RequiresPermissions("system:accessories:remove")
    @Log(title = "相关附件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fjIds}")
    public AjaxResult remove(@PathVariable Long[] fjIds) {
        return toAjax(bsAccessoriesService.deleteBsAccessoriesByFjIds(fjIds));
    }
}
