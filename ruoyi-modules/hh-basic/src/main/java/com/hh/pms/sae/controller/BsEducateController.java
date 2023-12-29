package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsEducate;
import com.hh.pms.sae.service.IBsEducateService;
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
 * 教育Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/educate")
public class BsEducateController extends BaseController {
    @Autowired
    private IBsEducateService bsEducateService;

    /**
     * 查询教育列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsEducate bsEducate) {
        startPage();
        List<BsEducate> list = bsEducateService.selectBsEducateList(bsEducate);
        return getDataTable(list);
    }

    /**
     * 导出教育列表
     */
    @RequiresPermissions("system:educate:export")
    @Log(title = "教育", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsEducate bsEducate) {
        List<BsEducate> list = bsEducateService.selectBsEducateList(bsEducate);
        ExcelUtil<BsEducate> util = new ExcelUtil<BsEducate>(BsEducate.class);
        util.exportExcel(response, list, "教育数据");
    }

    /**
     * 获取教育详细信息
     */
    @RequiresPermissions("system:educate:query")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid) {
        return success(bsEducateService.selectBsEducateByRid(rid));
    }

    /**
     * 新增教育
     */
    @RequiresPermissions("system:educate:add")
    @Log(title = "教育", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsEducate bsEducate) {
        return toAjax(bsEducateService.insertBsEducate(bsEducate));
    }

    /**
     * 修改教育
     */
    @RequiresPermissions("system:educate:edit")
    @Log(title = "教育", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsEducate bsEducate) {
        return toAjax(bsEducateService.updateBsEducate(bsEducate));
    }

    /**
     * 删除教育
     */
    @RequiresPermissions("system:educate:remove")
    @Log(title = "教育", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids) {
        return toAjax(bsEducateService.deleteBsEducateByRids(rids));
    }
}
