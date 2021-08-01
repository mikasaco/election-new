package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by 沈林强(四笠) on 2021/7/9.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/9
 */
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {

    private Integer currentPageNo;

    private Integer pageSize;

    private String keyWord;

}
