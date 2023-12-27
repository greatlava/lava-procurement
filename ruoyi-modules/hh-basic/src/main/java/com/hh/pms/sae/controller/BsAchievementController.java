package com.hh.pms.sae.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.sae.domain.BsAchievement;
import com.hh.pms.sae.service.IBsAchievementService;
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
 * 业绩Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/achievement")
public class BsAchievementController extends BaseController {
    @Autowired
    private IBsAchievementService bsAchievementService;

    /**
     * 查询业绩列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BsAchievement bsAchievement) {
        startPage();
        List<BsAchievement> list = bsAchievementService.selectBsAchievementList(bsAchievement);
        return getDataTable(list);
    }

    /**
     * 导出业绩列表
     */
    @RequiresPermissions("system:achievement:export")
    @Log(title = "业绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsAchievement bsAchievement) {
        List<BsAchievement> list = bsAchievementService.selectBsAchievementList(bsAchievement);
        ExcelUtil<BsAchievement> util = new ExcelUtil<BsAchievement>(BsAchievement.class);
        util.exportExcel(response, list, "业绩数据");
    }

    /**
     * 获取业绩详细信息
     */
    @RequiresPermissions("system:achievement:query")
    @GetMapping(value = "/{yjId}")
    public AjaxResult getInfo(@PathVariable("yjId") Long yjId) {
        return success(bsAchievementService.selectBsAchievementByYjId(yjId));
    }

    /**
     * 新增业绩
     */
    @RequiresPermissions("system:achievement:add")
    @Log(title = "业绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsAchievement bsAchievement) {
        return toAjax(bsAchievementService.insertBsAchievement(bsAchievement));
    }

    /**
     * 修改业绩
     */
    @RequiresPermissions("system:achievement:edit")
    @Log(title = "业绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsAchievement bsAchievement) {
        return toAjax(bsAchievementService.updateBsAchievement(bsAchievement));
    }

    /**
     * 删除业绩
     */
    @RequiresPermissions("system:achievement:remove")
    @Log(title = "业绩", businessType = BusinessType.DELETE)
    @DeleteMapping("/{yjIds}")
    public AjaxResult remove(@PathVariable Long[] yjIds) {
        return toAjax(bsAchievementService.deleteBsAchievementByYjIds(yjIds));
    }
}
