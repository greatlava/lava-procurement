package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsExperience;
import com.hh.pms.sae.service.IBsExperienceService;
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
 * 任职经历Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/experience")
public class BsExperienceController extends BaseController {
    @Autowired
    private IBsExperienceService bsExperienceService;

    /**
     * 查询任职经历列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsExperience bsExperience) {
        startPage();
        List<BsExperience> list = bsExperienceService.selectBsExperienceList(bsExperience);
        return getDataTable(list);
    }

    /**
     * 导出任职经历列表
     */
    @RequiresPermissions("system:experience:export")
    @Log(title = "任职经历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsExperience bsExperience) {
        List<BsExperience> list = bsExperienceService.selectBsExperienceList(bsExperience);
        ExcelUtil<BsExperience> util = new ExcelUtil<BsExperience>(BsExperience.class);
        util.exportExcel(response, list, "任职经历数据");
    }

    /**
     * 获取任职经历详细信息
     */
    @RequiresPermissions("system:experience:query")
    @GetMapping(value = "/{qid}")
    public AjaxResult getInfo(@PathVariable("qid") Long qid) {
        return success(bsExperienceService.selectBsExperienceByQid(qid));
    }

    /**
     * 新增任职经历
     */
    @RequiresPermissions("system:experience:add")
    @Log(title = "任职经历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsExperience bsExperience) {
        return toAjax(bsExperienceService.insertBsExperience(bsExperience));
    }

    /**
     * 修改任职经历
     */
    @RequiresPermissions("system:experience:edit")
    @Log(title = "任职经历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsExperience bsExperience) {
        return toAjax(bsExperienceService.updateBsExperience(bsExperience));
    }

    /**
     * 删除任职经历
     */
    @RequiresPermissions("system:experience:remove")
    @Log(title = "任职经历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qids}")
    public AjaxResult remove(@PathVariable Long[] qids) {
        return toAjax(bsExperienceService.deleteBsExperienceByQids(qids));
    }
}
