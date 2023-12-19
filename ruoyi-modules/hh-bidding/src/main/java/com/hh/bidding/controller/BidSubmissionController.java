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
import com.hh.bidding.domain.BidSubmission;
import com.hh.bidding.service.IBidSubmissionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 投递标书Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/submission")
public class BidSubmissionController extends BaseController
{
    @Autowired
    private IBidSubmissionService bidSubmissionService;

    /**
     * 查询投递标书列表
     */
//    @RequiresPermissions("system:submission:list")
    @GetMapping("/list")
    public TableDataInfo list(BidSubmission bidSubmission)
    {
        startPage();
        List<BidSubmission> list = bidSubmissionService.selectBidSubmissionList(bidSubmission);
        return getDataTable(list);
    }

    /**
     * 导出投递标书列表
     */
    @RequiresPermissions("system:submission:export")
    @Log(title = "投递标书", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidSubmission bidSubmission)
    {
        List<BidSubmission> list = bidSubmissionService.selectBidSubmissionList(bidSubmission);
        ExcelUtil<BidSubmission> util = new ExcelUtil<BidSubmission>(BidSubmission.class);
        util.exportExcel(response, list, "投递标书数据");
    }

    /**
     * 获取投递标书详细信息
     */
    @RequiresPermissions("system:submission:query")
    @GetMapping(value = "/{tdId}")
    public AjaxResult getInfo(@PathVariable("tdId") Long tdId)
    {
        return success(bidSubmissionService.selectBidSubmissionByTdId(tdId));
    }

    /**
     * 新增投递标书
     */
//    @RequiresPermissions("system:submission:add")
    @Log(title = "投递标书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidSubmission bidSubmission)
    {
        return toAjax(bidSubmissionService.insertBidSubmission(bidSubmission));
    }

    /**
     * 修改投递标书
     */
    @RequiresPermissions("system:submission:edit")
    @Log(title = "投递标书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidSubmission bidSubmission)
    {
        return toAjax(bidSubmissionService.updateBidSubmission(bidSubmission));
    }

    /**
     * 删除投递标书
     */
    @RequiresPermissions("system:submission:remove")
    @Log(title = "投递标书", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tdIds}")
    public AjaxResult remove(@PathVariable Long[] tdIds)
    {
        return toAjax(bidSubmissionService.deleteBidSubmissionByTdIds(tdIds));
    }

    //查询上传投标书的所有供应商
    @GetMapping(value = "/submissionList/{sid}")
    public AjaxResult operatorList(@PathVariable("sid") Long sid) {
        return success(bidSubmissionService.findSubmission(sid));
    }
}
