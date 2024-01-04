package com.hh.nobidding.controller;

import com.hh.nobidding.domain.BsInventory;
import com.hh.nobidding.domain.ComQuotation;
import com.hh.nobidding.domain.NobidNonPro;
import com.hh.nobidding.service.INobidNonProService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 非招标项目Controller
 *
 * @author ruoyi
 * @date 2023-12-11
 */
@RestController
@RequestMapping("/pro")
@Transactional
public class NobidNonProController extends BaseController {
    @Autowired
    private INobidNonProService nobidNonProService;

    /**
     * 查询非招标项目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(NobidNonPro nobidNonPro) {
        startPage();
        List<NobidNonPro> list = nobidNonProService.selectNobidNonProList(nobidNonPro);
        return getDataTable(list);
    }

    //查询可创建合同的非招标项目表
    @GetMapping("/list1")
    public TableDataInfo list1(NobidNonPro nobidNonPro) {
        startPage();
        List<NobidNonPro> list = nobidNonProService.selectNobidNonProList1(nobidNonPro);
        return getDataTable(list);
    }

    /**
     * 导出非招标项目列表
     */
    @Log(title = "非招标项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NobidNonPro nobidNonPro) {
        List<NobidNonPro> list = nobidNonProService.selectNobidNonProList(nobidNonPro);
        ExcelUtil<NobidNonPro> util = new ExcelUtil<NobidNonPro>(NobidNonPro.class);
        util.exportExcel(response, list, "非招标项目数据");
    }

    /**
     * 获取非招标项目详细信息
     */
    @GetMapping(value = "/{gid}")
    public AjaxResult getInfo(@PathVariable("gid") Long gid) {
        return success(nobidNonProService.selectNobidNonProByGid(gid));
    }

    /**
     * 新增非招标项目
     */
    @Log(title = "非招标项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NobidNonPro nobidNonPro) {
        return toAjax(nobidNonProService.insertNobidNonPro(nobidNonPro));
    }

    /**
     * 修改非招标项目
     */
    @Log(title = "非招标项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NobidNonPro nobidNonPro) {
        int i = nobidNonProService.updateNobidNonPro(nobidNonPro);
        if (i > 0) {
            int i1;
            if (nobidNonPro.getgRelease() == 2) {
                int i2 = nobidNonProService.updateNobidNonPro(nobidNonPro);
                if (i2 == 0) {
                    return AjaxResult.error("发布失败");
                } else {
                    return AjaxResult.success("发布成功");
                }
            } else {
                if (nobidNonPro.getComPubAttachments().getAnId() == null) {
                    i1 = nobidNonProService.insertComPubAttachments(nobidNonPro.getComPubAttachments());
                } else {
                    i1 = nobidNonProService.updateComPubAttachments(nobidNonPro.getComPubAttachments());
                }
                if (i1 == 0) {
                    return AjaxResult.error("发布失败");
                }
                return AjaxResult.success("发布成功");
            }
        }
        return AjaxResult.error("发布失败");
    }

    /**
     * 删除非招标项目
     */
    @Log(title = "非招标项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{gids}")
    public AjaxResult remove(@PathVariable Long[] gids) {
        return toAjax(nobidNonProService.deleteNobidNonProByGids(gids));
    }

    //查询项目报价
    @GetMapping("/selectQuotation")
    public TableDataInfo selectQuotation(String gfId) {
        List<ComQuotation> list = nobidNonProService.selectQuotation(gfId);
        return getDataTable(list);
    }

    //获取附件详细信息
    @GetMapping("/selectCom")
    public AjaxResult selectCom(Integer aid) {
        System.out.println("打印了一个aid");
        System.out.println(aid);
        return success(nobidNonProService.selectComPubAttachmentsByAid(aid));
    }


    /**
     * 新增报价单
     */
    @PostMapping("/addCom")
    public AjaxResult addCom(@RequestBody ComQuotation comQuotations) {
        return success(nobidNonProService.insertComQuotation(comQuotations));
    }

    /**
     * 删除报价单
     */
    @DeleteMapping("/delByGfId")
    public AjaxResult remove(String gfId) {
        return success(nobidNonProService.deleteComQuotationByGfIds(gfId));
    }

}