package com.mou.election.dal.domian;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EuserMessageJoinDO extends EmessageDO{
    private String userAttr;

    private Integer messageReplyStatus;

    private Integer popupStatus;

    private Integer readStatus;
}
