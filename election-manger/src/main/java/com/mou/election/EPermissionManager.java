package com.mou.election;

import com.mou.election.convert.EpermissionConvert;
import com.mou.election.dal.domian.EpermissionDO;
import com.mou.election.dal.domian.EpermissionDOExample;
import com.mou.election.dal.mapper.EpermissionDOMapper;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EPermissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
@Component
@Slf4j
public class EPermissionManager {

    @Autowired
    private EpermissionDOMapper permissionDOMapper;

    public void add(EPermissionDTO dto) {
        EpermissionDO epermissionDO = EpermissionConvert.dto2do(dto);
        epermissionDO.setGmtCreate(new Date());
        epermissionDO.setGmtModified(new Date());
        permissionDOMapper.insert(epermissionDO);
    }

    public void update(EPermissionDTO dto) {
        EpermissionDO epermissionDO = EpermissionConvert.dto2do(dto);
        epermissionDO.setGmtModified(new Date());
        permissionDOMapper.updateByPrimaryKeySelective(epermissionDO);
    }

    public void delete(Long id) {
        permissionDOMapper.deleteByPrimaryKey(id);
    }

    public EPermissionDTO get(Long id) {
        EpermissionDO epermissionDO = permissionDOMapper.selectByPrimaryKey(id);
        if (epermissionDO == null) {
            return null;
        }
        return EpermissionConvert.do2dto(epermissionDO);
    }

    public EPermissionDTO getByCode(String code) {
        EpermissionDOExample example = new EpermissionDOExample();
        example.createCriteria().andPermissionCodeEqualTo(code);
        List<EpermissionDO> epermissionDOS = permissionDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(epermissionDOS)) {
            return null;
        }
        if (epermissionDOS.size() > 1) {
            log.error("code = {} query DB has more then 1 permission", code);
            throw new EbizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        return EpermissionConvert.do2dto(epermissionDOS.get(0));
    }

    public List<EPermissionDTO> query(EPermissionDTO dto) {
        EpermissionDOExample example = new EpermissionDOExample();
        if (dto.getPermissionCode() != null) {
            example.createCriteria().andPermissionCodeLike("%" + dto.getPermissionCode() +"%");
        }
        if (dto.getPermissionName() != null) {
            example.createCriteria().andPermissionNameLike("%" + dto.getPermissionCode() +"%");
        }
        List<EpermissionDO> epermissionDOS = permissionDOMapper.selectByExample(example);
        return epermissionDOS.stream().map(EpermissionConvert::do2dto).collect(Collectors.toList());
    }

}














