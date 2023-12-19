package com.hh.procure.controller;

import com.hh.procure.Util.FileConfig;
import com.hh.procure.Util.FileUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    public R handleFileUpload(MultipartFile file) {
        return fileConfig.ruoyiFileUpload(file);
    }


}
