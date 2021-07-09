package com.mou.election.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EmessageVO {

    private String uuid;

    private String head;

    private String desct;

    private String details;

    private Date changeTermDate;

    private Integer changeTermStatus;

    private String isChangeTerm;

    private String isDetails;

    private String isPopup;

    private String isReceipt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
