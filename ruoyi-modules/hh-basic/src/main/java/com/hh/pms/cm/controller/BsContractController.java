package com.hh.pms.cm.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.cm.domain.BsContract;
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
import com.hh.pms.cm.service.IBsContractService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 合同Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/contract")
public class BsContractController extends BaseController {
    @Autowired
    private IBsContractService bsContractService;

    /**
     * 查询合同列表
     */
//    @RequiresPermissions("system:contract:list")
    @GetMapping("/list")
    public TableDataInfo list(BsContract bsContract) {
        startPage();
        List<BsContract> list = bsContractService.selectBsContractList(bsContract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @RequiresPermissions("system:contract:export")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsContract bsContract) {
        List<BsContract> list = bsContractService.selectBsContractList(bsContract);
        ExcelUtil<BsContract> util = new ExcelUtil<BsContract>(BsContract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @RequiresPermissions("system:contract:query")
    @GetMapping(value = "/{eid}")
    public AjaxResult getInfo(@PathVariable("eid") Long eid) {
        return success(bsContractService.selectBsContractByEid(eid));
    }

    /**
     * 新增合同
     */
    @RequiresPermissions("system:contract:add")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return null;
//        return toAjax(bsContractService.insertBsContract(bsContract));
    }

    /**
     * 修改合同
     */
    @RequiresPermissions("system:contract:edit")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsContract bsContract) {
        return toAjax(bsContractService.updateBsContract(bsContract));
    }

    /**
     * 删除合同
     */
    @RequiresPermissions("system:contract:remove")
    @Log(title = "合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{eids}")
    public AjaxResult remove(@PathVariable Long[] eids) {
        return toAjax(bsContractService.deleteBsContractByEids(eids));
    }
}
