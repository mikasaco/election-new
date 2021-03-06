package com.mou.election.service.impl;

import com.github.pagehelper.PageInfo;
import com.mou.election.EApplyManager;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.WxSendMessageDTO;
import com.mou.election.service.EApplyService;
import com.mou.election.service.EuserService;
import com.mou.election.service.WxTemplateService;
import com.mou.election.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EApplyServiceImpl implements EApplyService {

    @Autowired
    private EApplyManager applyManager;

    @Autowired
    private EuserService euserService;
    @Autowired
    private WxTemplateService wxTemplateService;


    @Override
    public void add(EApplyDTO applyDTO) {
        EApplyDTO queryDTO = new EApplyDTO();
        queryDTO.setUserId(applyDTO.getUserId());
        queryDTO.setStatus(ApplyStatusEnum.PROCESSING);
        List<EApplyDTO> applyDTOS = applyManager.query(queryDTO);
        if (!CollectionUtils.isEmpty(applyDTOS)) {
            throw new EbizException(ErrorCodeEnum.PROCESSIONG_APPLY_EXIST);
        }
        applyDTO.setStatus(ApplyStatusEnum.PROCESSING);
        //applyDTO.setSendStatus(EConstants.APPLY_SEND_STATUS);
        applyManager.add(applyDTO);
        wxTemplateService.sendWxMessage(wxTemplateService.getUserManagerAuditMessage());
    }

    @Override
    public void update(EApplyDTO applyDTO) {
        applyManager.update(applyDTO);
    }

    @Override
    public void delete(Long id) {
        applyManager.delete(id);
    }

    @Override
    public EApplyDTO get(Long id) {
        EApplyDTO applyDTO = applyManager.get(id);
        EUserDTO userDTO = euserService.getUserById(applyDTO.getUserId());
        applyDTO.setUserDTO(userDTO);
        return applyDTO;
    }

    @Override
    public List<EApplyDTO> query(EApplyDTO queryDTO) {
        applyManager.query(queryDTO);
        return null;
    }

    @Override
    public PageInfo<EApplyDTO> pageQuery(HttpServletRequest httpServletRequest, EApplyDTO applyRequest2DTO) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));
        EUserDTO userDTO = euserService.getUserById(userId);
        Boolean isAdmin = false;
        if (!CollectionUtils.isEmpty(userDTO.getPermissionDTOS())) {
            for (EPermissionDTO permissionDTO : userDTO.getPermissionDTOS()) {
                if ("query_all_user".equalsIgnoreCase(permissionDTO.getPermissionCode())) {
                    isAdmin = true;
                }
            }
        }
        if (!isAdmin) {
            applyRequest2DTO.setUserId(userId);
        }
        PageInfo<EApplyDTO> pageInfo = applyManager.pageQuery(applyRequest2DTO);
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            pageInfo.getList().forEach(applyDTO -> {
                applyDTO.setUserDTO(euserService.getUserById(applyDTO.getUserId()));
            });
        }
        return pageInfo;
    }

    public Integer count(EApplyDTO applyDTO) {
        return applyManager.count(applyDTO);
    }


}
