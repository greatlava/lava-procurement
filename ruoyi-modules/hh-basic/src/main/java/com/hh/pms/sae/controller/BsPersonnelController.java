package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsPersonnel;
import com.hh.pms.sae.service.IBsPersonnelService;
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
 * 核心技术人员Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/personnel")
public class BsPersonnelController extends BaseController {
    @Autowired
    private IBsPersonnelService bsPersonnelService;

    /**
     * 查询核心技术人员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsPersonnel bsPersonnel) {
        startPage();
        List<BsPersonnel> list = bsPersonnelService.selectBsPersonnelList(bsPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出核心技术人员列表
     */
    @RequiresPermissions("system:personnel:export")
    @Log(title = "核心技术人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsPersonnel bsPersonnel) {
        List<BsPersonnel> list = bsPersonnelService.selectBsPersonnelList(bsPersonnel);
        ExcelUtil<BsPersonnel> util = new ExcelUtil<BsPersonnel>(BsPersonnel.class);
        util.exportExcel(response, list, "核心技术人员数据");
    }

    /**
     * 获取核心技术人员详细信息
     */
    @GetMapping(value = "/{jsId}")
    public AjaxResult getInfo(@PathVariable("jsId") Long jsId) {
        return success(bsPersonnelService.selectBsPersonnelByJsId(jsId));
    }

    /**
     * 新增核心技术人员
     */
    @Log(title = "核心技术人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsPersonnel bsPersonnel) {
        return toAjax(bsPersonnelService.insertBsPersonnel(bsPersonnel));
    }

    /**
     * 修改核心技术人员
     */
    @Log(title = "核心技术人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsPersonnel bsPersonnel) {
        return toAjax(bsPersonnelService.updateBsPersonnel(bsPersonnel));
    }

    /**
     * 删除核心技术人员
     */
    @Log(title = "核心技术人员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jsIds}")
    public AjaxResult remove(@PathVariable Long[] jsIds) {
        return toAjax(bsPersonnelService.deleteBsPersonnelByJsIds(jsIds));
    }
}
