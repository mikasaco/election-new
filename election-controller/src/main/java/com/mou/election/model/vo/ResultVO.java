package com.mou.election.model.vo;

import com.mou.election.model.EExamDTO;
import com.mou.election.model.request.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class ResultVO extends BaseRequest {

    private Long id;


    private Date gmtCreate;


    private Date gmtModified;


    private Long examId;


    private String score;


    private Long userId;


    private Integer pass;

    private EExamDTO examDTO;
}
