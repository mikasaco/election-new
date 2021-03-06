package com.mou.election.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mou.election.EUserManager;
import com.mou.election.annotation.PassToken;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.*;
import com.mou.election.model.request.EOrgUserCountRequest;
import com.mou.election.model.request.EUserReqeust;
import com.mou.election.model.vo.EUserLoginVO;
import com.mou.election.model.vo.EUserVO;
import com.mou.election.service.EuserService;
import com.mou.election.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class EUserController {

    @Autowired
    private EuserService euserService;

    @Autowired
    private EUserManager euserManager;

    @RequestMapping("login")
    @PassToken
    public EResult<EUserLoginVO> login(@RequestBody EUserReqeust request) {
        EUserDTO userDTO = RequestConvert.userRequest2DTO(request);

        EUserDTO euserDTO = euserService.login(userDTO);
        String token = TokenUtils.getToken(euserDTO.getId());
        EUserLoginVO loginVO = new EUserLoginVO();
        loginVO.setToken(token);
        loginVO.setUserVO(ResponseConvert.userDTO2VO(euserDTO));
        return EResult.newSuccessInstance(loginVO);
    }

    @RequestMapping("bindOpenId/{jsCode}")
    public EResult<Boolean> bindOpenId(HttpServletRequest httpServletRequest, @PathVariable String jsCode) {
        EUserDTO userDTO = euserManager.getUserDTO(httpServletRequest);
        EUserDTO eUserDTO = euserService.bindOpenId(jsCode, userDTO);
        euserService.update(eUserDTO);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EUserReqeust request) {
        EUserDTO userDTO = RequestConvert.userRequest2DTO(request);
        euserService.add(userDTO);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EUserReqeust request) {
        EUserDTO userDTO = RequestConvert.userRequest2DTO(request);
        euserService.update(userDTO);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    public EResult<List<EUserVO>> query(HttpServletRequest httpServletRequest,@RequestBody EUserReqeust request) {
        EUserDTO userDTO = RequestConvert.userRequest2DTO(request);
        List<EUserDTO> userDTOS = euserService.query(httpServletRequest,userDTO);
        List<EUserVO> euserVOS = userDTOS.stream().map(ResponseConvert::userDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(euserVOS);
    }

    @RequestMapping("get/{id}")
    public EResult<EUserVO> get(@PathVariable Long id) {
        EUserDTO userDTO = euserService.getUserById(id);
        return EResult.newSuccessInstance(ResponseConvert.userDTO2VO(userDTO));
    }

    @RequestMapping("delete/{id}")
    public EResult<Boolean> delete(@PathVariable Long id) {
        euserService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("queryMySelf")
    public EResult<EUserVO> queryMySelf(HttpServletRequest httpServletRequest) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));

        EUserDTO userDTO = euserService.getUserById(userId);
        return EResult.newSuccessInstance(ResponseConvert.userDTO2VO(userDTO));
    }


    @RequestMapping("pageQuery")
    public EPageResult<List<EUserVO>> pageQuery(HttpServletRequest httpServletRequest,@RequestBody EUserReqeust request) {
        PageInfo<EUserDTO> pageInfo = euserService.pageQuery(httpServletRequest,RequestConvert.userRequest2DTO(request));
        List<EUserVO> eroleVOS = pageInfo.getList().stream().map(ResponseConvert::userDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(pageInfo.getTotal(), eroleVOS);
    }

    @RequestMapping("orgUserCount")
    public EResult<Object> countOrgId(@RequestBody EOrgUserCountRequest userCount) {
        EOrgUserCountDTO eOrgUserCountDTO = RequestConvert.orgUserRequestToDTO(userCount);
        PageInfo<EUserDTO> userPage = euserService.getOrgUserCount(eOrgUserCountDTO);
//userPage.getList().stream().map(ResponseConvert::userDTO2VO)
        return EPageResult.newSuccessInstance(userPage.getTotal());
    }

}
