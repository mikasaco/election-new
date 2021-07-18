package com.mou.election.controller;

import com.github.pagehelper.PageInfo;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.model.*;
import com.mou.election.model.vo.ExamAnswerVO;
import com.mou.election.model.vo.ExamCountVO;
import com.mou.election.model.vo.ExamVO;
import com.mou.election.service.ExamService;
import com.mou.election.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam/")
@Slf4j
public class ExamController {

    @Autowired
    private ExamService examService;

    @RequestMapping("add")
    public EResult<Boolean> add(@RequestBody ExamVO examVO) {
        EExamDTO examDTO = RequestConvert.examRequest2DTO(examVO);
        examService.add(examDTO);
        return EResult.newSuccessInstance(true);
    }

    @RequestMapping("pageQuery")
    public EResult<ExamVO> pageQuery(@RequestBody ExamVO examVO) {
        EExamDTO examDTO = RequestConvert.examRequest2DTO(examVO);
        PageInfo<EExamDTO> pageInfo = examService.pageQuery(examDTO);
        List<ExamVO> examVOS = pageInfo.getList().stream().map(ResponseConvert::examDTO2VO).collect(Collectors.toList());
        return EPageResult.newSuccessInstance(pageInfo.getTotal(), examVOS);
    }

    @RequestMapping("userAnswer")
    public EResult<Long> userAnswer(HttpServletRequest httpServletRequest, @RequestBody ExamAnswerVO examAnswerVO) {
        EExamAnswerDTO examAnswerDTO = RequestConvert.examAnswerVO2DTO(examAnswerVO);
        Long resultId = examService.userAnswer(httpServletRequest, examAnswerDTO);
        return EResult.newSuccessInstance(resultId);
    }

    @RequestMapping("getExamResult/{id}")
    public EResult<ExamVO> getExamResult(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        EResultDTO resultDTO = new EResultDTO();
        resultDTO.setId(id);

        EExamDTO examDTO = examService.getUserAnswer(httpServletRequest, resultDTO);
        ExamVO examVO = ResponseConvert.examDTO2VO(examDTO);
        return EResult.newSuccessInstance(examVO);
    }


    @RequestMapping("get/{id}")
    public EResult<ExamVO> get(@PathVariable Long id) {
        EExamDTO examDTO = examService.get(id);
        ExamVO examVO = ResponseConvert.examDTO2VO(examDTO);
        return EResult.newSuccessInstance(examVO);
    }

    @RequestMapping("count")
    public EResult<ExamCountVO> count(HttpServletRequest httpServletRequest) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));


        ExamCountVO examCountVO = new ExamCountVO();
        EExamAnswerDTO examAnswerDTO = new EExamAnswerDTO();
        examAnswerDTO.setUserId(userId);
        Integer count = examService.count(examAnswerDTO);
        if (count == 0) {
            examCountVO.setTotalExamQuestionNumber(0);
            examCountVO.setMaxScore(0);
            examCountVO.setRate(0);
            return EResult.newSuccessInstance(examCountVO);
        }
        examCountVO.setTotalExamQuestionNumber(count);

        EExamAnswerDTO right = new EExamAnswerDTO();
        right.setUserId(userId);
        right.setRight(1);
        Integer rightCount = examService.count(right);
        examCountVO.setRate(rightCount *100/ count);

        EResultDTO resultDTO = new EResultDTO();
        resultDTO.setUserId(userId);
        List<EResultDTO> eResultDTOS = examService.queryExamResult(resultDTO);
        if (CollectionUtils.isEmpty(eResultDTOS)) {
            examCountVO.setRate(0);
        } else {

            EResultDTO maxScoreDTO = eResultDTOS.stream().max(Comparator.comparing(EResultDTO::getScore))
                    .get();
            examCountVO.setMaxScore(Integer.valueOf(maxScoreDTO.getScore()));
        }
        return EResult.newSuccessInstance(examCountVO);
    }


}
