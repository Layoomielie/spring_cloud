package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author：张鸿建
 * @time：2019/7/20 10:24
 * @desc：
 **/
@Controller
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam(value = "file",required = true) MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes,fileSave);
        return fileSave.getAbsolutePath();
    }
}
