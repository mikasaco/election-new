package com.mou.election.controller;

import com.github.pagehelper.PageInfo;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.EResult;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EPageResult;
import com.mou.election.model.request.EPermissionRequest;
import com.mou.election.model.vo.EPermissionVO;
import com.mou.election.service.EPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
@RestController
@RequestMapping("/permission/")
public class EPermissionController {

    @Autowired
    private EPermissionService permissionService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EPermissionRequest request) {
        permissionService.add(RequestConvert.permissionRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EPermissionRequest request) {
        permissionService.update(RequestConvert.permissionRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "delete/{id}")
    public EResult<Boolean> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    public EResult<List<EPermissionVO>> query(@RequestBody EPermissionRequest request) {
        List<EPermissionDTO> query = permissionService.query(RequestConvert.permissionRequest2DTO(request));
        List<EPermissionVO> collect = query.stream().map(ResponseConvert::permissionDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(collect);
    }

    @RequestMapping("pageQuery")
    public EPageResult<EPermissionVO> pageQuery(@RequestBody EPermissionRequest request) {
        PageInfo<EPermissionDTO> pageInfo = permissionService.pageQuery(RequestConvert.permissionRequest2DTO(request));
        List<EPermissionVO> permissionVOS = pageInfo.getList().stream().map(ResponseConvert::permissionDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(pageInfo,permissionVOS);
    }
}












