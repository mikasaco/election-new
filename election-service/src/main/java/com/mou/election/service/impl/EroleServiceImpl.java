package com.mou.election.service.impl;

import com.mou.election.EPermissionManager;
import com.mou.election.EroleManager;
import com.mou.election.model.EroleDTO;
import com.mou.election.service.EroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Service
public class EroleServiceImpl implements EroleService {

    @Autowired
    private EroleManager eroleManager;

    @Autowired
    private EPermissionManager epermissionManager;

    @Override
    public void add(EroleDTO addDTO) {
        eroleManager.add(addDTO);
    }

    @Override
    public List<EroleDTO> query(EroleDTO queryDTO) {
        return eroleManager.query(queryDTO);
    }

    @Override
    public void update(EroleDTO euserDTO) {
        eroleManager.update(euserDTO);
    }

    @Override
    public void delete(Long id) {
        eroleManager.delete(id);
    }
}
