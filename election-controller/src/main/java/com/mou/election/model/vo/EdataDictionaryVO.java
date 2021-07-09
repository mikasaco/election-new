package com.mou.election.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Getter
@Setter
public class EdataDictionaryVO implements Serializable {

    private static final long serialVersionUID = 4151586170531783774L;

    @ExcelProperty(value = "字段1")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @ExcelProperty(value = "字段2")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    @ExcelProperty(value = "字段3")
    private String dataType;
    @ExcelProperty(value = "字段4")
    private String dataCode;
    @ExcelProperty(value = "字段5")
    private String dataDesc;
    @ExcelProperty(value = "字段6")
    private String dataFeature;
}
