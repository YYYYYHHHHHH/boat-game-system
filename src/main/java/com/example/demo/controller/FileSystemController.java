package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.serviceImpl.FileSystemServiceImpl;
import com.example.demo.utils.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileSystemController {
    @Autowired
    private FileSystemServiceImpl serviceImpl;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public boolean upload(@RequestPart("file") MultipartFile multipartFile, @RequestParam("path") String path)
            throws Exception {

        return serviceImpl.uploadFile(path, FileUtils.multipartFileToFile(multipartFile));
    }

    @GetMapping(value = "/download")
    public void download(@RequestParam("targetPath") String targetPath, HttpServletResponse response) throws Exception {
        File file = serviceImpl.downloadFile(targetPath);

        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment; fileName=" + file.getName());

        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(file);

        OutputStream os = response.getOutputStream();

        int i = fis.read(buffer);
        while (i != -1) {
            os.write(buffer, 0, i);
            i = fis.read(buffer);
        }
        fis.close();
        os.close();

        // return true;
    }

}
