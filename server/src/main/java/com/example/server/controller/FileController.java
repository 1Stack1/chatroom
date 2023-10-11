package com.example.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Controller
public class FileController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {//未上传文件时
            return "file is empty";
        }
        log.info("文件类型:                " + file.getContentType());
        log.info("文件名称:                " + file.getOriginalFilename());
        log.info("文件大小:                " + file.getSize());
        log.info("文件路径:                ");

        String url = ResourceUtils.getURL("classpath:").getPath() + "static";

        File newFile = new File(url);
        if (!newFile.exists()) {//如果文件夹不存在就新建
            newFile.mkdirs();
        }

        file.transferTo(new File(newFile, "yuzhou.png"));//上传

        return "ha";

    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("file") String file) throws IOException {

        String url = ResourceUtils.getURL("classpath:").getPath() + "static\\" + file;//获取下载的文件的路径

        FileInputStream fileInputStream = new FileInputStream(new File(url));//获取文件输入流

        response.setHeader("content-disposition", "attachment; fileName=" + file);

        ServletOutputStream outputStream = response.getOutputStream();//获取response的输出流

        //TODO 将文件写入response的输出流中
        int len = 0;
        byte[] data = new byte[1024];
        while ((len = fileInputStream.read(data)) != -1) {
            outputStream.write(data);
        }

        outputStream.close();
        fileInputStream.close();
    }
}
