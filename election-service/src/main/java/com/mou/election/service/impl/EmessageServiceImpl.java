package com.mou.election.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.convert.EmessageConvert;
import com.mou.election.dal.domian.*;
import com.mou.election.dal.mapper.EmessageDOMapper;
import com.mou.election.dal.mapper.EuserMessageDOMapper;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.model.*;
import com.mou.election.service.EmessageService;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmessageServiceImpl implements EmessageService {
    private Logger logger = LoggerFactory.getLogger(EmessageServiceImpl.class);

    @Autowired
    private EmessageDOMapper emessageDOMapper;
    @Autowired
    private EuserMessageDOMapper euserMessageDOMapper;


    @Override
    public EResult add(EmessageDTO mesageDto, Set<String> userAttributes) {
        EmessageDO messageDO = EmessageConvert.dto2do(mesageDto);
        String uuid = UUID.randomUUID().toString().replace("-","");
        messageDO.setCreateTime(new Date());
        messageDO.setLastUpdateTime(new Date());
        messageDO.setUuid(uuid);
        messageDO.setType(2);
        messageDO.setIsChangeTerm(StringUtil.isEmpty(messageDO.getIsChangeTerm())?"N":messageDO.getIsChangeTerm());
        messageDO.setStatus(ElectionConstants.MESSAGE_STATUS_Y);
        //插入消息表
        emessageDOMapper.insert(messageDO);
        //插入用户消息表
        userAttributes.forEach(str->{
            EuserMessageDO euserMessageDO = new EuserMessageDO();
            euserMessageDO.setUserAttr(str);
            euserMessageDO.setMessageId(uuid);
            euserMessageDO.setMessageReplyStatus(ElectionConstants.REPLY_STATUS_N);
            euserMessageDO.setIsReceipt(messageDO.getIsReceipt());
            euserMessageDO.setPopupStatus(ElectionConstants.POPUP_STATUS_N);
            euserMessageDO.setIsChangeTerm(StringUtil.isEmpty(messageDO.getIsChangeTerm())?"N":messageDO.getIsChangeTerm());
            euserMessageDO.setCreateTime(new Date());
            euserMessageDO.setLastUpdateTime(new Date());
            euserMessageDOMapper.insert(euserMessageDO);
        });
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @Override
    public EResult delById(EmessageDTO mesageDto) {
        if(StringUtils.isNullOrEmpty(mesageDto.getUuid())){
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        EmessageDO messageDO = EmessageConvert.dto2do(mesageDto);
        messageDO.setLastUpdateTime(new Date());
        messageDO.setStatus(ElectionConstants.MESSAGE_STATUS_N);
        EmessageDOExample messageExample = new EmessageDOExample();
        createCriteria(messageExample,messageDO.getUuid(),ElectionConstants.MESSAGE_STATUS_Y);
        emessageDOMapper.updateByExampleSelective(messageDO,messageExample);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @Override
    public EResult update(EmessageDTO mesageDto) {
        if(StringUtils.isNullOrEmpty(mesageDto.getUuid())){
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        logger.info("消息更新请求参数uuid:--" + mesageDto.getUuid());
        EmessageDO messageDO = EmessageConvert.dto2do(mesageDto);
        messageDO.setLastUpdateTime(new Date());
        EmessageDOExample messageExample = new EmessageDOExample();
        createCriteria(messageExample,messageDO.getUuid(),ElectionConstants.MESSAGE_STATUS_Y);
        int success = emessageDOMapper.updateByExampleSelective(messageDO,messageExample);
        //int success = emessageDOMapper.updateByExample(messageDO,messageExample);
        if(success >= 1){
            return EResult.newSuccessInstance(Boolean.TRUE);
        }
        return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
    }

    @Override
    public List<EmessageDTO> queryById(String id, EPageResult page) {
        EmessageDOExample emessageDOExample = new EmessageDOExample();
        if("all".equals(id)){
            EmessageDOExample.Criteria criteria = emessageDOExample.or();
            criteria.andStatusEqualTo(ElectionConstants.MESSAGE_STATUS_Y);
            if(page.getCurrentPageNo() == null) {
                page.setCurrentPageNo(ElectionConstants.PAGE_DEFAULT_INDEX);
            }
            if(page.getPageSize() == null) {
                page.setPageSize(ElectionConstants.PAGE_DEFAULT_SIZE);
            }
            if(StringUtil.isNotEmpty(page.getKeyWord())){
                criteria.andHeadLike(page.getKeyWord());
                EmessageDOExample.Criteria criteriaDesct = emessageDOExample.or();
                criteriaDesct.andStatusEqualTo(ElectionConstants.MESSAGE_STATUS_Y);
                criteriaDesct.andDesctLike(page.getKeyWord());
                EmessageDOExample.Criteria criteriaDetail = emessageDOExample.or();
                criteriaDetail.andStatusEqualTo(ElectionConstants.MESSAGE_STATUS_Y);
                criteriaDetail.andDetailsLike(page.getKeyWord());
            }
            Page<EmessageDO> pageInfo = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize(),"create_time desc")
                    .doSelectPage(()->emessageDOMapper.selectByExample(emessageDOExample));
            return pageInfo.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
        }
        createCriteria(emessageDOExample,id,ElectionConstants.MESSAGE_STATUS_Y);
        List<EmessageDO> messageDTOs = emessageDOMapper.selectByExample(emessageDOExample);
        if(messageDTOs == null || messageDTOs.size()<=0 ){
            return null;
        }
        return messageDTOs.stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
    }

    @Override
    public EResult reply(EuserMessageDTO userMessageDTO) {
        //判断参数是否为空
        if(userMessageDTO.getMessageId()== null || userMessageDTO.getUserAttr() == null) {
            return EResult.newErrorInstance(ErrorCodeEnum.PARAM_ERROR);
        }
        EuserMessageDO userMessageDO = EmessageConvert.dto2do(userMessageDTO);
        userMessageDO.setLastUpdateTime(new Date());
        EuserMessageDOExample example = new EuserMessageDOExample();
        EuserMessageDOExample.Criteria criteria = example.createCriteria();
        criteria.andMessageIdEqualTo(userMessageDO.getMessageId());
        criteria.andUserAttrEqualTo(userMessageDTO.getUserAttr());
        euserMessageDOMapper.updateByExampleSelective(userMessageDO,example);
        return EResult.newSuccessInstance(Boolean.TRUE);
    }

    @Override
    public Map<String,Object> replyRead(String userAttr,EPageResult page) {
        if(page.getPageSize() == null){
            page.setPageSize(ElectionConstants.PAGE_DEFAULT_SIZE);
        }
        if(page.getCurrentPageNo() == null) {
            page.setCurrentPageNo(ElectionConstants.PAGE_DEFAULT_INDEX);
        }
        /**
         * 显示30天内已读的消息
         * 显示未读消息并统计
         * 换届消息
         * 分页
         */
        Page<EuserMessageJoinDO> pageInfoCT = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize(),"create_time desc")
                .doSelectPage(()->euserMessageDOMapper.findUserMessageJoinByIsCT(userAttr));
        Page<EuserMessageJoinDO> pageInfoNotice = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize())
                .doSelectPage(()->euserMessageDOMapper.findUserMessageJoinByNotice(userAttr));
        int notReadTotal = euserMessageDOMapper.findUserMessageJoinByNotice(userAttr).stream()
                .filter(userMessageJoin -> ElectionConstants.READ_STATUS_N.equals(userMessageJoin.getReadStatus()))
                .collect(Collectors.toList()).size();
        Map<String,Object> userMessageResponse = new HashMap();
        userMessageResponse.put("ctMessageTotal",pageInfoCT.getTotal());
        userMessageResponse.put("ctMessageList",CollectionUtils.isEmpty(pageInfoCT.getResult()) ? Collections.EMPTY_LIST :
                pageInfoCT.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList()));
        userMessageResponse.put("ctMessageNotRead",CollectionUtils.isEmpty(pageInfoCT.getResult()) ? Collections.EMPTY_LIST :
                pageInfoCT.getResult().stream().filter(userMessage->
                        !ElectionConstants.READ_STATUS_Y.equals(userMessage.getReadStatus())).collect(Collectors.toList()).size());
        userMessageResponse.put("notReadTotal",notReadTotal);
        userMessageResponse.put("noticeTotal",pageInfoNotice.getTotal());
        userMessageResponse.put("noticeMessageList",CollectionUtils.isEmpty(pageInfoNotice.getResult()) ? Collections.EMPTY_LIST :
                pageInfoNotice.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList()));
        return userMessageResponse;
        //第一次推送更新弹出状态
        /*List<EuserMessageJoinDO> userMessagePopFilter = euserMessageJoinDOS.stream().filter(userMessage->
                ElectionConstants.IS_POPUP_Y.equals(userMessage.getIsPopup()) && userMessage.getPopupStatus()!= ElectionConstants.POPUP_STATUS_Y)
                .collect(Collectors.toList());

        if(userMessagePopFilter != null && userMessagePopFilter.size() > 0) {
            userMessagePopFilter.forEach(userMessage->{
                EuserMessageDO euserMessageDO = new EuserMessageDO();
                euserMessageDO.setPopupStatus(ElectionConstants.POPUP_STATUS_Y);
                euserMessageDO.setLastUpdateTime(new Date());
                EuserMessageDOExample example = new EuserMessageDOExample();
                EuserMessageDOExample.Criteria criteria = example.createCriteria();
                criteria.andUserAttrEqualTo(userMessage.getUserAttr());
                criteria.andMessageIdEqualTo(userMessage.getUuid());
                euserMessageDOMapper.updateByExampleSelective(euserMessageDO,example);
            });
        }*/
    }

    @Override
    public List<EuserMessageJoinUserDTO> replyShow(EuserMessageDTO euserMessageDTO, EPageResult page) {
        /*if(dimension.equals("message") && !StringUtils.isNullOrEmpty(euserMessageDTO.getMessageId())){
            Page<EuserMessageJoinUserDO> pageInfo = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize(),"create_time desc")
                    .doSelectPage(()->euserMessageJoinDOMapper.findUserMessageJoinUser(euserMessageDTO.getMessageId()));
            return pageInfo.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
        }else if(dimension.equals("user")&& !StringUtils.isNullOrEmpty(euserMessageDTO.getUserAttr())){
            Page<EuserMessageJoinDO> pageInfo = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize(),"create_time desc")
                    .doSelectPage(()->euserMessageJoinDOMapper.findUserMessageJoin(euserMessageDTO.getUserAttr()));
            return pageInfo.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
        }*/
        Page<EuserMessageJoinUserDO> pageInfo = PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize(), "create_time desc")
                .doSelectPage(() -> euserMessageDOMapper.findUserMessageJoinByIsReceipt(euserMessageDTO.getMessageId()));
        return pageInfo.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
    }

    @Override
    public List<EuserMessageDTO> replyShow(EPageResult page, String messageId, String userAttri) {
        EuserMessageDOExample userMessageExample = new EuserMessageDOExample();
        EuserMessageDOExample.Criteria criteria = userMessageExample.createCriteria();
        if(messageId != null){
            criteria.andMessageIdEqualTo(messageId);
        }
        if(userAttri != null){
            criteria.andUserAttrEqualTo(userAttri);
        }
        if(page.getCurrentPageNo() == null) {
            page.setCurrentPageNo(ElectionConstants.PAGE_DEFAULT_INDEX);
        }
        if(page.getPageSize() == null) {
            page.setPageSize(ElectionConstants.PAGE_DEFAULT_SIZE);
        }
        Page<EuserMessageDO> pageInfo = PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize(),"create_time desc")
                .doSelectPage(()->euserMessageDOMapper.selectByExample(userMessageExample));
        return pageInfo.getResult().stream().map(EmessageConvert::do2dto).collect(Collectors.toList());
    }

    private void createCriteriaByUuid(EmessageDOExample messageExample, String uuid) {
        EmessageDOExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUuidEqualTo(uuid);
    }

    private void createCriteria(EmessageDOExample messageExample, String uuid,int status) {
        EmessageDOExample.Criteria criteria = messageExample.createCriteria();
        if(!StringUtils.isNullOrEmpty(uuid)){
            criteria.andUuidEqualTo(uuid);
        }
        criteria.andStatusEqualTo(status);
    }
    private Date getBeforeDays(int days){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}
