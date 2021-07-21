package com.mou.election.convert;

import com.mou.election.dal.domian.EAnswerDO;
import com.mou.election.dal.domian.EExamDO;
import com.mou.election.dal.domian.EQuestionDO;
import com.mou.election.dal.domian.EResultDO;
import com.mou.election.model.EAnswerDTO;
import com.mou.election.model.EExamDTO;
import com.mou.election.model.EQuestionDTO;
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

    public static EQuestionDO questionDTO2DO(EQuestionDTO questionDTO){
        EQuestionDO questionDO = new EQuestionDO();
        BeanUtils.copyProperties(questionDTO,questionDO);
        questionDO.setType(questionDTO.getType().getCode());
        return questionDO;
    }

    public static EAnswerDO answerDTO2DO(EAnswerDTO answerDTO){
        EAnswerDO answerDO = new EAnswerDO();
        BeanUtils.copyProperties(answerDTO,answerDO);
        answerDO.setRightAnswer(answerDTO.getRight());
        return answerDO;
    }

    public static EResultDTO resultDO2DTO(EResultDO resultDO){
        EResultDTO resultDTO = new EResultDTO();
        BeanUtils.copyProperties(resultDO,resultDTO);
        return resultDTO;
    }




}
