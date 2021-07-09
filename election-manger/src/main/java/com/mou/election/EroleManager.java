package com.mou.election;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.constants.EConstants;
import com.mou.election.convert.EroleConvert;
import com.mou.election.dal.domian.EroleDO;
import com.mou.election.dal.domian.EroleDOExample;
import com.mou.election.dal.mapper.EroleDOMapper;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EroleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Component
@Slf4j
public class EroleManager {

    @Autowired
    private EroleDOMapper eroleDOMapper;

    @Autowired
    private EPermissionManager epermissionManager;

    public void add(EroleDTO addDTO) {
        EroleDO eroleDO = EroleConvert.dto2do(addDTO);
        eroleDO.setGmtCreate(new Date());
        eroleDO.setGmtModified(new Date());
        eroleDOMapper.insert(eroleDO);
    }

    public void update(EroleDTO updateDTO) {
        EroleDO eroleDO = EroleConvert.dto2do(updateDTO);
        eroleDO.setGmtModified(new Date());
        eroleDOMapper.updateByPrimaryKeySelective(eroleDO);
    }

    public void delete(Long id) {
        eroleDOMapper.deleteByPrimaryKey(id);
    }

    public EroleDTO get(Long id) {
        EroleDO eroleDO = eroleDOMapper.selectByPrimaryKey(id);
        if (eroleDO == null) {
            return null;
        }

        EroleDTO eroleDTO = EroleConvert.do2dto(eroleDO);
        this.correlatePermission(eroleDTO);
        return eroleDTO;
    }

    public EroleDTO getByCode(String code) {
        EroleDOExample example = new EroleDOExample();
        EroleDOExample.Criteria criteria = example.createCriteria();
        criteria.andRoleCodeEqualTo(code);
        List<EroleDO> eroleDOS = eroleDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(eroleDOS)) {
            return null;
        }
        if (eroleDOS.size() > 1) {
            log.error("code = {} query DB has more then 1 role", code);
            throw new EbizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        EroleDTO eroleDTO = EroleConvert.do2dto(eroleDOS.get(0));
        this.correlatePermission(eroleDTO);
        return eroleDTO;
    }

    public List<EroleDTO> query(EroleDTO queryDTO) {
        EroleDOExample example = new EroleDOExample();
        EroleDOExample.Criteria criteria = example.createCriteria();
        if (queryDTO.getRoleName() != null) {
            criteria.andRoleNameLike("%" + queryDTO.getRoleName() + "%");
        }
        if (queryDTO.getRoleCode() != null) {
            criteria.andRoleCodeLike("%" + queryDTO.getRoleCode() + "%");
        }
        List<EroleDO> eroleDOS = eroleDOMapper.selectByExample(example);
        return eroleDOS.stream().map(eroleDO -> {
            EroleDTO eroleDTO = EroleConvert.do2dto(eroleDO);
            this.correlatePermission(eroleDTO);
            return eroleDTO;
        }).collect(Collectors.toList());
    }

    private EroleDTO correlatePermission(EroleDTO roleDTO) {
        if (StringUtil.isNotEmpty(roleDTO.getFeature())) {
            Map<String, Object> map = JSONObject.parseObject(roleDTO.getFeature(), Map.class);
            List<String> permissionCodes = JSONObject.parseArray((String) map.get(EConstants.PERMISSION), String.class);
            if (!CollectionUtils.isEmpty(permissionCodes)) {
                permissionCodes.forEach(code -> {
                    EPermissionDTO permissionDTO = epermissionManager.getByCode(code);
                    if (permissionDTO != null) {
                        roleDTO.putPermission(permissionDTO);
                    }
                });
            }
        }
        return roleDTO;

    }
}
