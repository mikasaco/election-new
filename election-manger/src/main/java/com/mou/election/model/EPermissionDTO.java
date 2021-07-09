package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by 沈林强(四笠) on 2021/7/7.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/7
 */
@Getter
@Setter
@ToString
public class EPermissionDTO extends BaseDTO{

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String permissionName;

    private String permissionCode;

    private String feature;
}
