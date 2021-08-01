package com.mou.election.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EuserMessageJoinUserVO extends EUserVO {

    private String messageId;

    private Integer readStatus;

    private Integer messageReplyStatus;

    private Integer popupStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String orgName;

}
