package com.mou.election.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplyTotalVO {

    private Integer totalApplyNum;

    private Integer processingApplyNum;

    private Integer finishApplyNum;
}
