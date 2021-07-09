package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EorganizationDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
public interface EorganizationService {

    void add(EorganizationDTO addDTO);

    List<EorganizationDTO> query(EorganizationDTO queryDTO);

    void update(EorganizationDTO euserDTO);

    void delete(Long id);

    PageInfo<EorganizationDTO> pageQuery(EorganizationDTO organizationRequest2DTO);
}
