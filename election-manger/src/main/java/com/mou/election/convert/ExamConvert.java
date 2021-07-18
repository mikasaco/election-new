package com.mou.election.convert;

import com.mou.election.dal.domian.EExamDO;
import com.mou.election.dal.domian.EResultDO;
import com.mou.election.model.EExamDTO;
import com.mou.election.model.EResultDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class ExamConvert {

    public static EExamDO dto2do(EExamDTO examDTO) {
        EExamDO examDO = new EExamDO();
        if (examDTO.getGmtCreate() == null) {
            examDO.setGmtCreate(new Date());
        }
        if (examDTO.getGmtModified() == null) {
            examDO.setGmtModified(new Date());
        }
        BeanUtils.copyProperties(examDTO,examDO);
        return examDO;
    }

    public static EExamDTO do2dto(EExamDO examDO){
        EExamDTO examDTO = new EExamDTO();
        BeanUtils.copyProperties(examDO,examDTO);
        return examDTO;

    }

    public static EResultDTO resultDO2DTO(EResultDO resultDO){
        EResultDTO resultDTO = new EResultDTO();
        BeanUtils.copyProperties(resultDO,resultDTO);
        return resultDTO;
    }




}
