package com.mou.election.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.EorganizationManager;
import com.mou.election.EroleManager;
import com.mou.election.EUserManager;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.enums.LoginTypeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EorganizationDTO;
import com.mou.election.model.EroleDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.service.EuserService;
import com.mou.election.utils.HttpClientUtils;
import com.mou.election.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Service
@Slf4j
public class EuserServiceImpl implements EuserService {

    @Autowired
    private EUserManager userManager;

    @Autowired
    private EroleManager eroleManager;

    @Autowired
    private EorganizationManager eorganizationManager;

    private static Map<String, EUserDTO> map = new HashMap<>();

    @Override
    public EUserDTO login(EUserDTO userDTO) {
        EUserDTO euserDTO = null;
        if (LoginTypeEnum.OPENID.equals(userDTO.getLoginTypeEnum())) {
            if (StringUtil.isEmpty(userDTO.getJsCode())) {
                throw new EbizException(ErrorCodeEnum.PARAM_ERROR);
            }
            String openId = getOpenId(userDTO.getJsCode());
            euserDTO = userManager.getUserByOpenId(openId);
            if (null == euserDTO) {
                throw new EbizException(ErrorCodeEnum.USER_NOT_EXIST);
            }
        }
        if (LoginTypeEnum.PHONE.equals(userDTO.getLoginTypeEnum())) {
            if (StringUtil.isEmpty(userDTO.getPhone()) || StringUtil.isEmpty(userDTO.getPassword())) {
                throw new EbizException(ErrorCodeEnum.PARAM_ERROR);
            }
            euserDTO = userManager.getUserByPhone(userDTO.getPhone());
            if (null == euserDTO) {
                throw new EbizException(ErrorCodeEnum.USER_NOT_EXIST);
            }
            if (!euserDTO.getPassword().equalsIgnoreCase(userDTO.getPassword())) {
                throw new EbizException(ErrorCodeEnum.PASSWORD_NOT_CORRECT);
            }
        }
        this.convertPermissionAndRole(euserDTO);
        return euserDTO;
    }

    @Override
    public void add(EUserDTO userDTO) {
        EUserDTO euserDTO = userManager.getUserByPhone(userDTO.getPhone());
        if (euserDTO != null) {
            throw new EbizException(ErrorCodeEnum.USER_EXIST);
        }
        userManager.add(userDTO);
    }

    @Override
    public void update(EUserDTO euserDTO) {
        userManager.update(euserDTO);
    }

    @Override
    public void delete(Long id) {
        userManager.delete(id);
    }

    @Override
    public EUserDTO getUserById(Long id) {
        EUserDTO euserDTO = userManager.getUserById(id);
        this.convertPermissionAndRole(euserDTO);
        return euserDTO;

    }

    @Override
    public EUserDTO bindOpenId(String jsCode, EUserDTO euserDTO) {
        if (euserDTO == null) {
            throw new EbizException(ErrorCodeEnum.TOKEN_NOT_EXIST);
        }
        if (StringUtil.isNotEmpty(euserDTO.getOpenId())) {
            throw new EbizException(ErrorCodeEnum.OPEN_ID_BIND);
        }
        String openId = getOpenId(jsCode);
        euserDTO.setOpenId(openId);
        return euserDTO;
    }

    private String getOpenId(String jsCode) {
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", EConstants.WEIXIN_APP_ID);
        params.put("secret", EConstants.WEIXIN_SECRET);
        params.put("js_code", jsCode);
        params.put("grant_type", EConstants.GRANT_TYPE);
        String httpResult = HttpClientUtils.doGet(EConstants.URL, params);
        try {
            if (StringUtil.isNotEmpty(httpResult)) {
                Map map = JSON.parseObject(httpResult, Map.class);
                return (String) map.get("openid");
            }
        } catch (Exception e) {
            throw new EbizException(ErrorCodeEnum.TOKEN_OPEN_ID_FAILED);
        }
        return null;

    }


    @Override
    public EUserDTO getUserByPhone(String phone) {
        return userManager.getUserByPhone(phone);
    }

    @Override
    public EUserDTO getUserByOpenId(String openId) {
        return userManager.getUserByOpenId(openId);
    }

    @Override
    public List<EUserDTO> query(HttpServletRequest httpServletRequest, EUserDTO queryDTO) {
        queryPermissionHandle(httpServletRequest, queryDTO);

        List<EUserDTO> userDTOS = userManager.query(queryDTO);
        userDTOS.forEach(this::convertPermissionAndRole);

        return userDTOS;
    }

    private void queryPermissionHandle(HttpServletRequest httpServletRequest, EUserDTO queryDTO) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));
//        TokenUtils.verify(httpServletRequest.getHeader("token"),userId.toString());
        EUserDTO userDTO = getUserById(userId);
        Boolean isAdmin = false;
        if (!CollectionUtils.isEmpty(userDTO.getPermissionDTOS())) {
            for (EPermissionDTO permissionDTO : userDTO.getPermissionDTOS()) {
                if ("query_all_user".equalsIgnoreCase(permissionDTO.getPermissionCode())) {
                    isAdmin = true;
                }
            }
        }
        if (!isAdmin) {
            queryDTO.setOrganizationId(userDTO.getOrganizationId());
        }

    }

    @Override
    public PageInfo<EUserDTO> pageQuery(HttpServletRequest httpServletRequest, EUserDTO queryDTO) {
        queryPermissionHandle(httpServletRequest, queryDTO);
        PageInfo<EUserDTO> pageInfo = userManager.pageQuery(queryDTO);

        pageInfo.getList().forEach(this::convertPermissionAndRole);
        return pageInfo;
    }

    public void convertPermissionAndRole(EUserDTO dto) {
        if (null != dto.getOrganizationId()) {
            EorganizationDTO eorganizationDTO = eorganizationManager.getById(dto.getOrganizationId());
            if (null != eorganizationDTO) {
                dto.setOrganization(eorganizationDTO.getOrganizationName());
            }
        }

        if (StringUtil.isNotEmpty(dto.getFeature())) {
            Map<String, Object> map = JSONObject.parseObject(dto.getFeature(), Map.class);
            List<String> roleCodes = JSONObject.parseArray((String) map.get(EConstants.ROLE), String.class);
            if (CollectionUtils.isEmpty(roleCodes)) {
                return;
            }
            roleCodes.forEach(roleCode -> {
                EroleDTO eroleDTO = eroleManager.getByCode(roleCode);
                if (eroleDTO == null) {
                    return;
                }
                dto.putRoleDTO(eroleDTO);
                if (CollectionUtils.isEmpty(eroleDTO.getPermissionDTOList())) {
                    return;
                }
                eroleDTO.getPermissionDTOList().forEach(permissionDTO -> {
                    dto.putpermissionDTO(permissionDTO);
                });
            });
        }
    }

    @Override
    public Integer count() {
        return userManager.count(new EUserDTO());
    }
}
