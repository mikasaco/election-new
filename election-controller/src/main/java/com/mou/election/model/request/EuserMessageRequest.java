package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class EuserMessageRequest implements Serializable {
    private String messageId;

    private String userAttr;

    private Integer readStatus;

    private Integer messageReplyStatus;

    private Integer popupStatus;
}
