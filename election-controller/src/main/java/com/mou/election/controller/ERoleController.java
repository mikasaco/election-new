package com.mou.election.controller;

import com.github.pagehelper.PageInfo;
import com.mou.election.annotation.PassToken;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EResult;
import com.mou.election.model.EroleDTO;
import com.mou.election.model.request.EPermissionRequest;
import com.mou.election.model.request.EroleRequest;
import com.mou.election.model.vo.EPermissionVO;
import com.mou.election.model.vo.EroleVO;
import com.mou.election.service.ERoleService;
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
public class ERoleController {

    @Autowired
    private ERoleService roleService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EroleRequest request) {
        roleService.add(RequestConvert.roleRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EroleRequest request) {
        roleService.update(RequestConvert.roleRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/delete/{id}")
    public EResult<Boolean> update(@PathVariable Long id) {
        roleService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/get/{id}")
    public EResult<EroleVO> get(@PathVariable Long id) {
        EroleDTO eroleDTO = roleService.get(id);
        return EResult.newSuccessInstance(ResponseConvert.roleDTO2VO(eroleDTO));
    }

    @RequestMapping("query")
    @PassToken
    public EResult<List<EroleVO>> query(@RequestBody EroleRequest request) {
        List<EroleDTO> eroleDTOS = roleService.query(RequestConvert.roleRequest2DTO(request));
        List<EroleVO> collect = eroleDTOS.stream().map(ResponseConvert::roleDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(collect);
    }


    @RequestMapping("pageQuery")
    public EPageResult<List<EroleVO>> pageQuery(@RequestBody EroleRequest request) {
        PageInfo<EroleDTO> pageInfo = roleService.pageQuery(RequestConvert.roleRequest2DTO(request));
        List<EroleVO> eroleVOS = pageInfo.getList().stream().map(ResponseConvert::roleDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(pageInfo.getTotal(),eroleVOS);
    }
}
