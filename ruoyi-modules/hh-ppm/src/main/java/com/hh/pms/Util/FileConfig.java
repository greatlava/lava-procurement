package com.hh.pms.Util;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileConfig {
    @Autowired
    private RemoteFileService remoteFileService;

    public AjaxResult ruoyiFileUpload(MultipartFile file) {

        if (!file.isEmpty()) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            R<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return AjaxResult.error("文件服务异常，请联系管理员");
            }
            return AjaxResult.success(fileResult);
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
}
