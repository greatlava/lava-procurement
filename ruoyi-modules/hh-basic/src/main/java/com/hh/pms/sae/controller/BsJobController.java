package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsJob;
import com.hh.pms.sae.service.IBsJobService;
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
 * 职称Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/job")
public class BsJobController extends BaseController {
    @Autowired
    private IBsJobService bsJobService;

    /**
     * 查询职称列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsJob bsJob) {
        startPage();
        List<BsJob> list = bsJobService.selectBsJobList(bsJob);
        return getDataTable(list);
    }

    /**
     * 导出职称列表
     */
    @RequiresPermissions("system:job:export")
    @Log(title = "职称", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsJob bsJob) {
        List<BsJob> list = bsJobService.selectBsJobList(bsJob);
        ExcelUtil<BsJob> util = new ExcelUtil<BsJob>(BsJob.class);
        util.exportExcel(response, list, "职称数据");
    }

    /**
     * 获取职称详细信息
     */
    @RequiresPermissions("system:job:query")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid) {
        return success(bsJobService.selectBsJobByMid(mid));
    }

    /**
     * 新增职称
     */
    @RequiresPermissions("system:job:add")
    @Log(title = "职称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsJob bsJob) {
        return toAjax(bsJobService.insertBsJob(bsJob));
    }

    /**
     * 修改职称
     */
    @RequiresPermissions("system:job:edit")
    @Log(title = "职称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsJob bsJob) {
        return toAjax(bsJobService.updateBsJob(bsJob));
    }

    /**
     * 删除职称
     */
    @RequiresPermissions("system:job:remove")
    @Log(title = "职称", businessType = BusinessType.DELETE)
    @DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids) {
        return toAjax(bsJobService.deleteBsJobByMids(mids));
    }
}
