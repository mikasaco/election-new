package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class WxSendMessageDTO {

    private Set<String> toUser;

    private String templateId;

    private String page;

    private Map<String,String> data;

    private String miniprogramState;

    private String lang;
}
