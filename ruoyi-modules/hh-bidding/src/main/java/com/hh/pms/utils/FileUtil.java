package com.hh.pms.utils;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysFile;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    public static void downloadFiles(String file, HttpServletResponse response) {
        try {
            System.out.println("file:" + file);
            // 设置响应头，指定为zip文件
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=files.zip");
            // 创建ZipOutputStream
            ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
            //拆分文件名
            String[] fileNames = file.split(",");
            // 将多个文件打包到zip文件中
            for (String fileName : fileNames) {
                Path filePath = Paths.get(fileName);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);

                // 将文件内容写入ZipOutputStream
                Files.copy(filePath, zipOut);
            }
            // 关闭ZipOutputStream
            zipOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
