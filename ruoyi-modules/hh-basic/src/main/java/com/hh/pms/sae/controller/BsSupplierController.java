package com.hh.pms.sae.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.sae.domain.BsSupplier;
import com.hh.pms.sae.service.IBsSupplierService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 供应商Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/supplier")
public class BsSupplierController extends BaseController {
    @Autowired
    private IBsSupplierService bsSupplierService;

    @Autowired
    private RemoteFileService remoteFileService;

    @PostMapping("/upload1")
    public AjaxResult upload1(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            R<SysFile> fileResult = remoteFileService.upload(file);
            System.out.println("fileResult:" + fileResult.getData().getName() + "\t" + fileResult.getData().getUrl());
            System.out.println("文件上传成功！。。。。");
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return AjaxResult.error("文件服务异常，请联系管理员");
            }
            return AjaxResult.success(fileResult);
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }

    /**
     * 查询合格供应商列表
     */
    @RequiresPermissions("system:supplier:list")
    @GetMapping("/list")
    public TableDataInfo list(BsSupplier bsSupplier) {
        startPage();
        List<BsSupplier> list = bsSupplierService.selectBsSupplierList(bsSupplier);
        return getDataTable(list);
    }

    /**
     * 查询不合格供应商列表
     */
    @RequiresPermissions("system:supplier:list")
    @GetMapping("/noSupplierList")
    public TableDataInfo noSupplierList(BsSupplier bsSupplier) {
        startPage();
        List<BsSupplier> list = bsSupplierService.selectNoBsSupplierList(bsSupplier);
        return getDataTable(list);
    }

    /**
     * 查询供应商不良记录列表
     */
    @RequiresPermissions("system:supplier:list")
    @GetMapping("/supplierBadList")
    public TableDataInfo supplierBadList(BsSupplier bsSupplier) {
        startPage();
        List<BsSupplier> list = bsSupplierService.selectBsSupplierBadList(bsSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表
     */
    @RequiresPermissions("system:supplier:export")
    @Log(title = "供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsSupplier bsSupplier) {
        List<BsSupplier> list = bsSupplierService.selectBsSupplierList(bsSupplier);
        ExcelUtil<BsSupplier> util = new ExcelUtil<BsSupplier>(BsSupplier.class);
        util.exportExcel(response, list, "供应商数据");
    }

    /**
     * 获取供应商详细信息
     */
    @RequiresPermissions("system:supplier:query")
    @GetMapping(value = "/{hid}")
    public AjaxResult getInfo(@PathVariable("hid") Long hid) {
        BsSupplier bsSupplier = bsSupplierService.selectBsSupplierByHid(hid);
        String copy = bsSupplier.gethJuridicalCopies();
        if (copy != null) {
            String[] arr = copy.split(",");
            bsSupplier.setIdCardCopies(arr);
        }
        return success(bsSupplier);
    }

    /**
     * 获取准入供应商详细信息
     */
    @RequiresPermissions("system:supplier:query")
    @GetMapping("/access/{zrId}")
    public AjaxResult getInfoByZrId(@PathVariable("zrId") Long zrId) {
        BsSupplier bsSupplier = bsSupplierService.selectBsSupplierByZrId(zrId);
        String copy = bsSupplier.gethJuridicalCopies();
        if (copy != null) {
            String[] arr = copy.split(",");
            bsSupplier.setIdCardCopies(arr);
        }
        return success(bsSupplier);
    }

    /**
     * 新增供应商
     */
    @RequiresPermissions("system:supplier:add")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsSupplier bsSupplier) {
        return toAjax(bsSupplierService.insertBsSupplier(bsSupplier));
    }

    /**
     * 修改供应商
     */
    @RequiresPermissions("system:supplier:edit")
    @Log(title = "供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsSupplier bsSupplier) {
        return toAjax(bsSupplierService.updateBsSupplier(bsSupplier));
    }

    /**
     * 删除供应商
     */
    @RequiresPermissions("system:supplier:remove")
    @Log(title = "供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{hids}")
    public AjaxResult remove(@PathVariable Long[] hids) {
        return toAjax(bsSupplierService.deleteBsSupplierByHids(hids));
    }
}
