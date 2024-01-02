package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsOperator;
import com.hh.pms.sae.service.IBsOperatorService;
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
 * 业务经办人信息Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/operator")
public class BsOperatorController extends BaseController {
    @Autowired
    private IBsOperatorService bsOperatorService;

    /**
     * 查询业务经办人信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsOperator bsOperator) {
        startPage();
        List<BsOperator> list = bsOperatorService.selectBsOperatorList(bsOperator);
        return getDataTable(list);
    }

    /**
     * 导出业务经办人信息列表
     */
    @RequiresPermissions("system:operator:export")
    @Log(title = "业务经办人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsOperator bsOperator) {
        List<BsOperator> list = bsOperatorService.selectBsOperatorList(bsOperator);
        ExcelUtil<BsOperator> util = new ExcelUtil<BsOperator>(BsOperator.class);
        util.exportExcel(response, list, "业务经办人信息数据");
    }

    /**
     * 获取业务经办人信息详细信息
     */
    @GetMapping(value = "/{ywId}")
    public AjaxResult getInfo(@PathVariable("ywId") Long ywId) {
        return success(bsOperatorService.selectBsOperatorByYwId(ywId));
    }

    @GetMapping(value = "/getInfoByHid/{hid}")
    public AjaxResult getInfoByHid(@PathVariable("hid") Long hid) {
        return success(bsOperatorService.selectBsOperatorByHid(hid));
    }

    /**
     * 新增业务经办人信息
     */
    @Log(title = "业务经办人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsOperator bsOperator) {
        return toAjax(bsOperatorService.insertBsOperator(bsOperator));
    }

    /**
     * 修改业务经办人信息
     */
    @Log(title = "业务经办人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsOperator bsOperator) {
        return toAjax(bsOperatorService.updateBsOperator(bsOperator));
    }

    /**
     * 删除业务经办人信息
     */
    @Log(title = "业务经办人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ywIds}")
    public AjaxResult remove(@PathVariable Long[] ywIds) {
        return toAjax(bsOperatorService.deleteBsOperatorByYwIds(ywIds));
    }
}
