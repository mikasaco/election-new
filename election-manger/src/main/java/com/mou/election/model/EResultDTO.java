package com.mou.election.model;

import lombok.Data;

import java.util.Date;

@Data
public class EResultDTO {

    private Long id;


    private Date gmtCreate;


    private Date gmtModified;


    private Long examId;


    private String score;


    private Long userId;


    private Integer pass;

    private EExamDTO examDTO;


}
