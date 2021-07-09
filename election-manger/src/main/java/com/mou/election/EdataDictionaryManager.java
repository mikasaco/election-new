package com.mou.election;

import com.mou.election.convert.EdataDictionaryConvert;
import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.dal.domian.EdataDictionaryDOExample;
import com.mou.election.dal.mapper.EdataDictionaryDOMapper;
import com.mou.election.model.EdataDictionaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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

    public List<EdataDictionaryDTO> query(EdataDictionaryDTO edataDictionaryDTO) {
        EdataDictionaryDOExample example = new EdataDictionaryDOExample();
        example.createCriteria().andDataCodeEqualTo(edataDictionaryDTO.getDataCode());

        List<EdataDictionaryDO> edataDictionaryDOS = edataDictionaryDOMapper.selectByExample(example);
        return EdataDictionaryConvert.doList2Dto(edataDictionaryDOS);
    }

    public void update(EdataDictionaryDTO dto){
        EdataDictionaryDO dictionaryDO = EdataDictionaryConvert.dto2do(dto);
        dictionaryDO.setGmtModified(new Date());
        edataDictionaryDOMapper.updateByPrimaryKeySelective(dictionaryDO);
    }

    public void delete(Long id){
        edataDictionaryDOMapper.deleteByPrimaryKey(id);
    }

}
