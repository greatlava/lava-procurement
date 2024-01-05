package com.hh.nobidding.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.hh.nobidding.domain.PpmLineItems;
import com.hh.nobidding.service.IPpmLineItemsService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 行项目Controller
 *
 * @author ruoyi
 * @date 2023-12-26
 */
@RestController
@RequestMapping("/items")
public class PpmLineItemsController extends BaseController {
    @Autowired
    private IPpmLineItemsService ppmLineItemsService;

    /**
     * 查询行项目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PpmLineItems ppmLineItems) {
        startPage();
        List<PpmLineItems> list = ppmLineItemsService.selectPpmLineItemsList(ppmLineItems);
        return getDataTable(list);
    }

    /**
     * 导出行项目列表
     */
    @Log(title = "行项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmLineItems ppmLineItems) {
        List<PpmLineItems> list = ppmLineItemsService.selectPpmLineItemsList(ppmLineItems);
        ExcelUtil<PpmLineItems> util = new ExcelUtil<PpmLineItems>(PpmLineItems.class);
        util.exportExcel(response, list, "行项目数据");
    }

    /**
     * 获取行项目详细信息
     */
    @GetMapping(value = "/{vid}")
    public AjaxResult getInfo(@PathVariable("vid") Integer vid) {
        return success(ppmLineItemsService.selectPpmLineItemsByVid(vid));
    }

    /**
     * 新增行项目
     */
    @Log(title = "行项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmLineItems ppmLineItems) {
        return toAjax(ppmLineItemsService.insertPpmLineItems(ppmLineItems));
    }

    /**
     * 修改行项目
     */
    @Log(title = "行项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmLineItems ppmLineItems) {
        return toAjax(ppmLineItemsService.updatePpmLineItems(ppmLineItems));
    }

    /**
     * 删除行项目
     */
    @Log(title = "行项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vids}")
    public AjaxResult remove(@PathVariable Integer[] vids) {
        return toAjax(ppmLineItemsService.deletePpmLineItemsByVids(vids));
    }

    //查询非招标对应的产品信息
    @GetMapping("/getItemsDevice")
    public TableDataInfo selectItemsDevice(Long aid) {
        return getDataTable(ppmLineItemsService.selectItemsDevice(aid));
    }
}
