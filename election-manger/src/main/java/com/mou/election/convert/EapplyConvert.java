package com.mou.election.convert;

import com.mou.election.dal.domian.EApplyDO;
import com.mou.election.enums.ApplyDelayEnum;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.model.EApplyDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
public class EapplyConvert {

    public static EApplyDO dto2do(EApplyDTO dto) {
        EApplyDO applyDO = new EApplyDO();
        BeanUtils.copyProperties(dto, applyDO);
        applyDO.setDelay(dto.getDelay().getCode());
        applyDO.setStatus(dto.getStatus().getCode());
        return applyDO;
    }

    public static EApplyDTO do2dto(EApplyDO applyDO) {
        EApplyDTO dto = new EApplyDTO();
        BeanUtils.copyProperties(applyDO, dto);
        dto.setDelay(ApplyDelayEnum.getApplyDelayByCode(applyDO.getDelay()));
        dto.setStatus(ApplyStatusEnum.getApplyStatusByCode(applyDO.getStatus()));
        return dto;
    }
}
