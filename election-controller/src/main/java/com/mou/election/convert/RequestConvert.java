package com.mou.election.convert;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ApplyDelayEnum;
import com.mou.election.enums.ApplyStatusEnum;
import com.mou.election.enums.LoginTypeEnum;
import com.mou.election.enums.QuestionTypeEnum;
import com.mou.election.model.*;
import com.mou.election.model.request.*;
import com.mou.election.model.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 * request和DTO模型的转化器
 * 在controller域完成和下层数据层的模型转化
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class RequestConvert {

    public static EdataDictionaryDTO dataDictionaryRequest2DTO(EdataDictionaryRequest request) {
        EdataDictionaryDTO dataDictionaryDTO = new EdataDictionaryDTO();
        BeanUtils.copyProperties(request, dataDictionaryDTO);
        if (request.getFeatureKey() != null && !CollectionUtils.isEmpty(request.getFeatureValue())) {
            String valueStr = JSONObject.toJSONString(request.getFeatureValue());
            Map<String, String> map = ImmutableMap.of(request.getFeatureKey(), valueStr);
            request.setDataFeature(JSONObject.toJSONString(map));
        }
        return dataDictionaryDTO;
    }

    public static EUserDTO userRequest2DTO(EUserReqeust request) {
        EUserDTO euserDTO = new EUserDTO();
        BeanUtils.copyProperties(request, euserDTO);
        if (request.getLoginType() != null) {
            euserDTO.setLoginTypeEnum(LoginTypeEnum.valueOf(request.getLoginType()));
        }
        if (!CollectionUtils.isEmpty(request.getRoleCodes())) {
            Map<String, String> roleMap = new HashMap<>();
            roleMap.put(EConstants.ROLE, JSONObject.toJSONString(request.getRoleCodes()));
            euserDTO.setFeature(JSONObject.toJSONString(roleMap));
        }
        return euserDTO;
    }

    public static EmessageDTO messageRequest2DTO(EmessageRequest request) {
        EmessageDTO messageDTO = new EmessageDTO();
        BeanUtils.copyProperties(request, messageDTO);
        return messageDTO;
    }

    public static EuserMessageDTO userMessageRequest2DTO(EuserMessageRequest request) {
        EuserMessageDTO userMessageDTO = new EuserMessageDTO();
        BeanUtils.copyProperties(request, userMessageDTO);
        return userMessageDTO;
    }

    public static EorganizationDTO organizationRequest2DTO(EorganizationRequest request) {
        EorganizationDTO dto = new EorganizationDTO();
        BeanUtils.copyProperties(request, dto);
        return dto;
    }

    public static EroleDTO roleRequest2DTO(EroleRequest request) {
        EroleDTO dto = new EroleDTO();
        BeanUtils.copyProperties(request, dto);
        if (!CollectionUtils.isEmpty(request.getPermissionCodes())) {
            Map<String, String> permissionMap = new HashMap<>();
            permissionMap.put(EConstants.PERMISSION, JSONObject.toJSONString(request.getPermissionCodes()));
            dto.setFeature(JSONObject.toJSONString(permissionMap));
        }
        return dto;
    }

    public static EPermissionDTO permissionRequest2DTO(EPermissionRequest request) {
        EPermissionDTO permissionDTO = new EPermissionDTO();
        BeanUtils.copyProperties(request, permissionDTO);
        return permissionDTO;
    }

    public static EApplyDTO applyRequest2DTO(EApplyRequest request) {
        EApplyDTO applyDTO = new EApplyDTO();
        BeanUtils.copyProperties(request, applyDTO);
        if (request.getDelay() != null) {
            applyDTO.setDelay(ApplyDelayEnum.getApplyDelayByCode(request.getDelay()));

        }
        if (request.getStatus() != null) {
            applyDTO.setStatus(ApplyStatusEnum.getApplyStatusByCode(request.getStatus()));

        }
        return applyDTO;
    }

    public static EExamDTO examRequest2DTO(ExamVO examVO) {
        EExamDTO examDTO = new EExamDTO();
        BeanUtils.copyProperties(examVO, examDTO);
        examDTO.setTitle(examVO.getTitle());
        examDTO.setRemark(examVO.getRemark());
        if (examVO.getScore() == null) {
            examDTO.setPassScore("100");
        } else {
            examDTO.setPassScore(examVO.getScore());
        }
        if (!CollectionUtils.isEmpty(examVO.getKsQuestionRequests())) {
            List<EQuestionDTO> questionDTOS = examVO.getKsQuestionRequests().stream().
                    map(RequestConvert::questionVO2DTO)
                    .collect(Collectors.toList());
            Integer avgScore = Integer.valueOf(examDTO.getPassScore())/questionDTOS.size();
            for(EQuestionDTO questionDTO: questionDTOS){
                questionDTO.setScore(avgScore);
            }

            examDTO.setQuestionDTOS(questionDTOS);
        }

        return examDTO;
    }


    public static EQuestionDTO questionVO2DTO(QuestionVO questionVO) {
        EQuestionDTO questionDTO = new EQuestionDTO();
        BeanUtils.copyProperties(questionVO, questionDTO);
        questionDTO.setSort(questionVO.getSort());
        questionDTO.setTitle(questionVO.getTitle());
        questionDTO.setType(QuestionTypeEnum.getQuestionTypeByCode(questionVO.getType()));
        if (null == questionVO.getScore()) {

            questionDTO.setScore(10);
        } else {
            questionDTO.setScore(Integer.valueOf(questionVO.getScore()));
        }
        List<EAnswerDTO> answerDTOS = new ArrayList<>();
        questionVO.getKsOptionsRequests().forEach(answerVO -> {
            // 0不是正确答案，1是正确答案
            if (questionVO.getAnswer().contains(answerVO.getArrange())) {
                answerVO.setRight(1);
            } else {
                answerVO.setRight(0);
            }
            EAnswerDTO answerDTO = answerVO2DTO(answerVO);
            answerDTOS.add(answerDTO);
        });
        questionDTO.setAnswerDTOS(answerDTOS);
        return questionDTO;
    }

    public static EAnswerDTO answerVO2DTO(AnswerVO answerVO) {
        EAnswerDTO answerDTO = new EAnswerDTO();
        BeanUtils.copyProperties(answerVO, answerDTO);
        answerDTO.setArrange(answerVO.getArrange());
        answerDTO.setChoice(answerVO.getChoice());
        answerDTO.setRight(answerVO.getRight());
        return answerDTO;
    }

    public static EExamAnswerDTO examAnswerVO2DTO(ExamAnswerVO examAnswerVO) {
        EExamAnswerDTO examAnswerDTO = new EExamAnswerDTO();
        BeanUtils.copyProperties(examAnswerVO, examAnswerDTO);

        return examAnswerDTO;
    }

    public static EResultDTO resultVO2DTO(ResultVO resultVO){
        EResultDTO resultDTO = new EResultDTO();
        BeanUtils.copyProperties(resultVO,resultDTO);
        return resultDTO;
    }

}
