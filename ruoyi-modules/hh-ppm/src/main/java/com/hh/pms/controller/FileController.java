package com.hh.pms.controller;

import com.hh.pms.Util.FileConfig;
import com.hh.pms.Util.FileUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import org.apache.commons.io.file.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileConfig fileConfig;

    @GetMapping("/downloadFiles")
    public void downloadFiles(@RequestParam("file") String file, HttpServletResponse response) {
        FileUtil.downloadFiles(file, response);
    }

    @RequestMapping("/RemoteFileDownloader")
    public void RemoteFileDownloader(@RequestParam("file") String file, HttpServletResponse response) {
        FileUtil.RemoteFileDownloader(file, response);
    }

    @PostMapping("/upload")
    public AjaxResult handleFileUpload(MultipartFile file) {
        return fileConfig.ruoyiFileUpload(file);
    }


}
