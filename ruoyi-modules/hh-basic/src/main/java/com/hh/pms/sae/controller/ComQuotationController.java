package com.hh.pms.sae.controller;

import java.util.List;
import java.io.IOException;
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
import com.hh.pms.sae.domain.ComQuotation;
import com.hh.pms.sae.service.IComQuotationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 报价单Controller
 *
 * @author ruoyi
 * @date 2023-12-20
 */
@RestController
@RequestMapping("/quotation")
public class ComQuotationController extends BaseController {
    @Autowired
    private IComQuotationService comQuotationService;

    /**
     * 查询报价单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ComQuotation comQuotation) {
        startPage();
        List<ComQuotation> list = comQuotationService.selectComQuotationList(comQuotation);
        return getDataTable(list);
    }

    /**
     * 导出报价单列表
     */
    @Log(title = "报价单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ComQuotation comQuotation) {
        List<ComQuotation> list = comQuotationService.selectComQuotationList(comQuotation);
        ExcelUtil<ComQuotation> util = new ExcelUtil<ComQuotation>(ComQuotation.class);
        util.exportExcel(response, list, "报价单数据");
    }

    /**
     * 获取报价单详细信息
     */
    @GetMapping(value = "/{bjId}")
    public AjaxResult getInfo(@PathVariable("bjId") Long bjId) {
        return success(comQuotationService.selectComQuotationByBjId(bjId));
    }

    /**
     * 新增报价单
     */
    @Log(title = "报价单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComQuotation comQuotation) {
        return toAjax(comQuotationService.insertComQuotation(comQuotation));
    }

    /**
     * 修改报价单
     */
    @Log(title = "报价单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComQuotation comQuotation) {
        return toAjax(comQuotationService.updateComQuotation(comQuotation));
    }

    /**
     * 删除报价单
     */
    @Log(title = "报价单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bjIds}")
    public AjaxResult remove(@PathVariable Long[] bjIds) {
        return toAjax(comQuotationService.deleteComQuotationByBjIds(bjIds));
    }

    @GetMapping("/oneQuo")
    public AjaxResult oneQuo(String gfId,Long bjHid) {
        return success(comQuotationService.oneQuotation(gfId,bjHid));
    }

    @GetMapping("/getBjCount")
    public AjaxResult getBjCount(String gfId) {
        return success(comQuotationService.selectBjCount(gfId));
    }
}

