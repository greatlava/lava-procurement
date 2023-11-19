package com.hh.pms.cm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.cm.domain.BsEmployee;
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
import com.hh.pms.cm.service.IBsEmployeeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 员工Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/employee")
public class BsEmployeeController extends BaseController {
    @Autowired
    private IBsEmployeeService bsEmployeeService;

    /**
     * 查询员工列表
     */
    @RequiresPermissions("system:employee:list")
    @GetMapping("/list")
    public TableDataInfo list(BsEmployee bsEmployee) {
        startPage();
        List<BsEmployee> list = bsEmployeeService.selectBsEmployeeList(bsEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @RequiresPermissions("system:employee:export")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsEmployee bsEmployee) {
        List<BsEmployee> list = bsEmployeeService.selectBsEmployeeList(bsEmployee);
        ExcelUtil<BsEmployee> util = new ExcelUtil<BsEmployee>(BsEmployee.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @RequiresPermissions("system:employee:query")
    @GetMapping(value = "/{pid}")
    public AjaxResult getInfo(@PathVariable("pid") Long pid) {
        return success(bsEmployeeService.selectBsEmployeeByPid(pid));
    }

    /**
     * 新增员工
     */
    @RequiresPermissions("system:employee:add")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsEmployee bsEmployee) {
        return toAjax(bsEmployeeService.insertBsEmployee(bsEmployee));
    }

    /**
     * 修改员工
     */
    @RequiresPermissions("system:employee:edit")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsEmployee bsEmployee) {
        return toAjax(bsEmployeeService.updateBsEmployee(bsEmployee));
    }

    /**
     * 删除员工
     */
    @RequiresPermissions("system:employee:remove")
    @Log(title = "员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pids}")
    public AjaxResult remove(@PathVariable Long[] pids) {
        return toAjax(bsEmployeeService.deleteBsEmployeeByPids(pids));
    }
}
