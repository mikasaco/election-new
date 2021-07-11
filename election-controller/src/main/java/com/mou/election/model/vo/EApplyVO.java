package com.mou.election.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EApplyVO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    private Long userId;


    private String delay;

    private String delayDesc;

    private String statusDesc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyElectionDate;


    private String applyRemark;

    private String feature;


    private String status;

    private EUserVO userVO;

}
