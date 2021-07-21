package com.mou.election.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mou.election.convert.EapplyConvert;
import com.mou.election.convert.ExamConvert;
import com.mou.election.dal.domian.*;
import com.mou.election.dal.mapper.*;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.enums.QuestionTypeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.*;
import com.mou.election.service.ExamService;
import com.mou.election.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private EExamDOMapper examDOMapper;

    @Autowired
    private EQuestionDOMapper questionDOMapper;

    @Autowired
    private EAnswerDOMapper answerDOMapper;

    @Autowired
    private EExamAnswerDOMapper examAnswerDOMapper;

    @Autowired
    private EResultDOMapper resultDOMapper;


    @Override
    public void add(EExamDTO examDTO) {
        EExamDO examDO = new EExamDO();
        examDO.setGmtCreate(new Date());
        examDO.setGmtModified(new Date());
        examDO.setTitle(examDTO.getTitle());
        examDO.setRemark(examDTO.getRemark());
        examDO.setPassScore(examDTO.getPassScore());
        examDOMapper.insert(examDO);

        for (EQuestionDTO questionDTO : examDTO.getQuestionDTOS()) {
            EQuestionDO questionDO = new EQuestionDO();
            questionDO.setExamId(examDO.getId());
            questionDO.setGmtCreate(new Date());
            questionDO.setGmtModified(new Date());
            questionDO.setScore(questionDTO.getScore());
            questionDO.setTitle(questionDTO.getTitle());
            questionDO.setSort(questionDTO.getSort());
            questionDO.setType(questionDTO.getType().getCode());
            questionDOMapper.insert(questionDO);
            for (EAnswerDTO answerDTO : questionDTO.getAnswerDTOS()) {
                EAnswerDO answerDO = new EAnswerDO();
                answerDO.setGmtCreate(new Date());
                answerDO.setGmtModified(new Date());
                answerDO.setArrange(answerDTO.getArrange());
                answerDO.setRightAnswer(answerDTO.getRight());
                answerDO.setChoice(answerDTO.getChoice());
                answerDO.setQuestionId(questionDO.getId());
                answerDOMapper.insert(answerDO);
            }
        }
    }

    @Override
    public EExamDTO get(Long id) {
        EExamDTO examDTO = new EExamDTO();

        EExamDO examDO = examDOMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(examDO, examDTO);

        EQuestionDOExample example = new EQuestionDOExample();
        example.createCriteria().andExamIdEqualTo(examDO.getId());
        List<EQuestionDO> questionDOS = questionDOMapper.selectByExample(example);
        List<EQuestionDTO> questionDTOS = new ArrayList<>();
        for (EQuestionDO questionDO : questionDOS) {
            EQuestionDTO questionDTO = new EQuestionDTO();
            BeanUtils.copyProperties(questionDO, questionDTO);
            questionDTO.setType(QuestionTypeEnum.getQuestionTypeByCode(questionDO.getType()));

            EAnswerDOExample answerDOExample = new EAnswerDOExample();
            answerDOExample.createCriteria().andQuestionIdEqualTo(questionDO.getId());
            List<EAnswerDO> answerDOS = answerDOMapper.selectByExample(answerDOExample);
            List<EAnswerDTO> answerDTOS = new ArrayList<>();
            for (EAnswerDO answerDO : answerDOS) {
                EAnswerDTO answerDTO = new EAnswerDTO();
                BeanUtils.copyProperties(answerDO, answerDTO);
                answerDTO.setRight(answerDO.getRightAnswer());
                answerDTOS.add(answerDTO);
            }
            questionDTO.setAnswerDTOS(answerDTOS);
            questionDTOS.add(questionDTO);
        }
        examDTO.setQuestionDTOS(questionDTOS);
        return examDTO;
    }

    @Override
    public PageInfo<EExamDTO> pageQuery(EExamDTO examDTO) {
        EExamDOExample example = new EExamDOExample();
        Page<EExamDO> page = PageHelper.startPage(examDTO.getCurrentPageNo(), examDTO.getPageSize())
                .doSelectPage(() -> examDOMapper.selectByExample(example));
        List<EExamDTO> collect = page.getResult().stream().map(ExamConvert::do2dto).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(page.getTotal());
        pageInfo.setList(collect);
        return pageInfo;
    }

    @Override
    public Long userAnswer(HttpServletRequest httpServletRequest, EExamAnswerDTO examAnswerDTO) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));
        EExamDTO examDTO = get(examAnswerDTO.getExamId());
        List<EQuestionDTO> questionDTOS = examDTO.getQuestionDTOS();
        List<String> qustionAnswers = examAnswerDTO.getQustionAnswerRequests();
        List<EExamAnswerDO> examAnswerDOS = new ArrayList<>();

        for (int i = 0; i < questionDTOS.size(); i++) {
            EExamAnswerDO examAnswerDO = new EExamAnswerDO();
            examAnswerDO.setExamId(examAnswerDTO.getExamId());
            examAnswerDO.setGmtCreate(new Date());
            examAnswerDO.setGmtModified(new Date());
            EQuestionDTO questionDTO = questionDTOS.get(i);
            examAnswerDO.setQuestionId(questionDTO.getId());
            examAnswerDO.setUserId(userId);
            examAnswerDO.setResult(qustionAnswers.get(i));
            examAnswerDO.setIsRight(examAnswerIsRight(questionDTO, qustionAnswers.get(i)));
            questionDTO.setUserAnswerIsRight(examAnswerDO.getIsRight() == 1);
            examAnswerDOS.add(examAnswerDO);
        }

        EResultDO resultDO = new EResultDO();
        resultDO.setGmtCreate(new Date());
        resultDO.setGmtModified(new Date());
        resultDO.setUserId(userId);
        resultDO.setExamId(examDTO.getId());
        resultDO.setScore(calculateScore(questionDTOS));
        if (Double.valueOf(resultDO.getScore()) > Double.valueOf(examDTO.getPassScore())) {
            resultDO.setPass(1);
        } else {
            resultDO.setPass(0);
        }
        resultDOMapper.insert(resultDO);
        examAnswerDOS.forEach(examAnswerDO -> {
            examAnswerDO.setResultId(resultDO.getId());
            examAnswerDOMapper.insert(examAnswerDO);
        });
        return resultDO.getId();

    }

    private String calculateScore(List<EQuestionDTO> questionDTOS) {
        Integer sum = questionDTOS.stream().filter(EQuestionDTO::getUserAnswerIsRight)
                .mapToInt(EQuestionDTO::getScore).sum();
        return sum.toString();

    }

    @Override
    public EExamDTO getUserAnswer(HttpServletRequest httpServletRequest, EResultDTO resultDTO) {
        Long userId = TokenUtils.getUserIdByToken(httpServletRequest.getHeader("token"));
        EResultDO resultDO = resultDOMapper.selectByPrimaryKey(resultDTO.getId());
        EExamDTO examDTO = get(resultDO.getExamId());

        List<EQuestionDTO> questionDTOS = examDTO.getQuestionDTOS();
        for (EQuestionDTO questionDTO : questionDTOS) {

            EExamAnswerDOExample example = new EExamAnswerDOExample();
            example.createCriteria().andExamIdEqualTo(resultDO.getExamId()).andResultIdEqualTo(resultDO.getId())
                    .andQuestionIdEqualTo(questionDTO.getId()).andUserIdEqualTo(userId);
            List<EExamAnswerDO> examAnswerDOS = examAnswerDOMapper.selectByExample(example);
            if (examAnswerDOS == null || examAnswerDOS.size() > 1) {
                questionDTO.setUserAnswerIsRight(false);
                questionDTO.setAnalysis("");
            } else {
                questionDTO.setUserAnswerIsRight(examAnswerDOS.get(0).getIsRight() == 1);
                questionDTO.setAnalysis(examAnswerDOS.get(0).getResult());
            }
        }
        return examDTO;
    }


    private Integer examAnswerIsRight(EQuestionDTO questionDTO, String answer) {
        List<EAnswerDTO> answerDTOS = questionDTO.getAnswerDTOS();

        List<String> rightAnswers = answerDTOS.stream().filter(answerDTO -> answerDTO.getRight() == 1)
                .map(EAnswerDTO::getArrange).collect(Collectors.toList());
        // 单选，答案和选项正确的那个一样即可
        if (questionDTO.getType().equals(QuestionTypeEnum.ONE)) {
            for (EAnswerDTO answerDTO : answerDTOS) {
                if (answer.equalsIgnoreCase(answerDTO.getArrange())) {
                    return answerDTO.getRight();
                }
            }
        }

        // 多选
        if (questionDTO.getType().equals(QuestionTypeEnum.MORE)) {

            String[] moreAnswers = answer.split(",");
            if (moreAnswers.length != rightAnswers.size()) {
                return 0;
            }
            for (String one : moreAnswers) {
                if (!rightAnswers.contains(one)) {
                    return 0;
                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public Integer count(EExamAnswerDTO examAnswerDTO) {

        EExamAnswerDOExample example = new EExamAnswerDOExample();
        EExamAnswerDOExample.Criteria criteria = example.createCriteria();
        if (examAnswerDTO.getUserId() != null) {
            criteria.andUserIdEqualTo(examAnswerDTO.getUserId());

        }
        if (examAnswerDTO.getRight() != null && examAnswerDTO.getRight() != 0) {
            criteria.andIsRightEqualTo(1);
        }
        return examAnswerDOMapper.countByExample(example);
    }


    @Override
    public List<EResultDTO> queryExamResult(EResultDTO resultDTO) {
        EResultDOExample example = new EResultDOExample();
        EResultDOExample.Criteria criteria = example.createCriteria();
        if (resultDTO.getUserId() != null) {
            criteria.andUserIdEqualTo(resultDTO.getUserId());
        }
        if (resultDTO.getExamId() != null) {
            criteria.andExamIdEqualTo(resultDTO.getExamId());
        }
        List<EResultDO> resultDOS = resultDOMapper.selectByExample(example);
        return resultDOS.stream().map(ExamConvert::resultDO2DTO).collect(Collectors.toList());

    }

    @Override
    public void update(EExamDTO examDTO) {
        EExamDO examDO = ExamConvert.dto2do(examDTO);
        examDO.setGmtModified(new Date());
        examDOMapper.updateByPrimaryKey(examDO);

        for (EQuestionDTO questionDTO : examDTO.getQuestionDTOS()) {
            EQuestionDO questionDO = ExamConvert.questionDTO2DO(questionDTO);
            questionDO.setGmtModified(new Date());
            questionDOMapper.updateByPrimaryKey(questionDO);
            List<EAnswerDTO> answerDTOS = questionDTO.getAnswerDTOS();
            for (EAnswerDTO answerDTO : answerDTOS) {
                EAnswerDO answerDO = ExamConvert.answerDTO2DO(answerDTO);
                answerDO.setGmtModified(new Date());
                answerDOMapper.updateByPrimaryKey(answerDO);
            }
        }
    }

    @Override
    public void delete(Long id) {
        EExamDO examDO = examDOMapper.selectByPrimaryKey(id);
        if (examDO == null) {
            throw new EbizException(ErrorCodeEnum.PARAM_ERROR);
        }
        examDOMapper.deleteByPrimaryKey(id);

        EQuestionDOExample questionDOExample = new EQuestionDOExample();
        questionDOExample.createCriteria().andExamIdEqualTo(examDO.getId());
        List<EQuestionDO> questionDOS = questionDOMapper.selectByExample(questionDOExample);

        for(EQuestionDO questionDO: questionDOS){
            questionDOMapper.deleteByPrimaryKey(questionDO.getId());

            EAnswerDOExample answerDOExample = new EAnswerDOExample();
            answerDOExample.createCriteria().andQuestionIdEqualTo(questionDO.getId());
            answerDOMapper.deleteByExample(answerDOExample);
        }
    }
}
