/**
 * $Id: SendWxMessageRemindRunner.java,v 1.0 2021/10/31 15:44 zengws Exp $
 * <p>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.mou.election.runner;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.mou.election.EUserManager;
import com.mou.election.EorganizationManager;
import com.mou.election.constants.EConstants;
import com.mou.election.dal.domian.EorganizationDO;
import com.mou.election.dal.domian.EorganizationDOExample;
import com.mou.election.dal.mapper.EorganizationDOMapper;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.WxSendMessageDTO;
import com.mou.election.service.EorganizationService;
import com.mou.election.service.WxTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//@Component
@Slf4j
public class SendWxMessageRemindRunner implements ApplicationRunner {
    @Autowired
    private EorganizationDOMapper eorganizationDOMapper;
    @Autowired
    private WxTemplateService wxTemplateService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new messageNotifyTask(),0,1, TimeUnit.DAYS);

    }

    class messageNotifyTask implements Runnable {
        @Override
        public void run() {
            List<EorganizationDO> eorganizationDOS = getAllOrganization();
            sendOneMonthMessage(eorganizationDOS);
            sendThreeMonthMessage(eorganizationDOS);
        }
    }

    public void sendOneMonthMessage(List<EorganizationDO> eorganizationDOS){
        eorganizationDOS = eorganizationDOS.stream()
                .filter(eorganizationDO ->dateWithinMonth(eorganizationDO.getChangeTermTime(),1)
                    && hasSendMessage(eorganizationDO,"1month")).collect(Collectors.toList());
        String thing2 = "您所在的组织换届时间不足1个月";
        eorganizationDOS.forEach(
                eorganizationDO -> {
                    WxSendMessageDTO wxSendMessageDTO = wxTemplateService.getOrgMessageNotifyByMonth(eorganizationDO.getId(),thing2);
                    wxTemplateService.sendWxMessage(wxSendMessageDTO);
                    if(StringUtils.isEmpty(eorganizationDO.getSendStatus())){
                        Map<String, Object> map = JSONObject.parseObject(EConstants.APPLY_SEND_STATUS, Map.class);
                        map.put("1month","true");
                        eorganizationDO.setSendStatus(JSONObject.toJSONString(map));
                        eorganizationDOMapper.updateByPrimaryKey(eorganizationDO);
                    }
                }
        );
    }

    public void sendThreeMonthMessage(List<EorganizationDO> eorganizationDOS){
        eorganizationDOS = eorganizationDOS.stream()
                .filter(eorganizationDO ->dateWithinMonth(eorganizationDO.getChangeTermTime(),3)
                        && hasSendMessage(eorganizationDO,"3month")).collect(Collectors.toList());
        String thing2 = "您所在的组织换届时间不足3个月";
        eorganizationDOS.forEach(
                eorganizationDO -> {
                    WxSendMessageDTO wxSendMessageDTO = wxTemplateService.getOrgMessageNotifyByMonth(eorganizationDO.getId(),thing2);
                    wxTemplateService.sendWxMessage(wxSendMessageDTO);
                    if(StringUtils.isEmpty(eorganizationDO.getSendStatus())){
                        Map<String, Object> map = JSONObject.parseObject(EConstants.APPLY_SEND_STATUS, Map.class);
                        map.put("3month","true");
                        eorganizationDO.setSendStatus(JSONObject.toJSONString(map));
                        eorganizationDOMapper.updateByPrimaryKey(eorganizationDO);
                    }
                }
        );
    }

    public boolean dateWithinMonth(Date changeDate,int month) {
        int days = (int) ((changeDate.getTime() - new Date().getTime()) / (1000*3600*24));
        if(days > 0 && days < month*30) {
            return true;
        }
        return false;
    }

    public boolean hasSendMessage(EorganizationDO eorganizationDO,String monthKey){
        if(StringUtils.isEmpty(eorganizationDO.getSendStatus())){
            return true;
        }
        Map<String, Object> map = JSONObject.parseObject(EConstants.APPLY_SEND_STATUS, Map.class);

        if("false".equals(map.get(monthKey))) {
            return true;
        }
        return false;
    }


    public List<EorganizationDO> getAllOrganization () {
        EorganizationDOExample eorganizationDOExample = new EorganizationDOExample();
        EorganizationDOExample.Criteria criteria = eorganizationDOExample.createCriteria();
        criteria.andIdIsNotNull();
        List<EorganizationDO> eorganizationDOS = eorganizationDOMapper.selectByExample(eorganizationDOExample);
        return eorganizationDOS.stream().filter(organizationDO-> !StringUtils.isEmpty(organizationDO.getChangeTermTime()))
                .collect(Collectors.toList());
    }


}