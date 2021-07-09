package com.mou.election.convert;

import com.mou.election.dal.domian.EuserDO;
import com.mou.election.model.EUserDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class EuserConvert {

    public static EUserDTO do2dto(EuserDO euserDO) {
        EUserDTO dto = new EUserDTO();
        BeanUtils.copyProperties(euserDO, dto);
        return dto;
    }

    public static EuserDO dto2do(EUserDTO euserDTO) {
        EuserDO euserDO = new EuserDO();
        BeanUtils.copyProperties(euserDTO, euserDO);
        return euserDO;
    }

}
