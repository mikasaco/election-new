package com.mou.election.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EExamDTO extends BaseDTO{

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String passScore;

    private String score;

    private String remark;

    private String feature;

    private Integer rightNum;

    private List<EQuestionDTO> questionDTOS;
}
