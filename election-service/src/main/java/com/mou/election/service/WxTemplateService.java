package com.mou.election.service;

import com.mou.election.model.WxSendMessageDTO;

import java.util.List;

public interface WxTemplateService {
    List getTemplateList();

    boolean sendWxMessage(WxSendMessageDTO wxSendMessageDTO);

    WxSendMessageDTO getUserManagerAuditMessage();

    WxSendMessageDTO getOrgMessageNotifyByMonth(long orgId,String thing2);
}
