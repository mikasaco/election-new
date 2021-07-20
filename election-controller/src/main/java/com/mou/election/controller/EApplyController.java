package com.mou.election.controller;

import com.github.pagehelper.PageInfo;
import com.mou.election.EUserManager;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EResult;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.request.EApplyRequest;
import com.mou.election.model.vo.ApplyTotalVO;
import com.mou.election.model.vo.EApplyVO;
import com.mou.election.service.EApplyService;
import com.mou.election.utils.TokenUtils;
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
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));
        request.setUserId(userId);
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

    @RequestMapping("pageQuery")
    public EPageResult<EApplyVO> pageQuery(HttpServletRequest httpServletRequest,@RequestBody EApplyRequest request){
        PageInfo<EApplyDTO> result = applyService.pageQuery(httpServletRequest,RequestConvert.applyRequest2DTO(request));
        List<EApplyVO> permissionVOS = result.getList().stream().map(ResponseConvert::applyDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(result.getTotal(),permissionVOS);
    }

    @RequestMapping("count")
    public EResult<ApplyTotalVO> count(HttpServletRequest httpServletRequest, @RequestBody EApplyRequest request){
        ApplyTotalVO applyTotalVO = new ApplyTotalVO();
        EApplyDTO applyDTO = RequestConvert.applyRequest2DTO(request);


        Integer totalCount = applyService.count(applyDTO);
        applyTotalVO.setTotalApplyNum(totalCount);

        applyDTO.setStatus(ApplyStatusEnum.PROCESSING);
        Integer processingCount = applyService.count(applyDTO);

        applyDTO.setStatus(ApplyStatusEnum.PASS);
        Integer passCount = applyService.count(applyDTO);

        applyDTO.setStatus(ApplyStatusEnum.REJECTED);
        Integer rejectedCount = applyService.count(applyDTO);

        applyTotalVO.setTotalApplyNum(totalCount);
        applyTotalVO.setProcessingApplyNum(processingCount);
        applyTotalVO.setFinishApplyNum(passCount+rejectedCount);

        return EResult.newSuccessInstance(applyTotalVO);
    }
}
