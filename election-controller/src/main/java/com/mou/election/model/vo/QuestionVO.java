package com.mou.election.model.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionVO {

    private Long id;

    private Long examId;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String score;

    private Integer type;

    private Integer sort;

    private List<AnswerVO> ksOptionsRequests;

    private String analysis;

    private Boolean userAnswerIsRight;

    private List<String> answer;


}
