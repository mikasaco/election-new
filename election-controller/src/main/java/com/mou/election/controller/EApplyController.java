package com.mou.election.controller;

import com.mou.election.EUserManager;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EResult;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.request.EApplyRequest;
import com.mou.election.model.vo.EApplyVO;
import com.mou.election.service.EApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apply/")
@Slf4j
public class EApplyController {

    @Autowired
    private EApplyService applyService;

    @Autowired
    private EUserManager userManager;

    @RequestMapping("add")
    public EResult<Boolean> add(HttpServletRequest httpServletRequest, @RequestBody EApplyRequest request){
        EUserDTO userDTO = userManager.getUserDTO(httpServletRequest);
        request.setUserId(userDTO.getId());
        applyService.add(RequestConvert.applyRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EApplyRequest request){
        applyService.update(RequestConvert.applyRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/delete/{id}")
    public EResult<Boolean> delete(@PathVariable Long id){
        applyService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/get/{id}")
    public EResult<EApplyVO> get(@PathVariable Long id){
        EApplyDTO applyDTO = applyService.get(id);
        return EResult.newSuccessInstance(ResponseConvert.applyDTO2VO(applyDTO));
    }

    @RequestMapping("query")
    public EResult<List<EApplyVO>> query(@RequestBody EApplyRequest request){
        List<EApplyDTO> query = applyService.query(RequestConvert.applyRequest2DTO(request));
        List<EApplyVO> vos = query.stream().map(ResponseConvert::applyDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(vos);
    }

//    @RequestMapping("pageQuery")
//    public EPageResult<List<EorganizationVO>> pageQuery(@RequestBody EApplyRequest request){
//        PageInfo<EorganizationDTO> result = applyService.pageQuery(RequestConvert.organizationRequest2DTO(request));
//        List<EorganizationVO> permissionVOS = result.getList().stream().map(ResponseConvert::organizationDTO2VO).collect(Collectors.toList());
//        return EPageResult.newSuccessInstance(result.getTotal(),permissionVOS);
//    }
}
