package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class EmessageRequest implements Serializable {

    private String uuid;

    private String head;

    private String desct;

    private String details;

    private String isDetails;

    private String isPopup;

    private String isReceipt;
    //推送给那些用户 格式： 所有人|部门|用户
    private String pushUsers;

}
