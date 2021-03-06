package com.mou.election;

import com.alibaba.excel.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.convert.EapplyConvert;
import com.mou.election.convert.EpermissionConvert;
import com.mou.election.dal.domian.*;
import com.mou.election.dal.mapper.EApplyDOMapper;
import com.mou.election.dal.mapper.EorganizationDOMapper;
import com.mou.election.dal.mapper.EuserDOMapper;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
@Component
public class EApplyManager {

    @Autowired
    private EApplyDOMapper applyDOMapper;
    @Autowired
    private EuserDOMapper userDOMapper;
    @Autowired
    private EorganizationDOMapper organizationDOMapper;

    @Autowired
    private EUserManager userManager;

    public void add(EApplyDTO applyDTO) {
        EApplyDO applyDO = EapplyConvert.dto2do(applyDTO);
        applyDO.setGmtCreate(new Date());
        applyDO.setGmtModified(new Date());
        applyDO.setStatus("PROCESSING");
        applyDOMapper.insert(applyDO);

    }

    public void update(EApplyDTO applyDTO) {
        EApplyDO applyDO = EapplyConvert.dto2do(applyDTO);
        applyDO.setGmtModified(new Date());
        applyDOMapper.updateByPrimaryKeySelective(applyDO);
        if("END".equals(applyDTO.getStatus())) {
            EorganizationDO eorganizationDO = userDOMapper.getOrgByUserId(applyDTO.getUserId());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(applyDTO.getApplyElectionDate());calendar.add(Calendar.YEAR,3);
            eorganizationDO.setChangeTermTime(calendar.getTime());
            organizationDOMapper.updateByPrimaryKey(eorganizationDO);
        }
    }

    public void delete(Long id) {
        applyDOMapper.deleteByPrimaryKey(id);
    }

    public EApplyDTO get(Long id) {
        EApplyDO applyDO = applyDOMapper.selectByPrimaryKey(id);
        return EapplyConvert.do2dto(applyDO);
    }

    public List<EApplyDTO> query(EApplyDTO queryDTO) {
        EApplyDOExample example = buildQueryExample(queryDTO);
        List<EApplyDO> eApplyDOS = applyDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(eApplyDOS)) {
            return null;
        }
        return eApplyDOS.stream().map(EapplyConvert::do2dto).collect(Collectors.toList());
    }

    private EApplyDOExample buildQueryExample(EApplyDTO queryDTO) {


        EApplyDOExample example = new EApplyDOExample();
        EApplyDOExample.Criteria criteria = example.createCriteria();
        if (queryDTO.getStatus() != null) {
            criteria.andStatusEqualTo(queryDTO.getStatus().getCode());
        }
        if (queryDTO.getDelay() != null) {
            criteria.andDelayEqualTo(queryDTO.getDelay().getCode());
        }
        if (queryDTO.getOrganizationId() != null) {
            EUserDTO userDTO = new EUserDTO();
            userDTO.setOrganizationId(queryDTO.getOrganizationId());
            List<EUserDTO> userDTOS = userManager.query(userDTO);
            List<Long> userIds = userDTOS.stream().map(EUserDTO::getId).collect(Collectors.toList());
            criteria.andUserIdIn(userIds);
        }
        if(queryDTO.getUserId() !=null){
            criteria.andUserIdEqualTo(queryDTO.getUserId());
        }
        if(StringUtil.isNotEmpty(queryDTO.getKeyWord())){
            EUserDTO userDTO = new EUserDTO();
            userDTO.setUserName(queryDTO.getKeyWord());
            List<EUserDTO> query = userManager.query(userDTO);
            if(!query.isEmpty()){
                criteria.andUserIdIn(query.stream().map(EUserDTO::getId).collect(Collectors.toList()));
            }else {
                criteria.andUserIdEqualTo(0L);
            }
        }

        return example;
    }

    public PageInfo<EApplyDTO> pageQuery(EApplyDTO queryDTO) {
        EApplyDOExample example = buildQueryExample(queryDTO);
        Page<EApplyDO> page = PageHelper.startPage(queryDTO.getCurrentPageNo(), queryDTO.getPageSize())
                .doSelectPage(() -> applyDOMapper.selectByExample(example));
        List<EApplyDTO> collect = page.getResult().stream().map(EapplyConvert::do2dto).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(page.getTotal());
        pageInfo.setList(collect);
        return pageInfo;
    }

    public int count(EApplyDTO applyDTO) {
        EApplyDOExample example = buildQueryExample(applyDTO);
        return applyDOMapper.countByExample(example);
    }
}
