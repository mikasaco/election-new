/**
 * $Id: FileController.java,v 1.0 2021/7/29 21:15 zengws Exp $
 * <p>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.mou.election.controller;

import com.alibaba.excel.EasyExcel;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EResult;
import com.mou.election.model.vo.EdataDictionaryVO;
import com.mou.election.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.UUID;

/**
 * @author zengws
 * @version $Id: FileController.java,v 1.1 2021/7/29 21:15 zengws Exp $
 * Created on 2021/7/29 21:15
 */
@RestController("/fileUpload/")
public class FileController {
    @PostMapping(value = "image")
    public EResult<String> fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new EbizException(ErrorCodeEnum.FILE_UPLOAD_ERROR);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        Long userId = TokenUtils.getUserIdByToken(token);

        //获取文件格式
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = userId+"image"+suffixName;
        String filePath = getFilePath(); // 上传后的路径
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EResult.newSuccessInstance(fileName);
    }

    private String getFilePath() {
        return System.getProperties().getProperty("user.dir") + "/image";
    }


}