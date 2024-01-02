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
import com.hh.bidding.domain.BidCandidate;
import com.hh.bidding.service.IBidCandidateService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 中标候选人Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/candidate")
public class BidCandidateController extends BaseController {
    @Autowired
    private IBidCandidateService bidCandidateService;

    /**
     * 查询中标候选人列表
     */
    @RequiresPermissions("system:candidate:list")
    @GetMapping("/list")
    public TableDataInfo list(BidCandidate bidCandidate) {
        startPage();
        List<BidCandidate> list = bidCandidateService.selectBidCandidateList(bidCandidate);
        return getDataTable(list);
    }

    /**
     * 导出中标候选人列表
     */
    @RequiresPermissions("system:candidate:export")
    @Log(title = "中标候选人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidCandidate bidCandidate) {
        List<BidCandidate> list = bidCandidateService.selectBidCandidateList(bidCandidate);
        ExcelUtil<BidCandidate> util = new ExcelUtil<BidCandidate>(BidCandidate.class);
        util.exportExcel(response, list, "中标候选人数据");
    }

    /**
     * 获取中标候选人详细信息
     */
    @RequiresPermissions("system:candidate:query")
    @GetMapping(value = "/{zid}")
    public AjaxResult getInfo(@PathVariable("zid") Long zid) {
        return success(bidCandidateService.selectBidCandidateByZid(zid));
    }

    /**
     * 新增中标候选人
     */
    @RequiresPermissions("system:candidate:add")
    @Log(title = "中标候选人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidCandidate bidCandidate) {
        return toAjax(bidCandidateService.insertBidCandidate(bidCandidate));
    }

    /**
     * 修改中标候选人
     */
    @RequiresPermissions("system:candidate:edit")
    @Log(title = "中标候选人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidCandidate bidCandidate) {
        return toAjax(bidCandidateService.updateBidCandidate(bidCandidate));
    }

    /**
     * 删除中标候选人
     */
    @RequiresPermissions("system:candidate:remove")
    @Log(title = "中标候选人", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zids}")
    public AjaxResult remove(@PathVariable Long[] zids) {
        return toAjax(bidCandidateService.deleteBidCandidateByZids(zids));
    }

    @GetMapping(value = "/suppCand")
    public AjaxResult suppCand(Long sid) {
        return success(bidCandidateService.selectSuppCand(sid));
    }

    //获取附件详细信息
    @GetMapping("/getSuppHid")
    public AjaxResult getSuppHid(Long sid) {
        return success(bidCandidateService.selectBidCandidateBySid(sid));
    }
}
