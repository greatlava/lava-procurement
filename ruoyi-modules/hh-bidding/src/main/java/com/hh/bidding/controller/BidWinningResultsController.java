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
import com.hh.bidding.domain.BidWinningResults;
import com.hh.bidding.service.IBidWinningResultsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 中标结果公示Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/results")
public class BidWinningResultsController extends BaseController
{
    @Autowired
    private IBidWinningResultsService bidWinningResultsService;

    /**
     * 查询中标结果公示列表
     */
//    @RequiresPermissions("system:results:list")
    @GetMapping("/list")
    public TableDataInfo list( BidWinningResults bidWinningResults)
    {
        startPage();
        List<BidWinningResults> list = bidWinningResultsService.selectBidWinningResultsList(bidWinningResults);
        return getDataTable(list);
    }

    /**
     * 导出中标结果公示列表
     */
    @RequiresPermissions("system:results:export")
    @Log(title = "中标结果公示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidWinningResults bidWinningResults)
    {
        List<BidWinningResults> list = bidWinningResultsService.selectBidWinningResultsList(bidWinningResults);
        ExcelUtil<BidWinningResults> util = new ExcelUtil<BidWinningResults>(BidWinningResults.class);
        util.exportExcel(response, list, "中标结果公示数据");
    }

    /**
     * 获取中标结果公示详细信息
     */
    @RequiresPermissions("system:results:query")
    @GetMapping(value = "/{gsId}")
    public AjaxResult getInfo(@PathVariable("gsId") Long gsId)
    {
        return success(bidWinningResultsService.selectBidWinningResultsByGsId(gsId));
    }

    /**
     * 新增中标结果公示
     */
    @RequiresPermissions("system:results:add")
    @Log(title = "中标结果公示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidWinningResults bidWinningResults)
    {
        return toAjax(bidWinningResultsService.insertBidWinningResults(bidWinningResults));
    }

    /**
     * 修改中标结果公示
     */
    @RequiresPermissions("system:results:edit")
    @Log(title = "中标结果公示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidWinningResults bidWinningResults)
    {
        return toAjax(bidWinningResultsService.updateBidWinningResults(bidWinningResults));
    }

    /**
     * 删除中标结果公示
     */
    @RequiresPermissions("system:results:remove")
    @Log(title = "中标结果公示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gsIds}")
    public AjaxResult remove(@PathVariable Long[] gsIds)
    {
        return toAjax(bidWinningResultsService.deleteBidWinningResultsByGsIds(gsIds));
    }

    @GetMapping("/selectResultSupp/{sid}")
    public AjaxResult SuppList( @PathVariable("sid") Long sid)
    {
        return success(bidWinningResultsService.selectResultSupp(sid));
    }
    @GetMapping("/selectResultAndCandidate/{sid}")
    public AjaxResult selectResultAndCandidate( @PathVariable("sid") Long sid)
    {
        return success(bidWinningResultsService.selectResultAndCandidate(sid));
    }
}
