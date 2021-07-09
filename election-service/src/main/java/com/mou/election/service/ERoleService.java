package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EroleDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
public interface ERoleService {

    void add(EroleDTO addDTO);

    EroleDTO get(Long id);

    List<EroleDTO> query(EroleDTO queryDTO);

    void update(EroleDTO euserDTO);

    void delete(Long id);

    PageInfo<EroleDTO> pageQuery(EroleDTO eroleDTO);
}
