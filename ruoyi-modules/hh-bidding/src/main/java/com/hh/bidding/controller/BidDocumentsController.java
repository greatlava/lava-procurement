package com.hh.bidding.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.bidding.domain.BidDocuments;
import com.hh.bidding.service.IBidDocumentsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
//    @GetMapping("/findTwoDocInfo")
//    public TableDataInfo findTwoDocInfo(BidNotice bidNotice)
//    {
//        startPage();
//        List<BidDocuments> list = bidDocumentsService.findTwoDocInfo(bidNotice);
//        return getDataTable(list);
//    }

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
    @Resource
    private RemoteFileService remoteFileService;
    @PostMapping("/upload1")
    public AjaxResult upload1(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            System.out.println("file:"+file);
            LoginUser loginUser = SecurityUtils.getLoginUser();
            R<SysFile> fileResult = remoteFileService.upload(file);
            System.out.println("fileResult:" + fileResult.getData().getName() + "\t" + fileResult.getData().getUrl());
            System.out.println("文件上传成功！。。。。");
            //结果非空
            if (!StringUtils.isNull(fileResult) || !StringUtils.isNull(fileResult.getData())) {
                return AjaxResult.success("上传文件成功！",fileResult);
            }
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }

    @PostMapping("/upload2")
    public AjaxResult upload2(MultipartFile [] file) throws IOException {
       List<String> urls =new ArrayList<>();
        for (MultipartFile f:file) {
            if (!f.isEmpty()) {
                LoginUser loginUser = SecurityUtils.getLoginUser();
                R<SysFile> fileResult = remoteFileService.upload(f);
                System.out.println("fileResult:" + fileResult.getData().getName() + "\t" + fileResult.getData().getUrl());
                System.out.println("文件上传成功！。。。。");
                urls.add(fileResult.getData().getUrl());
                //结果非空
                if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                    return AjaxResult.error("上传文件异常，请联系管理员");
                }
            }
        }
        return AjaxResult.success("上传文件成功！",urls);
    }
}
