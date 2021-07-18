package com.mou.election.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AnswerVO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String choice;

    private String arrange;

    private Integer right;

    private Long questionId;

}
