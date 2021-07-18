package com.mou.election.model;

import com.mou.election.enums.QuestionTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EQuestionDTO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private QuestionTypeEnum type;

    private Integer sort;

    private Long examId;

    private Integer score;

    private List<EAnswerDTO> answerDTOS;

    private Boolean userAnswerIsRight;

    private String analysis;
}
