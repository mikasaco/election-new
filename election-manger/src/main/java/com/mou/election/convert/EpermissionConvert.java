package com.mou.election.convert;

import com.mou.election.dal.domian.EpermissionDO;
import com.mou.election.model.EPermissionDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
public class EpermissionConvert {

    public static EpermissionDO dto2do(EPermissionDTO dto){
        EpermissionDO epermissionDO = new EpermissionDO();
        BeanUtils.copyProperties(dto,epermissionDO);
        return epermissionDO;
    }

    public static EPermissionDTO do2dto(EpermissionDO epermissionDO){
        EPermissionDTO epermissionDTO = new EPermissionDTO();
        BeanUtils.copyProperties(epermissionDO,epermissionDTO);
        return epermissionDTO;
    }
}
