package com.mou.election.model;

import lombok.Data;

import java.util.Date;

@Data
public class EAnswerDTO {


    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String choice;

    private String arrange;

    private Long questionId;

    private Integer right;
}
