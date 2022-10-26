package com.example.server.controller;

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

@Controller
public class FileController {
    @GetMapping("/file")
    public String getFile(){
        return "file";
    }

    @GetMapping("/downfile")
    public String file(){
        return "down";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            return "file is empty";
        }
        System.out.println("文件类型:                "+file.getContentType());
        System.out.println("文件名称:                "+file.getOriginalFilename());
        System.out.println("文件大小:                "+file.getSize());
        System.out.println("文件路径:                ");
        String url= ResourceUtils.getURL("classpath:").getPath()+"static";
        File newFile=new File(url);
        if(!newFile.exists()) newFile.mkdirs();
        file.transferTo(new File(newFile,"yuzhou.png"));
        return "ha";

    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("file") String file) throws IOException {
        String url=ResourceUtils.getURL("classpath:").getPath()+"static\\"+file;
        FileInputStream fileInputStream = new FileInputStream(new File(url));

        response.setHeader("content-disposition", "attachment; fileName=" + file);
        ServletOutputStream outputStream = response.getOutputStream();
        int len=0;
        byte[] data=new byte[1024];
        while((len=fileInputStream.read(data))!=-1){
            outputStream.write(data);
        }
        outputStream.close();
        fileInputStream.close();
    }
}
