package com.mou.election.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mou.election.EdataDictionaryManager;
import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.dal.domian.EmessageDO;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EdataDictionaryDTO;
import com.mou.election.model.EmessageDTO;
import com.mou.election.service.EdataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Service
public class EdataDictionaryServiceImpl implements EdataDictionaryService {

    @Autowired
    private EdataDictionaryManager dataDictionaryManager;


    @Override
    public void add(EdataDictionaryDTO edataDictionaryDTO) {
        dataDictionaryManager.add(edataDictionaryDTO);
    }

    @Override
    public PageInfo<EdataDictionaryDTO> query(EPageResult page) {
        return dataDictionaryManager.query(page);
    }

    @Override
    public void update(EdataDictionaryDTO dto){
        dataDictionaryManager.update(dto);
    }

    @Override
    public void delete(Long id){
        dataDictionaryManager.delete(id);
    }
}
