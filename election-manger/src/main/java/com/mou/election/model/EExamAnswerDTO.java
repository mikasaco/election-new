package com.mou.election.model;

import lombok.Data;

import java.util.List;

@Data
public class EExamAnswerDTO extends BaseDTO {

    private Long examId;

    private Long questionId;

    private String result;

    private Integer right;

    private Long userId;

    private List<String> questionAnswerRequests;
}
