package com.mou.election.controller;

import com.github.pagehelper.PageInfo;
import com.mou.election.EApplyManager;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.model.*;
import com.mou.election.model.request.EApplyRequest;
import com.mou.election.model.request.EorganizationRequest;
import com.mou.election.model.vo.ApplyTotalVO;
import com.mou.election.model.vo.EPermissionVO;
import com.mou.election.model.vo.EorganizationVO;
import com.mou.election.model.vo.OrganizationTotalVO;
import com.mou.election.service.EorganizationService;
import com.mou.election.service.EuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private EuserService euserService;

    @Autowired
    private EApplyManager applyManager;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody EorganizationRequest request) {
        eorganizationService.add(RequestConvert.organizationRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("update")
    public EResult<Boolean> update(@RequestBody EorganizationRequest request) {
        eorganizationService.update(RequestConvert.organizationRequest2DTO(request));
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping(value = "/delete/{id}")
    public EResult<Boolean> delete(@PathVariable Long id) {
        eorganizationService.delete(id);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @RequestMapping("query")
    public EResult<List<EorganizationVO>> query(@RequestBody EorganizationRequest request) {
        List<EorganizationDTO> query = eorganizationService.query(RequestConvert.organizationRequest2DTO(request));
        List<EorganizationVO> vos = query.stream().map(ResponseConvert::organizationDTO2VO).collect(Collectors.toList());
        return EResult.newSuccessInstance(vos);
    }

    @RequestMapping("pageQuery")
    public EPageResult<List<EorganizationVO>> pageQuery(@RequestBody EorganizationRequest request) {
        PageInfo<EorganizationDTO> result = eorganizationService.pageQuery(RequestConvert.organizationRequest2DTO(request));
        List<EorganizationVO> permissionVOS = result.getList().stream().map(ResponseConvert::organizationDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(result.getTotal(), permissionVOS);
    }

    @RequestMapping("count")
    public EResult<OrganizationTotalVO> count(HttpServletRequest httpServletRequest, @RequestBody EorganizationRequest request) {
        OrganizationTotalVO totalVO = new OrganizationTotalVO();
        EorganizationDTO eorganizationDTO = RequestConvert.organizationRequest2DTO(request);

        List<EorganizationDTO> eorganizationDTOS = eorganizationService.query(eorganizationDTO);
        totalVO.setOrganizationNum(eorganizationDTOS.size());

        List<EUserDTO> userDTOS = euserService.query(httpServletRequest, new EUserDTO());
        totalVO.setUserNum(userDTOS.size());

        int applyCount = 0;
        for (EUserDTO userDTO : userDTOS) {
            EApplyDTO applyDTO = new EApplyDTO();
            applyDTO.setUserId(userDTO.getId());
            List<EApplyDTO> query = applyManager.query(applyDTO);
            if (!CollectionUtils.isEmpty(query)) {
                applyCount++;
            }
        }

        totalVO.setApplyUserNum(applyCount);
        return EResult.newSuccessInstance(totalVO);
    }

}
