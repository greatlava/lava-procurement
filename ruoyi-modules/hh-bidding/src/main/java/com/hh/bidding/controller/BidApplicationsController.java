package com.hh.bidding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hh.bidding.domain.CodeRulesResult;
import com.hh.bidding.service.IComCodeRulesService;
import com.hh.bidding.util.CodeRuleHelp;
import com.hh.bidding.util.CodeRuleUtil;
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
import com.hh.bidding.domain.BidApplications;
import com.hh.bidding.service.IBidApplicationsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 抽取申请Controller
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/applications")
public class BidApplicationsController extends BaseController
{
    @Autowired
    private IBidApplicationsService bidApplicationsService;

    @Autowired
    private IComCodeRulesService comCodeRulesService;

    /**
     * 查询抽取申请列表
     */
    @RequiresPermissions("system:applications:list")
    @GetMapping("/list")
    public TableDataInfo list(BidApplications bidApplications)
    {
        startPage();
        List<BidApplications> list = bidApplicationsService.selectBidApplicationsList(bidApplications);
        return getDataTable(list);
    }

    /**
     * 导出抽取申请列表
     */
    @RequiresPermissions("system:applications:export")
    @Log(title = "抽取申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidApplications bidApplications)
    {
        List<BidApplications> list = bidApplicationsService.selectBidApplicationsList(bidApplications);
        ExcelUtil<BidApplications> util = new ExcelUtil<BidApplications>(BidApplications.class);
        util.exportExcel(response, list, "抽取申请数据");
    }

    /**
     * 获取抽取申请详细信息
     */
    @RequiresPermissions("system:applications:query")
    @GetMapping(value = "/{xid}")
    public AjaxResult getInfo(@PathVariable("xid") Long xid)
    {
        return success(bidApplicationsService.selectBidApplicationsByXid(xid));
    }

    /**
     * 新增抽取申请
     */
    @RequiresPermissions("system:applications:add")
    @Log(title = "抽取申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidApplications bidApplications)
    {
        //创建编码
        CodeRulesResult result = CodeRuleHelp.createCode(comCodeRulesService, CodeRuleUtil.BID_APPLICATIONS);
        String code = result.getCode();
        bidApplications.setxCode(code);
        return toAjax(bidApplicationsService.insertBidApplications(bidApplications));
    }

    /**
     * 修改抽取申请
     */
    @RequiresPermissions("system:applications:edit")
    @Log(title = "抽取申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidApplications bidApplications)
    {
        return toAjax(bidApplicationsService.updateBidApplications(bidApplications));
    }

    /**
     * 删除抽取申请
     */
    @RequiresPermissions("system:applications:remove")
    @Log(title = "抽取申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{xids}")
    public AjaxResult remove(@PathVariable Long[] xids)
    {
        return toAjax(bidApplicationsService.deleteBidApplicationsByXids(xids));
    }

    @GetMapping(value = "/maxApp/{sid}")
    public AjaxResult maxApp(@PathVariable("sid") Long sid)
    {
        return success(bidApplicationsService.selectMaxApplications(sid));
    }
}
