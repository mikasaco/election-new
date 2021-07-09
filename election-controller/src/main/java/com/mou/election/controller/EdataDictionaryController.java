package com.mou.election.controller;

import com.mou.election.EdataDictionaryManager;
import com.mou.election.annotation.UserLoginToken;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EResult;
import com.mou.election.model.EdataDictionaryDTO;
import com.mou.election.model.request.EdataDictionaryRequest;
import com.mou.election.model.vo.EdataDictionaryVO;
import com.mou.election.service.EdataDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@RestController
@RequestMapping("/dataDictionary/")
@Slf4j
public class EdataDictionaryController {

    @Autowired
    private EdataDictionaryService edataDictionaryService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EdataDictionaryRequest dictionaryRequest) {
        edataDictionaryService.add(RequestConvert.dataDictionaryRequest2DTO(dictionaryRequest));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    public EResult<List<EdataDictionaryVO>> query(@RequestBody EdataDictionaryRequest dictionaryRequest) {

        List<EdataDictionaryDTO> dictionaryDTOS = edataDictionaryService.query(RequestConvert.dataDictionaryRequest2DTO(dictionaryRequest));
        List<EdataDictionaryVO> dictionaryVOS = dictionaryDTOS.stream().map(ResponseConvert::dataDictionaryDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(dictionaryVOS);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EdataDictionaryRequest dictionaryRequest) {
        edataDictionaryService.update(RequestConvert.dataDictionaryRequest2DTO(dictionaryRequest));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("delete/{id}")
    public EResult<Boolean> update(@PathVariable Long id) {
        edataDictionaryService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }
}
