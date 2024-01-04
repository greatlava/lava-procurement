package com.hh.pms.cm.controller;

import com.hh.pms.cm.domain.BsPayment;
import com.hh.pms.cm.service.IBsPaymentService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 支付约定Controller
 *
 * @author ruoyi
 * @date 2023-11-30
 */
@RestController
@RequestMapping("/payment")
public class BsPaymentController extends BaseController
{
    @Autowired
    private IBsPaymentService bsPaymentService;

    /**
     * 查询支付约定列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsPayment bsPayment)
    {
        startPage();
        List<BsPayment> list = bsPaymentService.selectBsPaymentList(bsPayment);
        return getDataTable(list);
    }

    /**
     * 导出支付约定列表
     */
    @Log(title = "支付约定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsPayment bsPayment)
    {
        List<BsPayment> list = bsPaymentService.selectBsPaymentList(bsPayment);
        ExcelUtil<BsPayment> util = new ExcelUtil<BsPayment>(BsPayment.class);
        util.exportExcel(response, list, "支付约定数据");
    }

    /**
     * 获取支付约定详细信息
     */
    @GetMapping(value = "/{payId}")
    public AjaxResult getInfo(@PathVariable("payId") Long payId)
    {
        return success(bsPaymentService.selectBsPaymentByPayId(payId));
    }

    /**
     * 新增支付约定
     */
    @Log(title = "支付约定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsPayment bsPayment)
    {
        return toAjax(bsPaymentService.insertBsPayment(bsPayment));
    }

    /**
     * 修改支付约定
     */
    @Log(title = "支付约定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsPayment bsPayment)
    {
        return toAjax(bsPaymentService.updateBsPayment(bsPayment));
    }

    /**
     * 删除支付约定
     */
    @Log(title = "支付约定", businessType = BusinessType.DELETE)
    @DeleteMapping("/{payIds}")
    public AjaxResult remove(@PathVariable Long[] payIds)
    {
        return toAjax(bsPaymentService.deleteBsPaymentByPayIds(payIds));
    }
}
