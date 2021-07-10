package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EApplyDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EApplyService {


    void add(EApplyDTO applyDTO);

    void update(EApplyDTO applyDTO);

    void delete(Long id);

    EApplyDTO get(Long id);

    List<EApplyDTO> query(EApplyDTO queryDTO);

    PageInfo<EApplyDTO> pageQuery(HttpServletRequest httpServletRequest, EApplyDTO applyRequest2DTO);

    Integer count(EApplyDTO applyRequest2DTO);
}
