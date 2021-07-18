package com.mou.election.controller;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mou.election.EdataDictionaryManager;
import com.mou.election.WxAppletsManager;
import com.mou.election.excel.UploadDataListener;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EResult;
import com.mou.election.model.WxSendMessageDTO;
import com.mou.election.model.vo.EdataDictionaryVO;
import com.mou.election.service.EdataDictionaryService;
import com.mou.election.service.WxTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * 测试Excel模板导入导出（easyExcel）
 * https://github.com/alibaba/easyexcel
 */
@Slf4j
@RestController
@RequestMapping("/test/")
public class TestController {
    @Autowired
    private EdataDictionaryManager edataDictionaryManager;
    @Autowired
    private WxAppletsManager wxAppletsManager;
    @Autowired
    private WxTemplateService wxTemplateService;

    private String message;


    @GetMapping("template")
    public void generateImportTemplate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        response.setHeader("content-type","application/x-msdownload");
        EasyExcel.write(response.getOutputStream(), EdataDictionaryVO.class).sheet("模板").doWrite(data());
    }


    @PostMapping("import")
    public void importExcels(@RequestParam("file") MultipartFile file) {
        try{
            if (file.isEmpty()) {
                throw new EbizException("Excel_ERROR","导入数据为空");
            }
            String filename = file.getOriginalFilename();
            if (!StringUtils.endsWith(filename, ".xlsx")) {
                throw new EbizException("Excel_ERROR","只支持.xlsx类型文件导入");
            }
            // 开始导入操作
            long beginTimeMillis = System.currentTimeMillis();
            final List<EdataDictionaryVO> data = Lists.newArrayList();
            final List<Map<String, Object>> error = Lists.newArrayList();
            EasyExcel.read(file.getInputStream(), EdataDictionaryVO.class, new UploadDataListener(edataDictionaryManager)).sheet().doRead();
            long time = ((System.currentTimeMillis() - beginTimeMillis));
            ImmutableMap<String, Object> result = ImmutableMap.of(
                    "time", time,
                    "data", data,
                    "error", error
            );
            //return new FebsResponse().data(result);
        }catch (Exception e) {
            message = "导入Excel数据失败," + e.getMessage();
            log.error(message);
            throw new EbizException("Excel_ERROR","导入Excel数据失败");
        }
    }

    @GetMapping(value = "getAccessToken",
            produces = "application/json;charset=utf-8")
    public String getAccessToken() {
        return wxAppletsManager.getWxAccessToken();
    }

    @GetMapping(value = "getTemplate",
            produces = "application/json;charset=utf-8")
    public EResult getTemplate() {
        return EResult.newSuccessInstance(wxTemplateService.getTemplateList());
    }


    @PostMapping(value = "sendMessage",
            produces = "application/json;charset=utf-8")
    public EResult sendTemplateMessage(@RequestBody WxSendMessageDTO dto) {
        return EResult.newSuccessInstance(wxTemplateService.sendWxMessage(dto));
    }
    private List<EdataDictionaryVO> data() {
        List<EdataDictionaryVO> list = new ArrayList();
        IntStream.range(0, 20).forEach(i -> {
            EdataDictionaryVO test = new EdataDictionaryVO();
            test.setGmtCreate(new Date());
            test.setGmtModified(new Date());
            test.setDataType("test"+i);
            test.setDataCode("testCode" + i);
            test.setDataDesc("testDesc" + i);
            test.setDataFeature("testFeature" + i);
            list.add(test);
        });
        return list;
    }


}
