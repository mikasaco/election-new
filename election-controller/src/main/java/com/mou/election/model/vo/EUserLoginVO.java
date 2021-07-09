package com.mou.election.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
@Getter
@Setter
@ToString
public class EUserLoginVO {

    private EUserVO userVO;

    private String token;
}
