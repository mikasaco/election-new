package com.mou.election.dal.domian;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EuserMessageJoinUserDO extends EuserDO{

    private String messageId;

    private Integer messageReplyStatus;

    private Integer popupStatus;

    private Integer readStatus;

    private String orgName;
}
