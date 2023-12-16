package com.hh.pms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.domain.ComCodeRules;
import com.hh.pms.service.IComCodeRulesService;
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
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/rules")
public class ComCodeRulesController extends BaseController
{
    @Autowired
    private IComCodeRulesService comCodeRulesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:rules:list")
    @GetMapping("/list")
    public TableDataInfo list(ComCodeRules comCodeRules)
    {
        startPage();
        List<ComCodeRules> list = comCodeRulesService.selectComCodeRulesList(comCodeRules);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:rules:export")
    @Log(title = "编号规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ComCodeRules comCodeRules)
    {
        List<ComCodeRules> list = comCodeRulesService.selectComCodeRulesList(comCodeRules);
        ExcelUtil<ComCodeRules> util = new ExcelUtil<ComCodeRules>(ComCodeRules.class);
        util.exportExcel(response, list, "编号规则数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:rules:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(comCodeRulesService.selectComCodeRulesById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:rules:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComCodeRules comCodeRules)
    {
        return toAjax(comCodeRulesService.insertComCodeRules(comCodeRules));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:rules:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComCodeRules comCodeRules)
    {
        return toAjax(comCodeRulesService.updateComCodeRules(comCodeRules));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:rules:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(comCodeRulesService.deleteComCodeRulesByIds(ids));
    }
}
