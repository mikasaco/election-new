package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EApplyRequest extends BaseRequest {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    private Long userId;


    private String delay;


    private Date applyElectionDate;


    private String applyRemark;

    private String feature;


    private String status;

}
