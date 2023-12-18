package com.hh.pms.Util;

import com.jcraft.jsch.*;
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

    public static final String FILENAME = "D:/rouyi/uploadPath/";

    private static final String HOST = "47.236.30.183";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "clk962464A";

    private static final String REMOTE_FILE_PATH = "/opt/file/";


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
                String name = StringPathUtils.replaceHttpToNull(fileName);
                System.out.println("name:" + FileUtil.FILENAME + name);
                Path filePath = Paths.get(FileUtil.FILENAME + name);
                ZipEntry zipEntry = new ZipEntry(StringPathUtils.subStringLastString(fileName));
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

    public static void RemoteFileDownloader(String file, HttpServletResponse response) {
        JSch jsch = new JSch();
        Session session = null;

        try {
            // Create a session with the remote server
            session = jsch.getSession(USERNAME, HOST, 22);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Create a ZipOutputStream to write the files as a zip archive
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=files.zip");
            ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());

            // Iterate through each file and add it to the zip
            String[] remoteFileNames = file.split(",");
            for (String fileName : remoteFileNames) {
                downloadAndAddFileToZip(zipOut, session, fileName);
            }

            // Close the ZipOutputStream
            zipOut.close();

        } catch (JSchException | IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }

    }

    private static void downloadAndAddFileToZip(ZipOutputStream zipOut, Session session, String remoteFileName)
            throws JSchException, IOException {
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        // Specify the remote file path
        String remoteFilePath = REMOTE_FILE_PATH + remoteFileName;
        System.out.println("remoteFilePath:" + remoteFilePath);
        // Open the remote file as an input stream
        try (InputStream inputStream = channelSftp.get(remoteFilePath)) {
            // Add the file to the zip archive
            ZipEntry zipEntry = new ZipEntry(remoteFileName);
            zipOut.putNextEntry(zipEntry);

            // Copy the file content to the ZipOutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                zipOut.write(buffer, 0, bytesRead);
            }

            // Close the ZipEntry
            zipOut.closeEntry();
        } catch (SftpException e) {
            throw new RuntimeException(e);
        } finally {
            // Disconnect the SFTP channel
            channelSftp.disconnect();
        }
    }

    public static R<SysFile> handleFileUpload(MultipartFile[] files) {
        JSch jsch = new JSch();
        Session session = null;

        try {
            // Create a session with the remote server
            session = jsch.getSession(USERNAME, HOST, 22);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Create an SFTP channel
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // Set the time zone to East Asia Standard Time (Shanghai, Beijing)
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(timeZone);

            // Get the current date in the "yyyy-MM-dd" format
            String currentDate = dateFormat.format(new Date());

            // Specify the remote directory path for today's date
            String remoteDirectory = REMOTE_FILE_PATH + currentDate + "/";

            // Check if the remote directory exists; if not, create it
            try {
                channelSftp.cd(remoteDirectory);
            } catch (SftpException e) {
                channelSftp.mkdir(remoteDirectory);
                channelSftp.cd(remoteDirectory);
            }

            // Upload each file
            for (MultipartFile file : files) {
                // Specify the remote file path within today's date directory
                String remoteFilePath = remoteDirectory + file.getOriginalFilename();

                // Upload the file
                channelSftp.put(file.getInputStream(), remoteFilePath);
            }
            // Disconnect the SFTP channel
            channelSftp.disconnect();
        } catch (JSchException | IOException | SftpException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return R.fail(500, "文件上传失败");
        } catch (MaxUploadSizeExceededException e) {
            e.printStackTrace();
            return R.fail(501, "文件最大不可超过5MB");
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        SysFile sysFile = new SysFile();
//        sysFile.setName(FileUtils.getName(url));
//        sysFile.setUrl(url);
        return R.ok(sysFile);
    }

    public static R deleteFile(String fileName) {
        fileName = StringPathUtils.replaceHttpToNull(fileName);
        // 创建Path对象
        Path path = Paths.get(FILENAME + fileName);
        try {

            Files.delete(path);
            R.ok(null,"删除成功！！");
        } catch (Exception e) {
            System.out.println();
            return R.fail("文件删除失败");
        }
        return R.ok();
    }

}
