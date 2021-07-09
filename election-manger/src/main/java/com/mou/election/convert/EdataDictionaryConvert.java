package com.mou.election.convert;

import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.model.EdataDictionaryDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class EdataDictionaryConvert {

    public static EdataDictionaryDO dto2do(EdataDictionaryDTO dto) {
        EdataDictionaryDO dictionaryDO = new EdataDictionaryDO();
        BeanUtils.copyProperties(dto, dictionaryDO);
        return dictionaryDO;
    }

    public static EdataDictionaryDTO do2dto(EdataDictionaryDO edataDictionaryDO) {
        EdataDictionaryDTO dto = new EdataDictionaryDTO();
        BeanUtils.copyProperties(edataDictionaryDO, dto);
        return dto;
    }

    public static List<EdataDictionaryDTO> doList2Dto(List<EdataDictionaryDO> dataDictionaryDOS) {
        return dataDictionaryDOS.stream().map(EdataDictionaryConvert::do2dto).collect(Collectors.toList());
    }
}
