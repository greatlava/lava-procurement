package com.hh.bidding.controller;

import java.util.List;
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
import com.hh.bidding.domain.BidTender;
import com.hh.bidding.service.IBidTenderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 招标项目Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/tender")
public class BidTenderController extends BaseController {
    @Autowired
    private IBidTenderService bidTenderService;

    /**
     * 查询招标项目列表
     */
    @RequiresPermissions("system:tender:list")
    @GetMapping("/list")
    public TableDataInfo list(BidTender bidTender) {
        startPage();
        List<BidTender> list = bidTenderService.selectBidTenderList(bidTender);
        return getDataTable(list);
    }

    /**
     * 导出招标项目列表
     */
    @RequiresPermissions("system:tender:export")
    @Log(title = "招标项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidTender bidTender) {
        List<BidTender> list = bidTenderService.selectBidTenderList(bidTender);
        ExcelUtil<BidTender> util = new ExcelUtil<BidTender>(BidTender.class);
        util.exportExcel(response, list, "招标项目数据");
    }

    /**
     * 获取招标项目详细信息
     */
    @RequiresPermissions("system:tender:query")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid) {
        return success(bidTenderService.selectBidTenderBySid(sid));
    }

    /**
     * 新增招标项目
     */
//    @RequiresPermissions("system:tender:add")
    @Log(title = "招标项目", businessType = BusinessType.INSERT)
    @PostMapping("addTender")
    public AjaxResult add(@RequestBody BidTender bidTender) {
        return toAjax(bidTenderService.insertBidTender(bidTender));
    }

    /**
     * 修改招标项目
     */
//    @RequiresPermissions("system:tender:edit")
    @Log(title = "招标项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidTender bidTender) {
        return toAjax(bidTenderService.updateBidTender(bidTender));
    }

    /**
     * 删除招标项目
     */
    @RequiresPermissions("system:tender:remove")
    @Log(title = "招标项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids) {
        return toAjax(bidTenderService.deleteBidTenderBySids(sids));
    }

    /**
     * 获取没有合同的项目
     */
    @GetMapping("/NoEidTenderList")
    public TableDataInfo noContract(BidTender bidTender) {
        startPage();
        List<BidTender> list = bidTenderService.selectNoEidTenderList(bidTender);
        return getDataTable(list);
    }

    /**
     * 获取招标项目与公告详细信息
     */
    @GetMapping(value = "/findTenderAndNotice/{sid}")
    public AjaxResult findTenderAndNotice(@PathVariable("sid") Long sid) {
        System.out.println("sid:"+sid);
        return success(bidTenderService.findTenderAndNotice(sid));
    }
}
