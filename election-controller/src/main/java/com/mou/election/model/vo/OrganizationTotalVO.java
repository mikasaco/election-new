package com.mou.election.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrganizationTotalVO {

    private Integer organizationNum;

    private Integer userNum;

    private Integer applyUserNum;

    private Integer notApplyNum;

    private Integer examNum;

}
