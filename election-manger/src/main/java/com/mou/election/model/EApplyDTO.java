package com.mou.election.model;

import com.mou.election.enums.ApplyDelayEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
@Getter
@Setter
@ToString
public class EApplyDTO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    private Long userId;


    private ApplyDelayEnum delay;


    private Date applyElectionDate;


    private String applyRemark;

    private String feature;


    private String status;

    private EUserDTO userDTO;
}
