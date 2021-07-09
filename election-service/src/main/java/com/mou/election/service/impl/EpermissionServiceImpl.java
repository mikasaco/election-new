package com.mou.election.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mou.election.EPermissionManager;
import com.mou.election.model.EPermissionDTO;
import com.mou.election.service.EPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
@Service
public class EpermissionServiceImpl implements EPermissionService {

    @Autowired
    private EPermissionManager permissionManager;

    @Override
    public void add(EPermissionDTO dto) {
        permissionManager.add(dto);
    }

    @Override
    public void update(EPermissionDTO dto) {
        permissionManager.update(dto);
    }

    @Override
    public void delete(Long id) {
        permissionManager.delete(id);
    }

    @Override
    public EPermissionDTO get(Long id) {
        return permissionManager.get(id);
    }

    @Override
    public List<EPermissionDTO> query(EPermissionDTO dto) {
        return permissionManager.query(dto);
    }

    @Override
    public PageInfo<EPermissionDTO> pageQuery(EPermissionDTO permissionDTO) {
        PageHelper.startPage(permissionDTO.getCurrentPageNo(), permissionDTO.getPageSize());
        List<EPermissionDTO> permissionDTOList = permissionManager.query(permissionDTO);
        return new PageInfo<>(permissionDTOList);
    }

}
