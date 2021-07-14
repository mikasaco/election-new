package com.mou.election.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mou.election.dal.domian.EmessageDO;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EdataDictionaryDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public interface EdataDictionaryService {

    void add(EdataDictionaryDTO edataDictionaryDTO);

    PageInfo<EdataDictionaryDTO> query(EPageResult page);

    void update(EdataDictionaryDTO dto);

    void delete(Long id);
}
