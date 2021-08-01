package com.mou.election.model.vo;

import com.mou.election.model.request.BaseRequest;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamVO extends BaseRequest {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String score;

    private String info;

    private String remark;

    private String feature;

    private Integer rightNum;

    private boolean isDelete;

    private List<QuestionVO> ksQuestionRequests;

    private List<QuestionVO> ksQuestionResponses;


}
