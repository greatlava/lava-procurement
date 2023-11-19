package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsCommittee;
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
import com.hh.pms.sae.service.IBsCommitteeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 评标委员会Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/committee")
public class BsCommitteeController extends BaseController {
    @Autowired
    private IBsCommitteeService bsCommitteeService;

    /**
     * 查询评标委员会列表
     */
    @RequiresPermissions("system:committee:list")
    @GetMapping("/list")
    public TableDataInfo list(BsCommittee bsCommittee) {
        startPage();
        List<BsCommittee> list = bsCommitteeService.selectBsCommitteeList(bsCommittee);
        return getDataTable(list);
    }

    /**
     * 导出评标委员会列表
     */
    @RequiresPermissions("system:committee:export")
    @Log(title = "评标委员会", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsCommittee bsCommittee) {
        List<BsCommittee> list = bsCommitteeService.selectBsCommitteeList(bsCommittee);
        ExcelUtil<BsCommittee> util = new ExcelUtil<BsCommittee>(BsCommittee.class);
        util.exportExcel(response, list, "评标委员会数据");
    }

    /**
     * 获取评标委员会详细信息
     */
    @RequiresPermissions("system:committee:query")
    @GetMapping(value = "/{pbId}")
    public AjaxResult getInfo(@PathVariable("pbId") Long pbId) {
        return success(bsCommitteeService.selectBsCommitteeByPbId(pbId));
    }

    /**
     * 新增评标委员会
     */
    @RequiresPermissions("system:committee:add")
    @Log(title = "评标委员会", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsCommittee bsCommittee) {
        return toAjax(bsCommitteeService.insertBsCommittee(bsCommittee));
    }

    /**
     * 修改评标委员会
     */
    @RequiresPermissions("system:committee:edit")
    @Log(title = "评标委员会", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsCommittee bsCommittee) {
        return toAjax(bsCommitteeService.updateBsCommittee(bsCommittee));
    }

    /**
     * 删除评标委员会
     */
    @RequiresPermissions("system:committee:remove")
    @Log(title = "评标委员会", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pbIds}")
    public AjaxResult remove(@PathVariable Long[] pbIds) {
        return toAjax(bsCommitteeService.deleteBsCommitteeByPbIds(pbIds));
    }
}
