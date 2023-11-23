package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsApplications;
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
import com.hh.pms.sae.service.IBsApplicationsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 抽取申请Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/applications")
public class BsApplicationsController extends BaseController {
    @Autowired
    private IBsApplicationsService bsApplicationsService;

    /**
     * 查询抽取申请列表
     */
    @RequiresPermissions("system:applications:list")
    @GetMapping("/list")
    public TableDataInfo list(BsApplications bsApplications) {
        startPage();
        List<BsApplications> list = bsApplicationsService.selectBsApplicationsList(bsApplications);
        return getDataTable(list);
    }

    /**
     * 导出抽取申请列表
     */
    @RequiresPermissions("system:applications:export")
    @Log(title = "抽取申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsApplications bsApplications) {
        List<BsApplications> list = bsApplicationsService.selectBsApplicationsList(bsApplications);
        ExcelUtil<BsApplications> util = new ExcelUtil<BsApplications>(BsApplications.class);
        util.exportExcel(response, list, "抽取申请数据");
    }

    /**
     * 获取抽取申请详细信息
     */
    @RequiresPermissions("system:applications:query")
    @GetMapping(value = "/{xid}")
    public AjaxResult getInfo(@PathVariable("xid") Long xid) {
        return success(bsApplicationsService.selectBsApplicationsByXid(xid));
    }

    /**
     * 新增抽取申请
     */
    @RequiresPermissions("system:applications:add")
    @Log(title = "抽取申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsApplications bsApplications) {
        return toAjax(bsApplicationsService.insertBsApplications(bsApplications));
    }

    /**
     * 修改抽取申请
     */
    @RequiresPermissions("system:applications:edit")
    @Log(title = "抽取申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsApplications bsApplications) {
        return toAjax(bsApplicationsService.updateBsApplications(bsApplications));
    }

    /**
     * 删除抽取申请
     */
    @RequiresPermissions("system:applications:remove")
    @Log(title = "抽取申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xids}")
    public AjaxResult remove(@PathVariable Long[] xids) {
        return toAjax(bsApplicationsService.deleteBsApplicationsByXids(xids));
    }
}
