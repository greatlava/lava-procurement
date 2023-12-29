package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsOccupation;
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
import com.hh.pms.sae.service.IBsOccupationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 职业Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/occupation")
public class BsOccupationController extends BaseController {
    @Autowired
    private IBsOccupationService bsOccupationService;

    /**
     * 查询职业列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsOccupation bsOccupation) {
        startPage();
        List<BsOccupation> list = bsOccupationService.selectBsOccupationList(bsOccupation);
        return getDataTable(list);
    }

    /**
     * 导出职业列表
     */
    @RequiresPermissions("system:occupation:export")
    @Log(title = "职业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsOccupation bsOccupation) {
        List<BsOccupation> list = bsOccupationService.selectBsOccupationList(bsOccupation);
        ExcelUtil<BsOccupation> util = new ExcelUtil<BsOccupation>(BsOccupation.class);
        util.exportExcel(response, list, "职业数据");
    }

    /**
     * 获取职业详细信息
     */
    @RequiresPermissions("system:occupation:query")
    @GetMapping(value = "/{nid}")
    public AjaxResult getInfo(@PathVariable("nid") Long nid) {
        return success(bsOccupationService.selectBsOccupationByNid(nid));
    }

    /**
     * 新增职业
     */
    @RequiresPermissions("system:occupation:add")
    @Log(title = "职业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsOccupation bsOccupation) {
        return toAjax(bsOccupationService.insertBsOccupation(bsOccupation));
    }

    /**
     * 修改职业
     */
    @RequiresPermissions("system:occupation:edit")
    @Log(title = "职业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsOccupation bsOccupation) {
        return toAjax(bsOccupationService.updateBsOccupation(bsOccupation));
    }

    /**
     * 删除职业
     */
    @RequiresPermissions("system:occupation:remove")
    @Log(title = "职业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{nids}")
    public AjaxResult remove(@PathVariable Long[] nids) {
        return toAjax(bsOccupationService.deleteBsOccupationByNids(nids));
    }
}
