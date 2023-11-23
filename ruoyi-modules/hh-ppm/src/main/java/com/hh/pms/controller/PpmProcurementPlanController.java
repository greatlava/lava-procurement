package com.hh.pms.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.domain.PpmProcurementPlan;
import com.hh.pms.service.IPpmProcurementPlanService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 采购计划Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/frameworkPlan")
public class PpmProcurementPlanController extends BaseController {
    @Autowired
    private IPpmProcurementPlanService ppmProcurementPlanService;

    /**
     * 查询采购计划列表
     */
    @RequiresPermissions("system:plan:list")
    @GetMapping("/list")
    public TableDataInfo list(PpmProcurementPlan ppmProcurementPlan) {
        startPage();
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectPpmProcurementPlanList(ppmProcurementPlan);
        return getDataTable(list);
    }

    /**
     * 导出采购计划列表
     */
    @RequiresPermissions("system:plan:export")
    @Log(title = "采购计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PpmProcurementPlan ppmProcurementPlan) {
        List<PpmProcurementPlan> list = ppmProcurementPlanService.selectPpmProcurementPlanList(ppmProcurementPlan);
        ExcelUtil<PpmProcurementPlan> util = new ExcelUtil<PpmProcurementPlan>(PpmProcurementPlan.class);
        util.exportExcel(response, list, "采购计划数据");
    }

    /**
     * 获取采购计划详细信息
     */
    @RequiresPermissions("system:plan:query")
    @GetMapping(value = "/{aid}")
    public AjaxResult getInfo(@PathVariable("aid") Long aid) {
        return success(ppmProcurementPlanService.selectPpmProcurementPlanByAid(aid));
    }

    /**
     * 新增采购计划
     */
    @RequiresPermissions("system:plan:add")
    @Log(title = "采购计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        return toAjax(ppmProcurementPlanService.insertPpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 修改采购计划
     */
    @RequiresPermissions("system:plan:edit")
    @Log(title = "采购计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PpmProcurementPlan ppmProcurementPlan) {
        return toAjax(ppmProcurementPlanService.updatePpmProcurementPlan(ppmProcurementPlan));
    }

    /**
     * 删除采购计划
     */
    @RequiresPermissions("system:plan:remove")
    @Log(title = "采购计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aids}")
    public AjaxResult remove(@PathVariable Long[] aids) {
        return toAjax(ppmProcurementPlanService.deletePpmProcurementPlanByAids(aids));
    }

    @RequiresPermissions("system:plan:download")
    @PostMapping("/fileDownload")
    public AjaxResult fileDownload(HttpServletResponse response, String fileName) {
        try {
            System.out.println("fileName" + fileName);
            String path = "E:\\java\\PracticalTraining\\文件";
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            File file = new File(path, fileName);
            InputStream input = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] buff = new byte[1024];
            int index = 0;
            //4、执行 写出操作
            while ((index = input.read(buff)) != -1) {
                out.write(buff, 0, index);
                out.flush();
            }
            out.close();
            input.close();
        } catch (IOException e) {
            return error("下载失败");
        }
        return success("下载成功！！");
    }
}
