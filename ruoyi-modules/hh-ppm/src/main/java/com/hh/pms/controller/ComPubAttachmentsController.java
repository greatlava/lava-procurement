package com.hh.pms.controller;

import java.util.List;
 import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.Util.FileUtil;
import com.hh.pms.Util.StringPathUtils;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.domain.ComPubAttachments;
import com.hh.pms.service.IComPubAttachmentsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 公共附件Controller
 *
 * @author ruoyi
 * @date 2023-12-13
 */
@RestController
@RequestMapping("/attachments")
public class ComPubAttachmentsController extends BaseController {
    @Autowired
    private IComPubAttachmentsService comPubAttachmentsService;

    /**
     * 查询公共附件列表
     */
    @RequiresPermissions("system:attachments:list")
    @GetMapping("/list")
    public TableDataInfo list(ComPubAttachments comPubAttachments) {
        startPage();
        List<ComPubAttachments> list = comPubAttachmentsService.selectComPubAttachmentsList(comPubAttachments);
        return getDataTable(list);
    }

    /**
     * 导出公共附件列表
     */
    @RequiresPermissions("system:attachments:export")
    @Log(title = "公共附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ComPubAttachments comPubAttachments) {
        List<ComPubAttachments> list = comPubAttachmentsService.selectComPubAttachmentsList(comPubAttachments);
        ExcelUtil<ComPubAttachments> util = new ExcelUtil<ComPubAttachments>(ComPubAttachments.class);
        util.exportExcel(response, list, "公共附件数据");
    }

    /**
     * 获取公共附件详细信息
     */
    @RequiresPermissions("system:attachments:query")
    @GetMapping(value = "/{anId}")
    public AjaxResult getInfo(@PathVariable("anId") Integer anId) {
        return success(comPubAttachmentsService.selectComPubAttachmentsByAnId(anId));
    }

    /**
     * 新增公共附件
     */
    @RequiresPermissions("system:attachments:add")
    @Log(title = "公共附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ComPubAttachments comPubAttachments) {
        return toAjax(comPubAttachmentsService.insertComPubAttachments(comPubAttachments));
    }

    /**
     * 修改公共附件
     */
    @RequiresPermissions("system:attachments:edit")
    @Log(title = "公共附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ComPubAttachments comPubAttachments) {
        return toAjax(comPubAttachmentsService.updateComPubAttachments(comPubAttachments));
    }

    /**
     * 删除公共附件
     */
    @RequiresPermissions("system:attachments:remove")
    @Log(title = "公共附件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{anIds}")
    public AjaxResult remove(@PathVariable Integer[] anIds) {
        return toAjax(comPubAttachmentsService.deleteComPubAttachmentsByAnIds(anIds));
    }

    @RequiresPermissions("system:attachments:query")
    @PostMapping("/selectedComPubAttamentsByAid")
    public AjaxResult selectedComPubAttamentsByAid(Integer aid) {
        return success(comPubAttachmentsService.selectedComPubAttamentsByAid(aid));
    }

    @PostMapping("/updateComPubAttamentsByAid")
    @Transactional
    public synchronized R updateComPubAttamentsByAid(@RequestBody ComPubAttachments comPubAttachments, String status) {
        System.out.println("comPubAttachments:" + comPubAttachments + "\n status:" + status);
        ComPubAttachments item = comPubAttachmentsService.selectedComPubAttamentsByAid(comPubAttachments.getAid());
        if (item != null) {
            if (status.equals("success")) {
                if (item.getAnName().contains(comPubAttachments.getAnName() + ",")) {
                    comPubAttachments.setAnName(item.getAnName().replace(comPubAttachments.getAnName() + ",", ""));
                } else {
                    comPubAttachments.setAnName(StringPathUtils.cutToTheEndStr(item.getAnName().replace(comPubAttachments.getAnName(), "")));
                }

                if (item.getAnUrl().contains(comPubAttachments.getAnUrl() + ",")) {
                    comPubAttachments.setAnUrl(item.getAnUrl().replace(comPubAttachments.getAnUrl() + ",", ""));
                } else {
                    comPubAttachments.setAnUrl(StringPathUtils.cutToTheEndStr(item.getAnUrl().replace(comPubAttachments.getAnUrl(), "")));
                }
                int i = comPubAttachmentsService.updateComPubAttamentsByAid(comPubAttachments);
                if (i > 0) {
                    return FileUtil.deleteFile(comPubAttachments.getAnUrl());
                } else {
                    return R.fail("上传失败");
                }
            } else {
                comPubAttachments.setAnName(item.getAnName() + "," + comPubAttachments.getAnName());
                comPubAttachments.setAnUrl(item.getAnUrl() + "," + comPubAttachments.getAnUrl());
                int i = comPubAttachmentsService.updateComPubAttamentsByAid(comPubAttachments);
                if (i > 0) {
                    return R.ok(null,"上传成功！！");
                } else {
                    return R.fail("上传失败");
                }
            }
        } else {
            comPubAttachmentsService.insertComPubAttachments(comPubAttachments);
            return R.ok("上传成功");
        }
    }
}
