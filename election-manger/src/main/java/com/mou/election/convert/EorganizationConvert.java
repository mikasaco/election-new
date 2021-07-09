package com.mou.election.convert;

import com.mou.election.dal.domian.EorganizationDO;
import com.mou.election.model.EorganizationDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
public class EorganizationConvert {

    public static EorganizationDO dto2do(EorganizationDTO dto){
        EorganizationDO eorganizationDO = new EorganizationDO();
        BeanUtils.copyProperties(dto,eorganizationDO);
        return eorganizationDO;
    }

    public static EorganizationDTO do2dto(EorganizationDO eorganizationDO){
        EorganizationDTO eorganizationDTO = new EorganizationDTO();
        BeanUtils.copyProperties(eorganizationDO,eorganizationDTO);
        return eorganizationDTO;
    }
}
