package com.hh.pms.controller;

import com.hh.pms.domain.NobidNonPro;
import com.hh.pms.service.INobidNonProService;
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
 * 非招标项目Controller
 *
 * @author ruoyi
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/pro")
public class NobidNonProController extends BaseController
{
    @Autowired
    private INobidNonProService nobidNonProService;

    /**
     * 查询非招标项目列表
     */
    @RequiresPermissions("system:pro:list")
    @GetMapping("/list")
    public TableDataInfo list(NobidNonPro nobidNonPro)
    {
        startPage();
        List<NobidNonPro> list = nobidNonProService.selectNobidNonProList(nobidNonPro);
        return getDataTable(list);
    }

    /**
     * 导出非招标项目列表
     */
    @RequiresPermissions("system:pro:export")
    @Log(title = "非招标项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NobidNonPro nobidNonPro)
    {
        List<NobidNonPro> list = nobidNonProService.selectNobidNonProList(nobidNonPro);
        ExcelUtil<NobidNonPro> util = new ExcelUtil<NobidNonPro>(NobidNonPro.class);
        util.exportExcel(response, list, "非招标项目数据");
    }

    /**
     * 获取非招标项目详细信息
     */
    @RequiresPermissions("system:pro:query")
    @GetMapping(value = "/{gid}")
    public AjaxResult getInfo(@PathVariable("gid") Long gid)
    {
        return success(nobidNonProService.selectNobidNonProByGid(gid));
    }

    /**
     * 新增非招标项目
     */
    @RequiresPermissions("system:pro:add")
    @Log(title = "非招标项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NobidNonPro nobidNonPro)
    {
        return toAjax(nobidNonProService.insertNobidNonPro(nobidNonPro));
    }

    /**
     * 修改非招标项目
     */
    @RequiresPermissions("system:pro:edit")
    @Log(title = "非招标项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NobidNonPro nobidNonPro)
    {
        return toAjax(nobidNonProService.updateNobidNonPro(nobidNonPro));
    }

    /**
     * 删除非招标项目
     */
    @RequiresPermissions("system:pro:remove")
    @Log(title = "非招标项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{gids}")
    public AjaxResult remove(@PathVariable Long[] gids)
    {
        return toAjax(nobidNonProService.deleteNobidNonProByGids(gids));
    }
}