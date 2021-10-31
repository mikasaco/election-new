package com.mou.election.runner;

import com.mou.election.convert.EuserConvert;
import com.mou.election.dal.domian.*;
import com.mou.election.dal.mapper.EmessageDOMapper;
import com.mou.election.dal.mapper.EuserDOMapper;
import com.mou.election.dal.mapper.EuserMessageDOMapper;
import com.mou.election.model.ElectionConstants;
import com.mou.election.model.EmessageDTO;
import com.mou.election.model.EUserDTO;
import com.mou.election.service.EmessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class ChangeTermThreadRunner implements ApplicationRunner {
    @Autowired
    private EuserDOMapper euserDOMapper;
    @Autowired
    private EuserMessageDOMapper euserMessageDOMapper;
    @Autowired
    private EmessageService emessageService;
    @Autowired
    private EmessageDOMapper emessageDOMapper;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new ChangeTermTask(),0,1, TimeUnit.DAYS);
    }

    class ChangeTermTask implements Runnable {
        @Override
        public void run() {
            log.info("换届时间状态更新批量初始化启动");
            Map<String,List<EUserDTO>> usersMap = getChangeTermUser();
            if(usersMap == null || usersMap.size()<=0){
                return;
            }
            //处理9-12个月的换届消息
            dealWith369And12(getChangeTermNotIn369User(),ElectionConstants.CHANGE_TERM_STATUS_3);
            //处理6-9个月的换届消息
            dealWith369And12(usersMap.get("mother9"),ElectionConstants.CHANGE_TERM_STATUS_0);
            //处理3-6个月的换届消息
            dealWith369And12(usersMap.get("mother6"),ElectionConstants.CHANGE_TERM_STATUS_1);
            //处理0-3个月的换届消息
            dealWith369And12(usersMap.get("mother3"),ElectionConstants.CHANGE_TERM_STATUS_2);
            log.info("换届时间状态更新批量完成");
        }
    }

    private void dealWith369And12(List<EUserDTO> userFor90Between60, int changeTermStatus){
        if(!collectionIsNull(userFor90Between60)){
            userFor90Between60.forEach(user->{
                EuserMessageDOExample euserMessageDOExample = new EuserMessageDOExample();
                EuserMessageDOExample.Criteria criteria = euserMessageDOExample.createCriteria();
                criteria.andUserAttrEqualTo(user.getPhone());
                criteria.andIsChangeTermEqualTo(ElectionConstants.IS_CHANGE_TERM_Y);
                List<EuserMessageDO> euserMessageDOList = euserMessageDOMapper.selectByExample(euserMessageDOExample);
                //当用户没有换届消息时初始化插入
                if(collectionIsNull(euserMessageDOList) && ElectionConstants.CHANGE_TERM_STATUS_3 != changeTermStatus) {
                    EmessageDTO emessageDTO = new EmessageDTO();
                    emessageDTO.setIsChangeTerm(ElectionConstants.IS_CHANGE_TERM_Y);
                    emessageDTO.setChangeTermStatus(changeTermStatus);
                    emessageDTO.setHead("换届消息");
                    emessageDTO.setDesct("hahahahhhahahaha初始化");
                    emessageService.add(emessageDTO,Arrays.asList(user.getPhone()).stream().collect(Collectors.toSet()));
                }else{
                    euserMessageDOList.forEach(userMessage->{
                        List<EuserMessageJoinDO>  userMessageJoin = euserMessageDOMapper.findUserMessageJoin(userMessage.getUserAttr());
                        if(!collectionIsNull(userMessageJoin) &&
                                changeTermStatus != userMessageJoin.get(0).getChangeTermStatus()) {
                            //更新换届消息状态
                            EmessageDOExample emessageDOExample = new EmessageDOExample();
                            EmessageDOExample.Criteria messageCriteria = emessageDOExample.createCriteria();
                            messageCriteria.andUuidEqualTo(userMessageJoin.get(0).getUuid());
                            EmessageDO messageDO= userMessageJoin.get(0);
                            messageDO.setChangeTermStatus(changeTermStatus);
                            messageDO.setLastUpdateTime(new Date());
                            emessageDOMapper.updateByExampleSelective(messageDO,emessageDOExample);
                        }
                    });
                }
            });
        }
    }


    private void dealWith60Between30(List<EUserDTO> userFor60Between30) {
        if(!collectionIsNull(userFor60Between30)) {
            userFor60Between30.forEach(user->{
                List<EuserMessageJoinDO> euserMessageJoinDOS = euserMessageDOMapper.findUserMessageJoinByIsCT(user.getPhone());
                if(!collectionIsNull(euserMessageJoinDOS)) {
                    //更新换届消息状态
                    EmessageDOExample emessageDOExample = new EmessageDOExample();
                    EmessageDOExample.Criteria messageCriteria = emessageDOExample.createCriteria();
                    messageCriteria.andUuidEqualTo(euserMessageJoinDOS.get(0).getUuid());
                    EmessageDO messageDO= euserMessageJoinDOS.get(0);
                    messageDO.setChangeTermStatus(ElectionConstants.CHANGE_TERM_STATUS_1);
                    messageDO.setLastUpdateTime(new Date());
                    emessageDOMapper.updateByExampleSelective(messageDO,emessageDOExample);
                }
            });
        }
    }

    public void dealWithNotIn369 (List<EUserDTO> userDTOS) {
        if(!collectionIsNull(userDTOS)) {
            userDTOS.forEach(user->{
                List<EuserMessageJoinDO> euserMessageJoinDOS = euserMessageDOMapper.findUserMessageJoinByIsCT(user.getPhone());
                if(!collectionIsNull(euserMessageJoinDOS)) {
                    //更新换届消息状态
                    EmessageDOExample emessageDOExample = new EmessageDOExample();
                    EmessageDOExample.Criteria messageCriteria = emessageDOExample.createCriteria();
                    messageCriteria.andUuidEqualTo(euserMessageJoinDOS.get(0).getUuid());
                    EmessageDO messageDO= euserMessageJoinDOS.get(0);
                    messageDO.setChangeTermStatus(ElectionConstants.CHANGE_TERM_STATUS_3);
                    messageDO.setLastUpdateTime(new Date());
                    emessageDOMapper.updateByExampleSelective(messageDO,emessageDOExample);
                }
            });
        }
    }

    private void dealWith36And12(List<EUserDTO> userFor30BetweenNow, int changeTermStatus) {
        if(!collectionIsNull(userFor30BetweenNow)) {
            userFor30BetweenNow.forEach(user->{
                List<EuserMessageJoinDO> euserMessageJoinDOS = euserMessageDOMapper.findUserMessageJoinByIsCT(user.getPhone());
                if(!collectionIsNull(euserMessageJoinDOS)) {
                    //更新换届消息状态
                    EmessageDOExample emessageDOExample = new EmessageDOExample();
                    EmessageDOExample.Criteria messageCriteria = emessageDOExample.createCriteria();
                    messageCriteria.andUuidEqualTo(euserMessageJoinDOS.get(0).getUuid());
                    EmessageDO messageDO= euserMessageJoinDOS.get(0);
                    messageDO.setChangeTermStatus(changeTermStatus);
                    messageDO.setLastUpdateTime(new Date());
                    emessageDOMapper.updateByExampleSelective(messageDO,emessageDOExample);
                }
            });
        }
    }
    /**
     * 获取90需换届的用户
     * @return
     */
    private Map<String,List<EUserDTO>> getChangeTermUser() {
        EuserDOExample euserDOExample = new EuserDOExample();
        EuserDOExample.Criteria criteria = euserDOExample.createCriteria();
        criteria.andChangeTermDateIsNotNull();
        criteria.andChangeTermDateLessThanOrEqualTo(getBeforeDays(90));
        criteria.andChangeTermDateGreaterThanOrEqualTo(new Date());
        List<EuserDO> euserDOS = euserDOMapper.selectByExample(euserDOExample);
        if(collectionIsNull(euserDOS)) {
            return Collections.emptyMap();
        }
        Map<String,List<EUserDTO>> mother369Map = new HashMap<>();
        mother369Map.put("mother3",getUserForBetweenDaysAgo(euserDOS,getBeforeDays(0),getBeforeDays(30)));
        mother369Map.put("mother6",getUserForBetweenDaysAgo(euserDOS,getBeforeDays(30),getBeforeDays(60)));
        mother369Map.put("mother9",getUserForBetweenDaysAgo(euserDOS,getBeforeDays(60),getBeforeDays(90)));
        return mother369Map;
    }

    /**
     * 不在369个月之内的换届消息
     * @return
     */
    private List<EUserDTO> getChangeTermNotIn369User() {
        EuserDOExample euserDOExample = new EuserDOExample();
        EuserDOExample.Criteria criteria = euserDOExample.createCriteria();
        criteria.andChangeTermDateIsNotNull();
        criteria.andChangeTermDateGreaterThan(getBeforeDays(90));
        List<EuserDO> euserDOS = euserDOMapper.selectByExample(euserDOExample);
        if(collectionIsNull(euserDOS)) {
            return Collections.emptyList();
        }
        return euserDOS.stream().map(EuserConvert::do2dto).collect(Collectors.toList());
    }

    /**
     * 根据时间获取 3,6,9个月内的数据  >startDate <=endDate
     * @param euserDOS
     * @param startDate
     * @param endDate
     * @return
     */
    private List<EUserDTO> getUserForBetweenDaysAgo(List<EuserDO> euserDOS , Date startDate, Date endDate){
        if(startDate.before(new Date())){
            log.info("-----------" + startDate);
        }

        List<EuserDO> euserDOSForBetweenDay = euserDOS.stream().filter(user-> user.getChangeTermDate().equals(endDate)
                ||(startDate.before(user.getChangeTermDate()) && endDate.after(user.getChangeTermDate())))
                .collect(Collectors.toList());
        if(collectionIsNull(euserDOSForBetweenDay)) {
            return Collections.emptyList();
        }
        return euserDOSForBetweenDay.stream().map(EuserConvert::do2dto).collect(Collectors.toList());
    }

    /**
     * 获取当前时间days之前的日期
     * @param days
     * @return
     */
    private Date getBeforeDays(int days){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    private boolean collectionIsNull(Collection collection){
        return collection == null || collection.size() <=0;
    }
}
