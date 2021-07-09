package com.mou.election.service.impl;

import com.mou.election.EApplyManager;
import com.mou.election.EUserManager;
import com.mou.election.model.EApplyDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.service.EApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EApplyServiceImpl implements EApplyService {

    @Autowired
    private EApplyManager applyManager;

    @Autowired
    private EUserManager userManager;


    @Override
    public void add(EApplyDTO applyDTO) {
        applyManager.add(applyDTO);
    }

    @Override
    public void update(EApplyDTO applyDTO) {
        applyManager.update(applyDTO);
    }

    @Override
    public void delete(Long id) {
        applyManager.delete(id);
    }

    @Override
    public EApplyDTO get(Long id) {
        EApplyDTO applyDTO = applyManager.get(id);
        EUserDTO userDTO = userManager.getUserById(applyDTO.getUserId());
        applyDTO.setUserDTO(userDTO);
        return applyDTO;
    }

    @Override
    public List<EApplyDTO> query(EApplyDTO queryDTO) {
        applyManager.query(queryDTO);
        return null;
    }
}
