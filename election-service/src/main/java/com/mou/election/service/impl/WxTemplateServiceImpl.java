package com.mou.election.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.EUserManager;
import com.mou.election.WxAppletsManager;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EUserDTO;
import com.mou.election.model.WxSendMessageDTO;
import com.mou.election.service.WxTemplateService;
import com.mou.election.utils.EStringUtils;
import com.mou.election.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WxTemplateServiceImpl implements WxTemplateService {
    @Autowired
    private WxAppletsManager wxAppletsManager;
    @Autowired
    private EUserManager eUserManager;

    /**
     * 获取微信订阅消息模板
     */
    public List getTemplateList() {
        Map<String, String> params = new HashMap<>(4);
        params.put("access_token", wxAppletsManager.getWxAccessToken());
        String httpResult = HttpClientUtils.doGet(EConstants.GET_TEMPLATE_LIST, params);
        try {
            if (StringUtil.isNotEmpty(httpResult)) {
                Map map = JSON.parseObject(httpResult, Map.class);
                return (List) map.get("data");
            }
        } catch (Exception e) {
            throw new EbizException(ErrorCodeEnum.TOKEN_OPEN_ID_FAILED);
        }
        return null;
    }

    public WxSendMessageDTO getUserManagerAuditMessage() {
        List<EUserDTO> eUserDTOS = eUserManager.getUserManagerAll();
        WxSendMessageDTO wxSendMessageDTO = new WxSendMessageDTO();
        eUserDTOS = eUserDTOS.stream().filter(user-> !StringUtils.isEmpty(user.getOpenId())).collect(Collectors.toList());
        Set<String> toUsers = new HashSet<>();
        for (EUserDTO eUserDTO : eUserDTOS) {
            toUsers.add(eUserDTO.getOpenId());
        }
        wxSendMessageDTO.setToUser(toUsers);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> messageData = new HashMap();
        messageData.put("thing2","您有一条换届新的申请待审批");
        messageData.put("time3",sdf.format(new Date()));
        messageData.put("thing4","审批提醒");
        wxSendMessageDTO.setData(messageData);
        wxSendMessageDTO.setTemplateId("cye_Q6gumn2rDD3wN4LiUiG_6ctHvxBHkXPdjGZ32No");
        wxSendMessageDTO.setPage("pages/index/index");
        return wxSendMessageDTO;
    }

    public WxSendMessageDTO getOrgMessageNotifyByMonth(long orgId,String thing2) {
        List<EUserDTO> eUserDTOS = eUserManager.getUserManagerAll();
        WxSendMessageDTO wxSendMessageDTO = new WxSendMessageDTO();
        eUserDTOS = eUserDTOS.stream().filter(user-> !StringUtils.isEmpty(user.getOpenId())).collect(Collectors.toList());
        Set<String> toUsers = new HashSet<>();
        for (EUserDTO eUserDTO : eUserDTOS) {
            toUsers.add(eUserDTO.getOpenId());
        }
        List<EUserDTO> userDTOS = eUserManager.getUserByOrgId(orgId);
        userDTOS = userDTOS.stream().filter(user-> !StringUtils.isEmpty(user.getOpenId())).collect(Collectors.toList());
        for (EUserDTO eUserDTO : userDTOS) {
            toUsers.add(eUserDTO.getOpenId());
        }
        wxSendMessageDTO.setToUser(toUsers);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> messageData = new HashMap();
        messageData.put("thing2",thing2);
        messageData.put("time3",sdf.format(new Date()));
        messageData.put("thing4","换届提醒");
        wxSendMessageDTO.setData(messageData);
        wxSendMessageDTO.setTemplateId("cye_Q6gumn2rDD3wN4LiUiG_6ctHvxBHkXPdjGZ32No");
        wxSendMessageDTO.setPage("pages/index/index");
        return wxSendMessageDTO;
    }

    /**
     * 发送模板消息
     * @param wxSendMessageDTO
     * @return
     */
    public boolean sendWxMessage(WxSendMessageDTO wxSendMessageDTO) {
        String url = EConstants.TEMPLATE_MESSAGE_SEND+"?access_token=" + wxAppletsManager.getWxAccessToken();
        wxSendMessageDTO.getToUser().stream().forEach(user->{
            String json = EStringUtils.spliceWxSendTemplate(user,wxSendMessageDTO.getTemplateId(),"index",
                    wxSendMessageDTO.getData(),wxSendMessageDTO.getMiniprogramState(),wxSendMessageDTO.getLang());
            log.info("json---" + json);
            try {
                log.info("消息推送wx返回----" + HttpClientUtils.doPostJson(url, json));
                log.info("微信订阅消息template:"+wxSendMessageDTO.getTemplateId() + "---openId:" + user + "发送成功");
            } catch (Exception e) {
                throw new EbizException("send_wx_template_error","发送微信订阅消息失败template:"+wxSendMessageDTO.getTemplateId() + "---openId:" + user );
            }
        });
        return true;
    }

    public static void main(String[] args) {
        Map<String,String> messageData = new HashMap();
        messageData.put("thing2","测试");
        messageData.put("time3","2018-01-01");
        messageData.put("thing4","测试");
        String json = EStringUtils.spliceWxSendTemplate("oWPu-5XzGEhQq9rOEsPXfdQNRxwM",
                "cye_Q6gumn2rDD3wN4LiUiG_6ctHvxBHkXPdjGZ32No","index",messageData,"","");
        log.info("json---" + json);
        WxAppletsManager wxAppletsManager = new WxAppletsManager();
        //String token = wxAppletsManager.getWxAccessToken();
        String token = "50_l_gtQdsLfW10GpXUz3PLuoCJaBp_LbBomTfSLkkUrqxyLRvFcUrViVhzCx_mIktuYoXWGVqCvRdhsJDJ_27-iePXtONwVPDP6-D36B7VuyjHso7DETxPfBr232d1GXBLyRyNAbQEaojgivdPEYOhABAWQG";
        HttpClientUtils.doPostJson("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+token, json);
    }
}
