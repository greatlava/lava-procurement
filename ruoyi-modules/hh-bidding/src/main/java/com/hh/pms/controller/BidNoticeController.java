package com.hh.pms.controller;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.domain.BidNotice;
import com.hh.pms.service.IBidNoticeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 招标公告Controller
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/notice")
public class BidNoticeController extends BaseController
{
    @Autowired
    private IBidNoticeService bidNoticeService;
    @Resource
    private RemoteFileService remoteFileService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private ISysUserService userService;

    /**
     * 查询招标公告列表
     */
    @RequiresPermissions("system:notice:list")
    @GetMapping("/list")
    public TableDataInfo list(BidNotice bidNotice)
    {
        startPage();
        List<BidNotice> list = bidNoticeService.selectBidNoticeList(bidNotice);
        return getDataTable(list);
    }

    /**
     * 导出招标公告列表
     */
    @RequiresPermissions("system:notice:export")
    @Log(title = "招标公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BidNotice bidNotice)
    {
        List<BidNotice> list = bidNoticeService.selectBidNoticeList(bidNotice);
        ExcelUtil<BidNotice> util = new ExcelUtil<BidNotice>(BidNotice.class);
        util.exportExcel(response, list, "招标公告数据");
    }

    /**
     * 获取招标公告详细信息
     */
    @RequiresPermissions("system:notice:query")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bidNoticeService.selectBidNoticeByUid(uid));
    }

    /**
     * 新增招标公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "招标公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BidNotice bidNotice)
    {
        return toAjax(bidNoticeService.insertBidNotice(bidNotice));
    }

    /**
     * 修改招标公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "招标公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BidNotice bidNotice)
    {
        return toAjax(bidNoticeService.updateBidNotice(bidNotice));
    }

    /**
     * 删除招标公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "招标公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bidNoticeService.deleteBidNoticeByUids(uids));
    }


    @RequiresPermissions("system:notice:remove")
    @Log(title = "上传", businessType = BusinessType.DELETE)
    @PostMapping("/upload")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file)
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION))
            {
                return error("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            R<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData()))
            {
                return error("文件服务异常，请联系管理员");
            }
            String url = fileResult.getData().getUrl();
            if (userService.updateUserAvatar(loginUser.getUsername(), url))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", url);
                // 更新缓存用户头像
                loginUser.getSysUser().setAvatar(url);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
}
