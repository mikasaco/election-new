package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EPageResult;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.model.EroleDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
public interface EPermissionService {

    void add(EPermissionDTO dto);

    void update(EPermissionDTO dto);

    void delete(Long id);

    EPermissionDTO get(Long id);

    List<EPermissionDTO> query(EPermissionDTO dto);


    PageInfo<EPermissionDTO> pageQuery(EPermissionDTO dto);
}
