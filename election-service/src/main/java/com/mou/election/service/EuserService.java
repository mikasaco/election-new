package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EUserDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public interface EuserService {

    EUserDTO login(EUserDTO userDTO);

    void add(EUserDTO userDTO);

    void update(EUserDTO euserDTO);

    void delete(Long id);

    EUserDTO getUserById(Long id);

    void bindOpenId(String jsCode, EUserDTO euserDTO);

    EUserDTO getUserByPhone(String phone);

    EUserDTO getUserByOpenId(String openId);

    List<EUserDTO> query(EUserDTO queryDTO);

    PageInfo<EUserDTO> pageQuery(EUserDTO queryDTO);
}
