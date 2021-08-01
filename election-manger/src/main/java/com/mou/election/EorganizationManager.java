package com.mou.election;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.convert.EorganizationConvert;
import com.mou.election.convert.EpermissionConvert;
import com.mou.election.dal.domian.EorganizationDO;
import com.mou.election.dal.domian.EorganizationDOExample;
import com.mou.election.dal.domian.EpermissionDO;
import com.mou.election.dal.domian.EpermissionDOExample;
import com.mou.election.dal.mapper.EorganizationDOMapper;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EorganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Component
public class EorganizationManager {


    @Autowired
    private EorganizationDOMapper eorganizationDOMapper;


    public void add(EorganizationDTO addDTO) {
        EorganizationDO eorganizationDO = EorganizationConvert.dto2do(addDTO);
        eorganizationDO.setGmtCreate(new Date());
        eorganizationDO.setGmtModified(new Date());
        eorganizationDOMapper.insert(eorganizationDO);
    }

    public void update(EorganizationDTO updateDTO) {
        EorganizationDO eorganizationDO = EorganizationConvert.dto2do(updateDTO);
        eorganizationDO.setGmtModified(new Date());
        eorganizationDOMapper.updateByPrimaryKeySelective(eorganizationDO);
    }

    public void delete(Long id) {
        eorganizationDOMapper.deleteByPrimaryKey(id);
    }

    public EorganizationDTO getById(Long id) {
        EorganizationDO eorganizationDO = eorganizationDOMapper.selectByPrimaryKey(id);
        if (eorganizationDO == null){
            return null;
        }
        return EorganizationConvert.do2dto(eorganizationDO);
    }

    public List<EorganizationDTO> query(EorganizationDTO queryDTO) {
        EorganizationDOExample example = new EorganizationDOExample();
        EorganizationDOExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(queryDTO.getOrganizationName())) {
            criteria.andOrganizationNameLike(queryDTO.getOrganizationName());
        }
        List<EorganizationDO> eorganizationDOS = eorganizationDOMapper.selectByExample(example);
        return eorganizationDOS.stream().map(EorganizationConvert::do2dto).collect(Collectors.toList());
    }

    public PageInfo<EorganizationDTO> pageQuery(EorganizationDTO eorganizationDTO) {
        EorganizationDOExample example = buildExample(eorganizationDTO);
        Page<EorganizationDO> page = PageHelper.startPage(eorganizationDTO.getCurrentPageNo(),eorganizationDTO.getPageSize())
                .doSelectPage(()->eorganizationDOMapper.selectByExample(example));
        List<EorganizationDTO> collect = page.getResult().stream().map(EorganizationConvert::do2dto).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(page.getTotal());
        pageInfo.setList(collect);
        return pageInfo;

    }

    private EorganizationDOExample buildExample(EorganizationDTO eorganizationDTO) {
        EorganizationDOExample example = new EorganizationDOExample();
        EorganizationDOExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(eorganizationDTO.getOrganizationName())) {
            criteria.andOrganizationNameLike(eorganizationDTO.getOrganizationName());
        }
        if(StringUtil.isNotEmpty(eorganizationDTO.getKeyWord())){
            criteria.andOrganizationNameLike("%"+eorganizationDTO.getKeyWord()+"%");
        }
        return example;
    }

    public Integer count(EorganizationDTO eorganizationDTO){
        EorganizationDOExample eorganizationDOExample = buildExample(eorganizationDTO);
        return eorganizationDOMapper.countByExample(eorganizationDOExample);
    }
}
