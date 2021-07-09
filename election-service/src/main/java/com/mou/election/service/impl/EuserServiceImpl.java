package com.mou.election.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.EroleManager;
import com.mou.election.EUserManager;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.enums.LoginTypeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EroleDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.service.EuserService;
import com.mou.election.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
public class EuserServiceImpl implements EuserService {

    @Autowired
    private EUserManager userManager;

    @Autowired
    private EroleManager eroleManager;

    private static Map<String, EUserDTO> map = new HashMap<>();

    @Override
    public EUserDTO login(EUserDTO userDTO) {
        EUserDTO euserDTO = null;
        if (LoginTypeEnum.OPENID.equals(userDTO.getLoginTypeEnum())) {
            if (StringUtil.isEmpty(userDTO.getOpenId())) {
                throw new EbizException(ErrorCodeEnum.WEIXIN_NOT_BIND);
            }
            euserDTO = userManager.getUserByOpenId(userDTO.getOpenId());
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
        return euserDTO;
    }

    @Override
    public void bindOpenId(String jsCode, EUserDTO euserDTO) {
        if (euserDTO == null) {
            throw new EbizException(ErrorCodeEnum.TOKEN_NOT_EXIST);
        }
        if (StringUtil.isNotEmpty(euserDTO.getOpenId())) {
            throw new EbizException(ErrorCodeEnum.OPEN_ID_BIND);
        }
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", EConstants.WEIXIN_APP_ID);
        params.put("secret", EConstants.WEIXIN_SECRET);
        params.put("js_code", jsCode);
        params.put("grant_type", EConstants.GRANT_TYPE);
        String openId = HttpClientUtils.doGet(EConstants.URL, params);
        if (StringUtil.isNotEmpty(openId)) {
            throw new EbizException(ErrorCodeEnum.TOKEN_OPEN_ID_FAILED);
        }
        euserDTO.setOpenId(openId);
        userManager.update(euserDTO);
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
    public List<EUserDTO> query(EUserDTO queryDTO) {
        List<EUserDTO> userDTOS = userManager.query(queryDTO);
        userDTOS.forEach(dto -> {
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
                    dto.putRoleCode(roleCode);
                    if (CollectionUtils.isEmpty(eroleDTO.getPermissionDTOList())) {
                        return;
                    }
                    eroleDTO.getPermissionDTOList().forEach(permissionDTO -> {
                        dto.putpermissionCode(permissionDTO.getPermissionCode());
                    });
                });
            }
        });
        return userDTOS;
    }

    @Override
    public PageInfo<EUserDTO> pageQuery(EUserDTO queryDTO){
        return userManager.pageQuery(queryDTO);
    }
}
