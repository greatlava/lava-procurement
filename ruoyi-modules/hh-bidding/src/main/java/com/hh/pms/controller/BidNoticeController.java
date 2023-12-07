package com.hh.pms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.domain.BidNotice;
import com.hh.pms.service.IBidNoticeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 招标公告Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/notice")
public class BidNoticeController extends BaseController
{
    @Autowired
    private IBidNoticeService bidNoticeService;


    /**
     * 查询招标公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BidNotice bidNotice)
    {
        startPage();
        List<BidNotice> list = bidNoticeService.selectBidNoticeList(bidNotice);
        return getDataTable(list);
    }

    /**
     * 导出招标公告列表
     */
    @RequiresPermissions("system:notice:export")
    @Log(title = "招标公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidNotice bidNotice)
    {
        List<BidNotice> list = bidNoticeService.selectBidNoticeList(bidNotice);
        ExcelUtil<BidNotice> util = new ExcelUtil<BidNotice>(BidNotice.class);
        util.exportExcel(response, list, "招标公告数据");
    }

    /**
     * 获取招标公告详细信息
     */
    @RequiresPermissions("system:notice:query")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bidNoticeService.selectBidNoticeByUid(uid));
    }

    /**
     * 新增招标公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "招标公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BidNotice bidNotice)
    {
        return toAjax(bidNoticeService.insertBidNotice(bidNotice));
    }

    /**
     * 修改招标公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "招标公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated  @RequestBody BidNotice bidNotice)
    {
        return toAjax(bidNoticeService.updateBidNotice(bidNotice));
    }

    /**
     * 删除招标公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "招标公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bidNoticeService.deleteBidNoticeByUids(uids));
    }

    //终止
    @PostMapping("/find")
    public AjaxResult findAllByEndTime() {
        return AjaxResult.success(bidNoticeService.findAllByEndTime());
    }

    //判定是否已存在已发布状态
    @PostMapping("/findStatus")
    public AjaxResult findStatus(@Validated @RequestBody  Long sid) {
        return AjaxResult.success(bidNoticeService.findStatus(sid));
    }

    /**
     * 删除已发布招标公告
     */
    @Log(title = "招标公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/sc/{sid}")
    public AjaxResult remove123(@PathVariable Long sid)
    {
        return toAjax(bidNoticeService.deleteYfb(sid));
    }
}
