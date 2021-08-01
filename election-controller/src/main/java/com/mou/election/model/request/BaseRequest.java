package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
@Getter
@Setter
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 1528370280267898529L;

    private Integer currentPageNo = 1;

    private Integer pageSize = 10;

    private String keyWord;

}
