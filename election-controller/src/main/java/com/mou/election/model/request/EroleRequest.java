package com.mou.election.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Getter
@Setter
@ToString
public class EroleRequest implements Serializable {

    private static final long serialVersionUID = 120556014684579765L;

    private Long id;

    private Date gmtCreate;


    private Date gmtModified;

    private String roleName;

    private String roleCode;

    private List<String> permissionCodes;

    private String feature;
}
