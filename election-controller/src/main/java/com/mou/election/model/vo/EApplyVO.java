package com.mou.election.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty(value = "创建时间")
    private Date gmtCreate;
    @ExcelProperty(value = "修改时间")
    private Date gmtModified;


    private Long userId;

    @ExcelProperty(value = "延期")
    private String delay;
    private String delayDesc;

    private String statusDesc;
    @ExcelProperty(value = "换届时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyElectionDate;

    @ExcelProperty(value = "备注")
    private String applyRemark;

    private String feature;

    @ExcelProperty(value = "换届状态")
    private String status;

    private EUserVO userVO;

}
