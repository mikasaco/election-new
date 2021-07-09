package com.mou.election.service;

import com.mou.election.model.EdataDictionaryDTO;

import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public interface EdataDictionaryService {

    void add(EdataDictionaryDTO edataDictionaryDTO);


    List<EdataDictionaryDTO> query(EdataDictionaryDTO edataDictionaryDTO);

    void update(EdataDictionaryDTO dto);

    void delete(Long id);
}
