package com.hh.pms.sae.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hh.pms.sae.domain.BsSupplier;
import com.hh.pms.sae.utils.TokenUtil;
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
import com.hh.pms.sae.domain.NobidSupNonPro;
import com.hh.pms.sae.service.INobidSupNonProService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 供应商非招标项目Controller
 *
 * @author ruoyi
 * @date 2023-12-20
 */
@RestController
@RequestMapping("/pro")
public class NobidSupNonProController extends BaseController {
    @Autowired
    private INobidSupNonProService nobidSupNonProService;

    /**
     * 查询供应商非招标项目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(NobidSupNonPro nobidSupNonPro) {
        startPage();
        List<NobidSupNonPro> list = nobidSupNonProService.selectNobidSupNonProList(nobidSupNonPro);
        return getDataTable(list);
    }

    @GetMapping("/checkIsJoin")
    public AjaxResult checkIsJoin(Long hid, String gf_id) {
        NobidSupNonPro nobidSupNonPro = nobidSupNonProService.checkIsJoin(hid, gf_id);
        if (nobidSupNonPro == null) {
            return AjaxResult.error("yes");
        }
        return AjaxResult.error("no");
    }

    /**
     * 导出供应商非招标项目列表
     */
    @Log(title = "供应商非招标项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NobidSupNonPro nobidSupNonPro) {
        List<NobidSupNonPro> list = nobidSupNonProService.selectNobidSupNonProList(nobidSupNonPro);
        ExcelUtil<NobidSupNonPro> util = new ExcelUtil<NobidSupNonPro>(NobidSupNonPro.class);
        util.exportExcel(response, list, "供应商非招标项目数据");
    }

    /**
     * 获取供应商非招标项目详细信息
     */
    @GetMapping(value = "/{gfId}")
    public AjaxResult getInfo(@PathVariable("gfId") String gfId) {
        return success(nobidSupNonProService.selectNobidSupNonProByGfId(gfId));
    }

    /**
     * 新增供应商非招标项目
     */
    @Log(title = "供应商非招标项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NobidSupNonPro nobidSupNonPro) {
        return toAjax(nobidSupNonProService.insertNobidSupNonPro(nobidSupNonPro));
    }

    /**
     * 修改供应商非招标项目
     */
    @Log(title = "供应商非招标项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NobidSupNonPro nobidSupNonPro) {
        return toAjax(nobidSupNonProService.updateNobidSupNonPro(nobidSupNonPro));
    }

    /**
     * 删除供应商非招标项目
     */
    @Log(title = "供应商非招标项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{gfIds}")
    public AjaxResult remove(@PathVariable String[] gfIds) {
        return toAjax(nobidSupNonProService.deleteNobidSupNonProByGfIds(gfIds));
    }
}
