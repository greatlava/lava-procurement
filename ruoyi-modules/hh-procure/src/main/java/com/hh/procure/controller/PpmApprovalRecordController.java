package com.hh.procure.controller;

import com.hh.procure.domain.PpmApprovalRecord;
import com.hh.procure.service.IPpmApprovalRecordService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 审批记录Controller
 *
 * @author ruoyi
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/record")
public class PpmApprovalRecordController extends BaseController {
    @Autowired
    private IPpmApprovalRecordService ppmApprovalRecordService;

    /**
     * 查询审批记录列表
     */
    @RequiresPermissions("system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmApprovalRecord ppmApprovalRecord) {
        System.out.println("ppmApprovalRecord:" + ppmApprovalRecord);
        startPage();
        List<PpmApprovalRecord> list = ppmApprovalRecordService.selectPpmApprovalRecordList(ppmApprovalRecord);
        return getDataTable(list);
    }

    /**
     * 导出审批记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "审批记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmApprovalRecord ppmApprovalRecord) {
        List<PpmApprovalRecord> list = ppmApprovalRecordService.selectPpmApprovalRecordList(ppmApprovalRecord);
        ExcelUtil<PpmApprovalRecord> util = new ExcelUtil<PpmApprovalRecord>(PpmApprovalRecord.class);
        util.exportExcel(response, list, "审批记录数据");
    }

    /**
     * 获取审批记录详细信息
     */
    @RequiresPermissions("system:record:query")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Integer rid) {
        return success(ppmApprovalRecordService.selectPpmApprovalRecordByRid(rid));
    }

    /**
     * 新增审批记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "审批记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmApprovalRecord ppmApprovalRecord) {
        return toAjax(ppmApprovalRecordService.insertPpmApprovalRecord(ppmApprovalRecord));
    }

    /**
     * 修改审批记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "审批记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmApprovalRecord ppmApprovalRecord) {
        return toAjax(ppmApprovalRecordService.updatePpmApprovalRecord(ppmApprovalRecord));
    }

    /**
     * 删除审批记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "审批记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Integer[] rids) {
        return toAjax(ppmApprovalRecordService.deletePpmApprovalRecordByRids(rids));
    }
}
