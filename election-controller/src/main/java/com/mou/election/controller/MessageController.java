package com.mou.election.controller;


import com.github.pagehelper.util.StringUtil;
import com.mou.election.EUserManager;
import com.mou.election.convert.RequestConvert;
import com.mou.election.convert.ResponseConvert;
import com.mou.election.dal.domian.EuserDO;
import com.mou.election.dal.mapper.EuserDOMapper;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.model.*;
import com.mou.election.model.request.EmessageRequest;
import com.mou.election.model.request.EuserMessageRequest;
import com.mou.election.model.vo.EuserMessageJoinVO;
import com.mou.election.service.EmessageService;
import com.mou.election.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message/")
public class MessageController {
    @Autowired
    EmessageService emessageService;
    @Autowired
    EUserManager euserManager;

    @PostMapping(value="add",
            produces = "application/json;charset=utf-8")
    public EResult add(@RequestBody EmessageRequest request) {
        Set<String> userAttributes = parsePushUsers(request.getPushUsers());
        if(userAttributes == null) {
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        EmessageDTO messageDTO = RequestConvert.messageRequest2DTO(request);
        return emessageService.add(messageDTO,userAttributes);
    }

    @PostMapping(value="del",
            produces = "application/json;charset=utf-8")
    public EResult del (@RequestBody EmessageRequest request) {
        EmessageDTO messageDTO = RequestConvert.messageRequest2DTO(request);
        return emessageService.delById(messageDTO);
    }

    @PostMapping(value="update",
            produces = "application/json;charset=utf-8")
    public EResult update (@RequestBody EmessageRequest request) {
        EmessageDTO messageDTO = RequestConvert.messageRequest2DTO(request);
        return emessageService.update(messageDTO);
    }

    /**
     * 根据messageId 查询 message或者 查询All message
     * @param id
     * @param page
     * @return
     */
    @GetMapping(value="query/{id}",
            produces = "application/json;charset=utf-8")
    public EResult query (@PathVariable String id, EPageResult page) {
        List<EmessageDTO> dto =  emessageService.queryById(id,page);
        if(dto == null){
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        Map<String,Object> responseMap = new HashMap();
        responseMap.put("messageList",dto.stream().map(ResponseConvert::messageDTO2VO));
        responseMap.put("totalItems",dto.size());
        return EResult.newSuccessInstance(responseMap);
    }

    /**
     * 客户端更新消息
     */
    @PostMapping(value="notice",
            produces = "application/json;charset=utf-8")
    public EResult reply (@RequestBody EuserMessageRequest userMessageRequest) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        EUserDTO euserDTO = euserManager.getUserByToken(token);
        if(euserDTO == null){
            return EResult.newErrorInstance(ErrorCodeEnum.USER_NOT_EXIST);
        }
        userMessageRequest.setUserAttr(euserDTO.getPhone());
        EuserMessageDTO euserMessageDTO = RequestConvert.userMessageRequest2DTO(userMessageRequest);
        return emessageService.reply(euserMessageDTO);
    }

    /**
     * 客户端获取消息
     * @return
     */
    @GetMapping(value = "notice",
            produces = "application/json;charset=utf-8")
    public EResult replyRead(EPageResult page) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if(token == null){
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        EUserDTO euserDTO = euserManager.getUserByToken(token);
        if(euserDTO == null){
            return EResult.newErrorInstance(ErrorCodeEnum.USER_NOT_EXIST);
        }
        Map<String,Object> responseMap = emessageService.replyRead(euserDTO.getPhone(),page);
        List<EuserMessageJoinDTO> ctMessageList = (List<EuserMessageJoinDTO>) responseMap.get("ctMessageList");
        List<EuserMessageJoinDTO> noticeMessageList = (List<EuserMessageJoinDTO>) responseMap.get("noticeMessageList");
        responseMap.put("ctMessageList", CollectionUtils.isEmpty(ctMessageList) ? Collections.EMPTY_LIST :
                ctMessageList.stream().map(ResponseConvert::messageDTO2VO).collect(Collectors.toList()));
        responseMap.put("noticeMessageList", CollectionUtils.isEmpty(noticeMessageList) ? Collections.EMPTY_LIST :
                noticeMessageList.stream().map(ResponseConvert::messageDTO2VO).collect(Collectors.toList()));
        return EResult.newSuccessInstance(responseMap);
    }

    /**
     * 根据不同的维度回执展示
     * @return
     */
    @GetMapping(value = "reply",
            produces = "application/json;charset=utf-8")
    public EResult replyShow(EuserMessageRequest request, EPageResult page) {
        EuserMessageDTO euserMessageDTO = RequestConvert.userMessageRequest2DTO(request);
        List<EuserMessageJoinUserDTO> dtoList = emessageService.replyShow(euserMessageDTO,page);
        if(dtoList == null) {
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        Map<String,Object> responseMap = new HashMap();
        responseMap.put("messageList",dtoList.stream().map(ResponseConvert::messageDTO2VO));
        responseMap.put("totalItems",dtoList.size());
        return  EResult.newSuccessInstance(responseMap);
    }

    private Set<String> parsePushUsers(String pushUsers){
        Set<String> userSet = new HashSet<>();
        String[] strParse = pushUsers.split("\\|");
        if(!StringUtil.isEmpty(strParse[0])) {
            List<EUserDTO> user = euserManager.query(null);
            if(user != null &&  user.size()>0) {
                return user.stream().map(EUserDTO::getPhone).collect(Collectors.toSet());
            }
        }

        if(!StringUtil.isEmpty(strParse[1])) {
            String orgIds[] = strParse[1].split(",");
            Arrays.stream(orgIds).forEach(orgId -> {
                List<EUserDTO> users = euserManager.getUserByOrgId(Long.valueOf(orgId));
                if(!CollectionUtils.isEmpty(users)){
                    users.stream().forEach(user-> userSet.add(user.getPhone()));
                }
            });
         }
        if(strParse.length > 2 && !StringUtil.isEmpty(strParse[2])){
            String phones[] = strParse[2].split(",");
            Arrays.stream(phones).forEach(phone -> {
                userSet.add(phone);
            });
        }
        return userSet;
    }

}
