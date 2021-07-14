package com.mou.election.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class EApplyRequest extends BaseRequest {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    private Long userId;


    private String delay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyElectionDate;


    private String applyRemark;

    private String feature;


    private String status;

    private Long organizationId;

    private List<Long> userIds;

}
