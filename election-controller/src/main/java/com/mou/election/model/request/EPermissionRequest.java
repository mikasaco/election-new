package com.mou.election.model.request;

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
public class EPermissionRequest extends BaseRequest{

    private static final long serialVersionUID = -2678693083169224193L;
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String permissionName;

    private String permissionCode;

    private String feature;
}
