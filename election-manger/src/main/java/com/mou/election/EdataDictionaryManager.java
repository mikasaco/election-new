package com.mou.election;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mou.election.convert.EdataDictionaryConvert;
import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.dal.domian.EdataDictionaryDOExample;
import com.mou.election.dal.mapper.EdataDictionaryDOMapper;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EdataDictionaryDTO;
import com.mou.election.model.ElectionConstants;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Component
public class EdataDictionaryManager {
    @Autowired
    private EdataDictionaryDOMapper edataDictionaryDOMapper;

    public void add(EdataDictionaryDTO edataDictionaryDTO) {
        EdataDictionaryDO dictionaryDO = EdataDictionaryConvert.dto2do(edataDictionaryDTO);
        dictionaryDO.setGmtCreate(new Date());
        dictionaryDO.setGmtModified(new Date());
        edataDictionaryDOMapper.insert(dictionaryDO);
    }

    public void add(EdataDictionaryDO edataDictionaryDO) {
        edataDictionaryDO.setGmtCreate(new Date());
        edataDictionaryDO.setGmtModified(new Date());
        edataDictionaryDOMapper.insert(edataDictionaryDO);
    }

    public PageInfo<EdataDictionaryDTO> query(EPageResult pageResult) {
        if(pageResult.getCurrentPageNo() == null) {
            pageResult.setCurrentPageNo(ElectionConstants.PAGE_DEFAULT_INDEX);
        }
        if(pageResult.getPageSize() == null) {
            pageResult.setPageSize(ElectionConstants.PAGE_DEFAULT_SIZE);
        }
        Page<EdataDictionaryDO> pages = PageHelper.startPage(pageResult.getCurrentPageNo(),pageResult.getPageSize(),"gmt_create desc")
                .doSelectPage(()->edataDictionaryDOMapper.selectByExample(null));

        PageInfo<EdataDictionaryDTO> pageInfo = new PageInfo();
        pageInfo.setTotal(pages.getTotal());
        if(CollectionUtils.isEmpty(pages.getResult())){
            pageInfo.setList(null);
        }else{
            pageInfo.setList(pages.getResult().stream()
                    .map(EdataDictionaryConvert::do2dto).collect(Collectors.toList()));
        }
        return pageInfo;
    }

    public void update(EdataDictionaryDTO dto){
        EdataDictionaryDO dictionaryDO = EdataDictionaryConvert.dto2do(dto);
        dictionaryDO.setGmtModified(new Date());
        edataDictionaryDOMapper.updateByPrimaryKeySelective(dictionaryDO);
    }

    public void updateByPrimaryKey(EdataDictionaryDO edataDictionaryDO) {
        edataDictionaryDO.setGmtModified(new Date());
        edataDictionaryDOMapper.updateByPrimaryKey(edataDictionaryDO);
    }

    public void delete(Long id){
        edataDictionaryDOMapper.deleteByPrimaryKey(id);
    }

    public List<EdataDictionaryDO> queryByDataDict(EdataDictionaryDO edataDictionaryDO) {
        EdataDictionaryDOExample edataDictionaryDOExample = new EdataDictionaryDOExample();
        EdataDictionaryDOExample.Criteria criteria = edataDictionaryDOExample.createCriteria();
        if(!StringUtils.isNullOrEmpty(edataDictionaryDO.getDataType())) {
            criteria.andDataTypeEqualTo(edataDictionaryDO.getDataType());
        }
        if(!StringUtils.isNullOrEmpty(edataDictionaryDO.getDataCode())){
            criteria.andDataCodeEqualTo(edataDictionaryDO.getDataCode());
        }
        if(!StringUtils.isNullOrEmpty(edataDictionaryDO.getDataDesc())){
            criteria.andDataDescEqualTo(edataDictionaryDO.getDataDesc());
        }
        return edataDictionaryDOMapper.selectByExample(edataDictionaryDOExample);
    }

}
