package com.mou.election.convert;

import com.mou.election.dal.domian.EmessageDO;
import com.mou.election.dal.domian.EuserMessageDO;
import com.mou.election.dal.domian.EuserMessageJoinDO;
import com.mou.election.dal.domian.EuserMessageJoinUserDO;
import com.mou.election.model.EmessageDTO;
import com.mou.election.model.EuserMessageDTO;
import com.mou.election.model.EuserMessageJoinDTO;
import com.mou.election.model.EuserMessageJoinUserDTO;
import org.springframework.beans.BeanUtils;

public class EmessageConvert {
    public static EmessageDTO do2dto(EmessageDO emessageDO) {
        EmessageDTO dto = new EmessageDTO();
        BeanUtils.copyProperties(emessageDO, dto);
        return dto;
    }

    public static EmessageDO dto2do(EmessageDTO emessageDTO) {
        EmessageDO emessageDO = new EmessageDO();
        BeanUtils.copyProperties(emessageDTO, emessageDO);
        return emessageDO;
    }

    public static EuserMessageDTO do2dto(EuserMessageDO euserMessageDO) {
        EuserMessageDTO dto = new EuserMessageDTO();
        BeanUtils.copyProperties(euserMessageDO, dto);
        return dto;
    }

    public static EuserMessageDO dto2do(EuserMessageDTO dto) {
        EuserMessageDO userMessageDo = new EuserMessageDO();
        BeanUtils.copyProperties(dto, userMessageDo);
        return userMessageDo;
    }

    public static EuserMessageJoinDTO do2dto(EuserMessageJoinDO euserMessageJoinDO) {
        EuserMessageJoinDTO dto = new EuserMessageJoinDTO();
        BeanUtils.copyProperties(euserMessageJoinDO, dto);
        return dto;
    }

    public static EuserMessageJoinDO dto2do(EuserMessageJoinDTO dto) {
        EuserMessageJoinDO euserMessageJoinDO = new EuserMessageJoinDO();
        BeanUtils.copyProperties(dto, euserMessageJoinDO);
        return euserMessageJoinDO;
    }

    public static EuserMessageJoinUserDTO do2dto(EuserMessageJoinUserDO euserMessageJoinUserDO) {
        EuserMessageJoinUserDTO dto = new EuserMessageJoinUserDTO();
        BeanUtils.copyProperties(euserMessageJoinUserDO, dto);
        return dto;
    }

    public static EuserMessageJoinUserDO dto2do(EuserMessageJoinUserDTO dto) {
        EuserMessageJoinUserDO euserMessageJoinDO = new EuserMessageJoinUserDO();
        BeanUtils.copyProperties(dto, euserMessageJoinDO);
        return euserMessageJoinDO;
    }

}
