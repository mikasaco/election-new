package com.mou.election.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.WxAppletsManager;
import com.mou.election.constants.EConstants;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.WxSendMessageDTO;
import com.mou.election.service.WxTemplateService;
import com.mou.election.utils.EStringUtils;
import com.mou.election.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WxTemplateServiceImpl implements WxTemplateService {
    @Autowired
    private WxAppletsManager wxAppletsManager;

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

    /**
     * 发送模板消息
     * @param wxSendMessageDTO
     * @return
     */
    public boolean sendWxMessage(WxSendMessageDTO wxSendMessageDTO) {
        String url = EConstants.TEMPLATE_MESSAGE_SEND+"?access_token=" + wxAppletsManager.getWxAccessToken();
        wxSendMessageDTO.getToUser().stream().forEach(user->{
            String json = EStringUtils.spliceWxSendTemplate(user,wxSendMessageDTO.getTemplateId(),"index",wxSendMessageDTO.getData());
            try {
                HttpClientUtils.doPostJson(url, json);
                log.info("微信订阅消息template:"+wxSendMessageDTO.getTemplateId() + "---openId:" + user + "发送成功");
            } catch (Exception e) {
                throw new EbizException("send_wx_template_error","发送微信订阅消息失败template:"+wxSendMessageDTO.getTemplateId() + "---openId:" + user );
            }
        });
        return true;
    }
}
