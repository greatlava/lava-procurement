package com.hh.pms.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hh.pms.domain.BidNotice;
import com.hh.pms.domain.Result;
import com.hh.pms.utils.FileUtil;
import com.hh.pms.utils.StringPathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.domain.BidGetTender;
import com.hh.pms.service.IBidGetTenderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import springfox.documentation.spring.web.json.Json;

/**
 * 获取标书Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/tenderFile")
public class BidGetTenderController extends BaseController {
    @Autowired
    private IBidGetTenderService bidGetTenderService;

    /**
     * 查询获取标书列表
     */
//    @RequiresPermissions("system:tender:list")
    @GetMapping("/list")
    public TableDataInfo list(BidGetTender bidGetTender) {
        startPage();
        List<BidGetTender> list = bidGetTenderService.selectBidGetTenderList(bidGetTender);
        return getDataTable(list);
    }

    /**
     * 导出获取标书列表
     */
    @RequiresPermissions("system:tender:export")
    @Log(title = "获取标书", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidGetTender bidGetTender) {
        List<BidGetTender> list = bidGetTenderService.selectBidGetTenderList(bidGetTender);
        ExcelUtil<BidGetTender> util = new ExcelUtil<BidGetTender>(BidGetTender.class);
        util.exportExcel(response, list, "获取标书数据");
    }

    /**
     * 获取获取标书详细信息
     */
    @RequiresPermissions("system:tender:query")
    @GetMapping(value = "/{yid}")
    public AjaxResult getInfo(@PathVariable("yid") Long yid) {
        return success(bidGetTenderService.selectBidGetTenderByYid(yid));
    }

    /**
     * 新增获取标书
     */
    @RequiresPermissions("system:tender:add")
    @Log(title = "获取标书", businessType = BusinessType.INSERT)
    @PostMapping("/addBs")
    public AjaxResult add(@RequestBody BidGetTender bidGetTender) {
        return toAjax(bidGetTenderService.insertBidGetTender(bidGetTender));
    }

    /**
     * 修改获取标书
     */
    @RequiresPermissions("system:tender:edit")
    @Log(title = "获取标书", businessType = BusinessType.UPDATE)
    @PutMapping("/putBs")
    public AjaxResult edit(@RequestBody BidGetTender bidGetTender) {
        return toAjax(bidGetTenderService.updateBidGetTender(bidGetTender));
    }

    /**
     * 删除获取标书
     */
    @RequiresPermissions("system:tender:remove")
    @Log(title = "获取标书", businessType = BusinessType.DELETE)
    @DeleteMapping("/{yids}")
    public AjaxResult remove(@PathVariable Long[] yids) {
        return toAjax(bidGetTenderService.deleteBidGetTenderByYids(yids));
    }


    //下载招标书
    @GetMapping("/downloadZip")
    public void downloadZip(@RequestParam String obj, HttpServletResponse response) {
        BidNotice bidNotice = JSON.parseObject(obj, BidNotice.class);
        String url = "";
        for (Result b : bidNotice.getResults()) {
            url += b.getUrl() + ",";
        }
        url = StringPathUtils.cutToTheEndStr(url);
        FileUtil.downloadFiles(url,response);
    }


}
