package com.mou.election.convert;

import com.mou.election.dal.domian.EroleDO;
import com.mou.election.model.EroleDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
public class EroleConvert {

    public static EroleDTO do2dto(EroleDO eroleDO){
        EroleDTO dto = new EroleDTO();
        BeanUtils.copyProperties(eroleDO,dto);
        return dto;
    }

    public static EroleDO dto2do(EroleDTO dto){
        EroleDO eroleDO = new EroleDO();
        BeanUtils.copyProperties(dto,eroleDO);
        return eroleDO;
    }
}
