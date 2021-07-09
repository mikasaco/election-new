package com.mou.election.service.impl;

import com.mou.election.EorganizationManager;
import com.mou.election.model.EorganizationDTO;
import com.mou.election.service.EorganizationService;
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
public class EorganizationServiceImpl implements EorganizationService {

    @Autowired
    private EorganizationManager eorganizationManager;

    @Override
    public void add(EorganizationDTO addDTO) {
        eorganizationManager.add(addDTO);
    }

    @Override
    public List<EorganizationDTO> query(EorganizationDTO queryDTO) {
        return eorganizationManager.query(queryDTO);
    }

    @Override
    public void update(EorganizationDTO eorganizationDTO) {
        eorganizationManager.update(eorganizationDTO);
    }

    @Override
    public void delete(Long id) {
        eorganizationManager.delete(id);
    }
}
