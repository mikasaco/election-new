package com.mou.election.controller;

import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.EResult;
import com.mou.election.model.EorganizationDTO;
import com.mou.election.model.request.EorganizationRequest;
import com.mou.election.model.vo.EorganizationVO;
import com.mou.election.service.EorganizationService;
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
@RequestMapping("/organization/")
@Slf4j
public class EorganizationController {

    @Autowired
    private EorganizationService eorganizationService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EorganizationRequest request){
        eorganizationService.add(RequestConvert.organizationRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EorganizationRequest request){
        eorganizationService.update(RequestConvert.organizationRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public EResult<Boolean> update(@PathVariable Long id){
        eorganizationService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    public EResult<List<EorganizationVO>> query(@RequestBody EorganizationRequest request){
        List<EorganizationDTO> query = eorganizationService.query(RequestConvert.organizationRequest2DTO(request));
        List<EorganizationVO> vos = query.stream().map(ResponseConvert::organizationDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(vos);
    }

}
