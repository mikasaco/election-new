package com.mou.election;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.convert.EuserConvert;
import com.mou.election.dal.domian.EuserDO;
import com.mou.election.dal.domian.EuserDOExample;
import com.mou.election.dal.mapper.EuserDOMapper;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EUserDTO;
import com.mou.election.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Slf4j
@Component
public class EUserManager {

    @Autowired
    private EuserDOMapper euserDOMapper;

    private Map<String, EUserDTO> tokenUserMap = new HashMap<>();


    public EUserDTO getUserDTO(HttpServletRequest request) {
        String token = request.getHeader("token");
        return tokenUserMap.get(token);
    }

    public void putUserDTO(String token, EUserDTO euserDTO) {
        tokenUserMap.put(token, euserDTO);
    }

    public EUserDTO getUserById(Long id) {
        return EuserConvert.do2dto(euserDOMapper.selectByPrimaryKey(id));
    }

    public void add(EUserDTO addDTO) {
        EuserDO euserDO = EuserConvert.dto2do(addDTO);
        euserDO.setGmtCreate(new Date());
        euserDO.setGmtModified(new Date());
        if(StringUtil.isEmpty(euserDO.getPassword())) {
            euserDO.setPassword(euserDO.getPhone());
        }
        euserDOMapper.insert(euserDO);
    }

    public void update(EUserDTO euserDTO) {
        EuserDO euserDO = EuserConvert.dto2do(euserDTO);
        euserDO.setGmtModified(new Date());
        euserDOMapper.updateByPrimaryKeySelective(euserDO);
    }

    public void delete(Long id) {
        euserDOMapper.deleteByPrimaryKey(id);
    }

    public EUserDTO getUserByPhone(String phone) {
        EuserDOExample example = new EuserDOExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<EuserDO> euserDOS = euserDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(euserDOS)) {
            return null;
        }
        if (euserDOS.size() > 1) {
            log.error("phone = {} query DB has more then 1 user", phone);
            throw new EbizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        return EuserConvert.do2dto(euserDOS.get(0));
    }

    public EUserDTO getUserByOpenId(String openId) {
        EuserDOExample example = new EuserDOExample();
        example.createCriteria().andPhoneEqualTo(openId);
        List<EuserDO> euserDOS = euserDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(euserDOS)) {
            return null;
        }
        if (euserDOS.size() > 1) {
            log.error("openId = {} query DB has more then 1 user", openId);
            throw new EbizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        return EuserConvert.do2dto(euserDOS.get(0));
    }

    public List<EUserDTO> query(EUserDTO queryDTO) {
        EuserDOExample example = new EuserDOExample();
        EuserDOExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(queryDTO.getUserName())) {
            criteria.andUserNameEqualTo(queryDTO.getUserName());
        }
        if (null != queryDTO.getOrganizationId()) {
            criteria.andOrganizationIdEqualTo(queryDTO.getOrganizationId());
        }
        if (StringUtil.isNotEmpty(queryDTO.getPost())) {
            criteria.andPostEqualTo(queryDTO.getPost());
        }
        if (StringUtil.isNotEmpty(queryDTO.getStatus())) {
            criteria.andStatusEqualTo(queryDTO.getStatus());
        }
        List<EuserDO> euserDOS = euserDOMapper.selectByExample(example);
        return euserDOS.stream().map(EuserConvert::do2dto).collect(Collectors.toList());
    }

    public EUserDTO getUserByToken(String token){
        Long userId = TokenUtils.verify(token);
        EuserDO euserDO = euserDOMapper.selectByPrimaryKey(userId);
        if(euserDO == null){
            return null;
        }
        return EuserConvert.do2dto(euserDO);
    }

}
