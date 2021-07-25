package com.mou.election.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExamAnswerVO implements Serializable {


    private Long examId;

    private List<String> questionAnswerRequests;
}
