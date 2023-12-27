package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsEnterprise;
import com.hh.pms.sae.service.IBsEnterpriseService;
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
 * 企业资质Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/enterprise")
public class BsEnterpriseController extends BaseController {
    @Autowired
    private IBsEnterpriseService bsEnterpriseService;

    /**
     * 查询企业资质列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsEnterprise bsEnterprise) {
        startPage();
        List<BsEnterprise> list = bsEnterpriseService.selectBsEnterpriseList(bsEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业资质列表
     */
    @RequiresPermissions("system:enterprise:export")
    @Log(title = "企业资质", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsEnterprise bsEnterprise) {
        List<BsEnterprise> list = bsEnterpriseService.selectBsEnterpriseList(bsEnterprise);
        ExcelUtil<BsEnterprise> util = new ExcelUtil<BsEnterprise>(BsEnterprise.class);
        util.exportExcel(response, list, "企业资质数据");
    }

    /**
     * 获取企业资质详细信息
     */
    @RequiresPermissions("system:enterprise:query")
    @GetMapping(value = "/{zzId}")
    public AjaxResult getInfo(@PathVariable("zzId") Long zzId) {
        return success(bsEnterpriseService.selectBsEnterpriseByZzId(zzId));
    }

    /**
     * 新增企业资质
     */
    @RequiresPermissions("system:enterprise:add")
    @Log(title = "企业资质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsEnterprise bsEnterprise) {
        return toAjax(bsEnterpriseService.insertBsEnterprise(bsEnterprise));
    }

    /**
     * 修改企业资质
     */
    @RequiresPermissions("system:enterprise:edit")
    @Log(title = "企业资质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsEnterprise bsEnterprise) {
        return toAjax(bsEnterpriseService.updateBsEnterprise(bsEnterprise));
    }

    /**
     * 删除企业资质
     */
    @RequiresPermissions("system:enterprise:remove")
    @Log(title = "企业资质", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zzIds}")
    public AjaxResult remove(@PathVariable Long[] zzIds) {
        return toAjax(bsEnterpriseService.deleteBsEnterpriseByZzIds(zzIds));
    }
}
