package com.mou.election.service.impl;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageInfo;
import com.mou.election.EApplyManager;
import com.mou.election.EUserManager;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.EroleDTO;
import com.mou.election.service.EApplyService;
import com.mou.election.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
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
    private EUserManager userManager;


    @Override
    public void add(EApplyDTO applyDTO) {
        EApplyDTO queryDTO = new EApplyDTO();
        queryDTO.setUserId(applyDTO.getUserId());
        queryDTO.setStatus(ApplyStatusEnum.PROCESSING);
        List<EApplyDTO> applyDTOS = applyManager.query(applyDTO);
        if (!CollectionUtils.isEmpty(applyDTOS)) {
            throw new EbizException(ErrorCodeEnum.PROCESSIONG_APPLY_EXIST);
        }
        applyManager.add(applyDTO);
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
        EUserDTO userDTO = userManager.getUserById(applyDTO.getUserId());
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
        Long userId = TokenUtils.verify(httpServletRequest.getHeader("token"));
        EUserDTO userDTO = userManager.getUserById(userId);
        Boolean isAdmin = false;
        if (!CollectionUtils.isEmpty(userDTO.getPermissionDTOS())){
            for(EPermissionDTO permissionDTO:userDTO.getPermissionDTOS()){
                if("query_all_user".equalsIgnoreCase(permissionDTO.getPermissionCode())){
                    isAdmin = true;
                }
            }
        }
        if (!isAdmin){
            applyRequest2DTO.setUserId(userId);
        }
        return applyManager.pageQuery(applyRequest2DTO);
    }

    public Integer count(EApplyDTO applyDTO){
        return applyManager.count(applyDTO);
    }


}
