package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsTraining;
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
import com.hh.pms.sae.service.IBsTrainingService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 培训Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/training")
public class BsTrainingController extends BaseController {
    @Autowired
    private IBsTrainingService bsTrainingService;

    /**
     * 查询培训列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsTraining bsTraining) {
        startPage();
        List<BsTraining> list = bsTrainingService.selectBsTrainingList(bsTraining);
        return getDataTable(list);
    }

    /**
     * 导出培训列表
     */
    @RequiresPermissions("system:training:export")
    @Log(title = "培训", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsTraining bsTraining) {
        List<BsTraining> list = bsTrainingService.selectBsTrainingList(bsTraining);
        ExcelUtil<BsTraining> util = new ExcelUtil<BsTraining>(BsTraining.class);
        util.exportExcel(response, list, "培训数据");
    }

    /**
     * 获取培训详细信息
     */
    @RequiresPermissions("system:training:query")
    @GetMapping(value = "/{lid}")
    public AjaxResult getInfo(@PathVariable("lid") Long lid) {
        return success(bsTrainingService.selectBsTrainingByLid(lid));
    }

    /**
     * 新增培训
     */
    @RequiresPermissions("system:training:add")
    @Log(title = "培训", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsTraining bsTraining) {
        return toAjax(bsTrainingService.insertBsTraining(bsTraining));
    }

    /**
     * 修改培训
     */
    @RequiresPermissions("system:training:edit")
    @Log(title = "培训", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsTraining bsTraining) {
        return toAjax(bsTrainingService.updateBsTraining(bsTraining));
    }

    /**
     * 删除培训
     */
    @RequiresPermissions("system:training:remove")
    @Log(title = "培训", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lids}")
    public AjaxResult remove(@PathVariable Long[] lids) {
        return toAjax(bsTrainingService.deleteBsTrainingByLids(lids));
    }
}
