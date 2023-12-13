package com.hh.pms.sae.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.hh.pms.sae.domain.BsAccess;
import com.hh.pms.sae.service.IBsAccessService;
import com.hh.pms.sae.utils.CodeUtils;
import com.hh.pms.sae.utils.TokenUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private IBsAccessService bsAccessService;

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
            return AjaxResult.success(fileResult.getData());
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }

    /**
     * 生成验证码图片
     *
     * @param request
     * @param res
     * @throws IOException
     */
    @GetMapping("/code")
    public AjaxResult code(HttpServletRequest request,
                           HttpServletResponse res) throws IOException {
        CodeUtils code = new CodeUtils();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("code", text);
        CodeUtils.output(image, res.getOutputStream());
        return AjaxResult.success(res);
    }

    @PostMapping("/loginSupplier")
    public AjaxResult login(String userName, String pass, String code, HttpServletRequest request,
                            HttpServletResponse response) {
        System.out.println("userName" + userName + "\t" + pass);
        BsSupplier bsSupplier = bsSupplierService.loginSupplier(userName, pass);
        JSONObject jsonObject = new JSONObject();

        //判断code是否正确
        if (request.getSession(true).getAttribute("code") == null || !code.toUpperCase().equals(request.getSession(true).getAttribute("code").toString().toUpperCase())) {
            return AjaxResult.error("code error");
        }

        if (bsSupplier != null) {
            String token = TokenUtil.sign(bsSupplier);
            jsonObject.put("token", token);
            jsonObject.put("bsSupplier", bsSupplier);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200);
            return AjaxResult.success(jsonObject);
        }
        return AjaxResult.error("用户名或者密码错误");
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
//    @RequiresPermissions("system:supplier:add")
    @Log(title = "供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsSupplier bsSupplier) {
        // 生成年月日字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(new Date());

        // 生成UUID
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        // 取UUID的后六位作为六位随机数
        String randomStr = uuidString.substring(uuidString.length() - 6);

        //准入编号
        String result = "ZR" + dateStr + randomStr;

        BsAccess access = new BsAccess();
        access.setZrBnumber(result);
        access.setZrPromoter(bsSupplier.gethJuridical());
        BsAccess s = bsAccessService.insertBsAccess(access);
        System.out.println(s);
        if (s != null) {
            bsSupplier.setZrId(s.getZrId());
            return toAjax(bsSupplierService.insertBsSupplier(bsSupplier));
        }
        return AjaxResult.error("操作失败");
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
