package com.mou.election.service;

import com.mou.election.model.EApplyDTO;

import java.util.List;

public interface EApplyService {


    void add(EApplyDTO applyDTO);

    void update(EApplyDTO applyDTO);

    void delete(Long id);

    EApplyDTO get(Long id);

    List<EApplyDTO> query(EApplyDTO queryDTO);
}
