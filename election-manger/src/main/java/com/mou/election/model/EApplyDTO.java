package com.mou.election.model;

import com.mou.election.enums.ApplyDelayEnum;
import com.mou.election.enums.ApplyStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
@Getter
@Setter
@ToString
public class EApplyDTO extends BaseDTO{

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    private Long userId;


    private ApplyDelayEnum delay;


    private Date applyElectionDate;


    private String applyRemark;

    private String feature;

    private ApplyStatusEnum status;

    private EUserDTO userDTO;

    private Long organizationId;

    private List<Long> userIds;

    private Date delayElectionDate;

    private String sendStatus;
}
