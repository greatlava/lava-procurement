package com.hh.bidding.controller;

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
import com.hh.bidding.domain.BidCommittee;
import com.hh.bidding.service.IBidCommitteeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 评标委员会Controller
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/committee")
public class BidCommitteeController extends BaseController
{
    @Autowired
    private IBidCommitteeService bidCommitteeService;

    /**
     * 查询评标委员会列表
     */
    @RequiresPermissions("system:committee:list")
    @GetMapping("/list")
    public TableDataInfo list(BidCommittee bidCommittee)
    {
        startPage();
        List<BidCommittee> list = bidCommitteeService.selectBidCommitteeList(bidCommittee);
        return getDataTable(list);
    }

    /**
     * 导出评标委员会列表
     */
    @RequiresPermissions("system:committee:export")
    @Log(title = "评标委员会", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidCommittee bidCommittee)
    {
        List<BidCommittee> list = bidCommitteeService.selectBidCommitteeList(bidCommittee);
        ExcelUtil<BidCommittee> util = new ExcelUtil<BidCommittee>(BidCommittee.class);
        util.exportExcel(response, list, "评标委员会数据");
    }

    /**
     * 获取评标委员会详细信息
     */
    @RequiresPermissions("system:committee:query")
    @GetMapping(value = "/{pbId}")
    public AjaxResult getInfo(@PathVariable("pbId") Long pbId)
    {
        return success(bidCommitteeService.selectBidCommitteeByPbId(pbId));
    }

    /**
     * 新增评标委员会
     */
    @RequiresPermissions("system:committee:add")
    @Log(title = "评标委员会", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidCommittee bidCommittee)
    {
        return toAjax(bidCommitteeService.insertBidCommittee(bidCommittee));
    }

    /**
     * 修改评标委员会
     */
    @RequiresPermissions("system:committee:edit")
    @Log(title = "评标委员会", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidCommittee bidCommittee)
    {
        return toAjax(bidCommitteeService.updateBidCommittee(bidCommittee));
    }

    /**
     * 删除评标委员会
     */
    @RequiresPermissions("system:committee:remove")
    @Log(title = "评标委员会", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pbIds}")
    public AjaxResult remove(@PathVariable Long[] pbIds)
    {
        return toAjax(bidCommitteeService.deleteBidCommitteeByPbIds(pbIds));
    }

    @DeleteMapping("/delComBySid/{sid}")
    public AjaxResult remove(@PathVariable Long sid)
    {
        return toAjax(bidCommitteeService.delComBySid(sid));
    }

    @GetMapping("/findCommitAndExpert/{sid}")
    public TableDataInfo findCommitAndExpert(@PathVariable("sid") Long sid)
    {
        startPage();
        List<BidCommittee> list = bidCommitteeService.findCommitteeAndExpert(sid);
        return getDataTable(list);
    }
}
