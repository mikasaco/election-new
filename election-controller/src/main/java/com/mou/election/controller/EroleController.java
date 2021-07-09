package com.mou.election.controller;

import com.mou.election.annotation.PassToken;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.EResult;
import com.mou.election.model.EroleDTO;
import com.mou.election.model.request.EroleRequest;
import com.mou.election.model.vo.EroleVO;
import com.mou.election.service.EroleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@RestController
@RequestMapping("/role/")
@Slf4j
public class EroleController {

    @Autowired
    private EroleService eroleService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EroleRequest request) {
        eroleService.add(RequestConvert.roleRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EroleRequest request) {
        eroleService.update(RequestConvert.roleRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/delete/{id}")
    public EResult<Boolean> update(@PathVariable Long id) {
        eroleService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    @PassToken
    public EResult<List<EroleVO>> query(@RequestBody EroleRequest request) {
        List<EroleDTO> eroleDTOS = eroleService.query(RequestConvert.roleRequest2DTO(request));
        List<EroleVO> collect = eroleDTOS.stream().map(ResponseConvert::roleDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(collect);
    }

}
