package com.mou.election.controller;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mou.election.EdataDictionaryManager;
import com.mou.election.excel.UploadDataListener;
import com.mou.election.exception.EbizException;
import com.mou.election.model.vo.EdataDictionaryVO;
import com.mou.election.service.EdataDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 *
 */
@Slf4j
@Controller
@RequestMapping("/test/")
public class TestController {
    @Autowired
    private EdataDictionaryManager edataDictionaryManager;

    private String message;


    @PostMapping("template")
    public void generateImportTemplate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
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
            if (!data.isEmpty()) {
                // 将合法的记录批量入库
                //this.testService.batchInsert(data);
            }
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
            //throw new EbizException(message);
        }
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
