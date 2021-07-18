package com.mou.election.convert;

import com.mou.election.enums.QuestionTypeEnum;
import com.mou.election.model.*;
import com.mou.election.model.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 * DTO和VO的转换器
 * 在controller域完成和下层数据层的模型转化
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class ResponseConvert {

    public static EdataDictionaryVO dataDictionaryDTO2VO(EdataDictionaryDTO edataDictionaryDTO) {
        EdataDictionaryVO edataDictionaryVO = new EdataDictionaryVO();
        BeanUtils.copyProperties(edataDictionaryDTO, edataDictionaryVO);
        return edataDictionaryVO;
    }

    public static EUserVO userDTO2VO(EUserDTO userDTO) {
        EUserVO euserVO = new EUserVO();
        BeanUtils.copyProperties(userDTO, euserVO);
        if (!CollectionUtils.isEmpty(userDTO.getRoleDTOS())) {
            euserVO.setRoleVOS(userDTO.getRoleDTOS().stream()
                    .map(roleDTO -> {
                        EroleVO eroleVO = new EroleVO();
                        eroleVO.setRoleCode(roleDTO.getRoleCode());
                        eroleVO.setRoleName(roleDTO.getRoleName());
                        return eroleVO;
                    }).collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(userDTO.getPermissionDTOS())) {
            euserVO.setPermissionVOS(userDTO.getPermissionDTOS().stream()
                    .map(permissionDTO -> {
                        EPermissionVO permissionVO = new EPermissionVO();
                        permissionVO.setPermissionCode(permissionDTO.getPermissionCode());
                        permissionVO.setPermissionName(permissionDTO.getPermissionName());
                        return permissionVO;
                    }).collect(Collectors.toList()));
        }
        return euserVO;
    }

    public static EmessageVO messageDTO2VO(EmessageDTO dto) {
        EmessageVO vo = new EmessageVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EuserMessageVO messageDTO2VO(EuserMessageDTO dto) {
        EuserMessageVO vo = new EuserMessageVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EorganizationVO organizationDTO2VO(EorganizationDTO dto) {
        EorganizationVO vo = new EorganizationVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EApplyVO applyDTO2VO(EApplyDTO dto) {
        EApplyVO vo = new EApplyVO();
        BeanUtils.copyProperties(dto, vo);
        vo.setUserVO(userDTO2VO(dto.getUserDTO()));
        vo.setStatus(dto.getStatus().getCode());
        vo.setDelay(dto.getDelay().getCode());
        vo.setStatusDesc(dto.getStatus().getDesc());
        vo.setDelayDesc(dto.getDelay().getDesc());
        return vo;
    }

    public static EroleVO roleDTO2VO(EroleDTO dto) {
        EroleVO vo = new EroleVO();
        BeanUtils.copyProperties(dto, vo);
        if (!CollectionUtils.isEmpty(dto.getPermissionDTOList())) {
            List<EPermissionVO> epermissionVOS = dto.getPermissionDTOList().stream().map(ResponseConvert::permissionDTO2VO).collect(Collectors.toList());
            epermissionVOS.forEach(permissionVO -> {
                vo.putpermissionVO(permissionVO);
            });
        }
        return vo;
    }

    public static EPermissionVO permissionDTO2VO(EPermissionDTO dto) {
        EPermissionVO vo = new EPermissionVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EuserMessageJoinVO messageDTO2VO(EuserMessageJoinDTO dto) {
        EuserMessageJoinVO vo = new EuserMessageJoinVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EuserMessageJoinUserVO messageDTO2VO(EuserMessageJoinUserDTO dto) {
        EuserMessageJoinUserVO vo = new EuserMessageJoinUserVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }


    public static ExamVO examDTO2VO(EExamDTO examDTO) {
        ExamVO examVO = new ExamVO();
        examVO.setInfo(examDTO.getTitle());
        examVO.setId(examDTO.getId());
        examVO.setGmtCreate(examDTO.getGmtCreate());
        examVO.setGmtModified(examDTO.getGmtModified());
        examVO.setScore(examDTO.getPassScore());
        examVO.setTitle(examDTO.getTitle());
        examVO.setRemark(examDTO.getRemark());
        List<QuestionVO> questionVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(examDTO.getQuestionDTOS())) {

            for (EQuestionDTO questionDTO : examDTO.getQuestionDTOS()) {
                QuestionVO questionVO = questionDTO2VO(questionDTO);
                questionVOS.add(questionVO);
            }
            examVO.setKsQuestionResponses(questionVOS);
        }
        return examVO;
    }

    public static QuestionVO questionDTO2VO(EQuestionDTO questionDTO) {
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(questionDTO, questionVO);
        questionVO.setType(questionDTO.getType().getCode());
        questionVO.setScore(questionDTO.getScore().toString());
        List<AnswerVO> ksOptionsRequests = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        if (!CollectionUtils.isEmpty(questionDTO.getAnswerDTOS())) {
            for (EAnswerDTO answerDTO : questionDTO.getAnswerDTOS()) {
                AnswerVO answerVO = answerDTO2VO(answerDTO);
                ksOptionsRequests.add(answerVO);
                if (answerVO.getRight().equals(1)) {
                    answer.add(answerVO.getArrange());
                }
            }
        }
        questionVO.setAnswer(answer);
        questionVO.setKsOptionsRequests(ksOptionsRequests);
        return questionVO;
    }

    public static AnswerVO answerDTO2VO(EAnswerDTO answerDTO) {
        AnswerVO answerVO = new AnswerVO();
        BeanUtils.copyProperties(answerDTO, answerVO);
        return answerVO;
    }


}
