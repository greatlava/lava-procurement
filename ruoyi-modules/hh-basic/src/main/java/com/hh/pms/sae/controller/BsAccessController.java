package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsAccess;
import com.hh.pms.sae.service.IBsAccessService;
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
 * 供应商准入Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/access")
public class BsAccessController extends BaseController {
    @Autowired
    private IBsAccessService bsAccessService;

    /**
     * 查询供应商准入列表
     */
    @RequiresPermissions("system:supplier:list")
    @GetMapping("/list")
    public TableDataInfo list(BsAccess bsAccess) {
        startPage();
        List<BsAccess> list = bsAccessService.selectBsAccessList(bsAccess);
        return getDataTable(list);
    }

    /**
     * 导出供应商准入列表
     */
    @Log(title = "供应商准入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsAccess bsAccess) {
        List<BsAccess> list = bsAccessService.selectBsAccessList(bsAccess);
        ExcelUtil<BsAccess> util = new ExcelUtil<BsAccess>(BsAccess.class);
        util.exportExcel(response, list, "供应商准入数据");
    }

    /**
     * 获取供应商准入详细信息
     */
    @GetMapping(value = "/{zrId}")
    public AjaxResult getInfo(@PathVariable("zrId") Long zrId) {
        return success(bsAccessService.selectBsAccessByZrId(zrId));
    }

    /**
     * 新增供应商准入
     */
    @Log(title = "供应商准入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsAccess bsAccess) {
        return null;
    }

    /**
     * 修改供应商准入
     */
    @Log(title = "供应商准入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsAccess bsAccess) {
        return toAjax(bsAccessService.updateBsAccess(bsAccess));
    }

    /**
     * 删除供应商准入
     */
    @Log(title = "供应商准入", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zrIds}")
    public AjaxResult remove(@PathVariable Long[] zrIds) {
        return toAjax(bsAccessService.deleteBsAccessByZrIds(zrIds));
    }
}
