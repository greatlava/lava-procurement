package com.hh.pms.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.hh.pms.domain.BidDocuments;
import com.hh.pms.service.IBidDocumentsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 招标文件Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/documents")
public class BidDocumentsController extends BaseController
{
    @Autowired
    private IBidDocumentsService bidDocumentsService;

    /**
     * 查询招标文件列表
     */
    @RequiresPermissions("system:documents:list")
    @GetMapping("/list")
    public TableDataInfo list(BidDocuments bidDocuments)
    {
        startPage();
        List<BidDocuments> list = bidDocumentsService.selectBidDocumentsList(bidDocuments);
        return getDataTable(list);
    }

    /**
     * 导出招标文件列表
     */
    @RequiresPermissions("system:documents:export")
    @Log(title = "招标文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidDocuments bidDocuments)
    {
        List<BidDocuments> list = bidDocumentsService.selectBidDocumentsList(bidDocuments);
        ExcelUtil<BidDocuments> util = new ExcelUtil<BidDocuments>(BidDocuments.class);
        util.exportExcel(response, list, "招标文件数据");
    }

    /**
     * 获取招标文件详细信息
     */
    @RequiresPermissions("system:documents:query")
    @GetMapping(value = "/{wid}")
    public AjaxResult getInfo(@PathVariable("wid") Long wid)
    {
        return success(bidDocumentsService.selectBidDocumentsByWid(wid));
    }

    /**
     * 新增招标文件
     */
    @RequiresPermissions("system:documents:add")
    @Log(title = "招标文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidDocuments bidDocuments)
    {
        return toAjax(bidDocumentsService.insertBidDocuments(bidDocuments));
    }

    /**
     * 修改招标文件
     */
    @RequiresPermissions("system:documents:edit")
    @Log(title = "招标文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidDocuments bidDocuments)
    {
        return toAjax(bidDocumentsService.updateBidDocuments(bidDocuments));
    }

    /**
     * 删除招标文件
     */
    @RequiresPermissions("system:documents:remove")
    @Log(title = "招标文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wids}")
    public AjaxResult remove(@PathVariable Long[] wids)
    {
        return toAjax(bidDocumentsService.deleteBidDocumentsByWids(wids));
    }
}
