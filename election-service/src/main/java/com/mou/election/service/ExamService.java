package com.mou.election.service;

import com.github.pagehelper.PageInfo;
import com.mou.election.model.EExamAnswerDTO;
import com.mou.election.model.EExamDTO;
import com.mou.election.model.EResultDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExamService {

    void add(EExamDTO examDTO);

    EExamDTO get(Long id);

    PageInfo<EExamDTO> pageQuery(EExamDTO examDTO);

    Long userAnswer(HttpServletRequest httpServletRequest, EExamAnswerDTO examAnswerDTO);

    EExamDTO getUserAnswer(HttpServletRequest httpServletRequest, EResultDTO resultDTO);

    Integer count(EExamAnswerDTO examAnswerDTO);

    List<EResultDTO> queryExamResult(EResultDTO resultDTO);
}
