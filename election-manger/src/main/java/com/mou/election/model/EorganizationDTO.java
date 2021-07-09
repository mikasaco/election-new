package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Getter
@Setter
@ToString
public class EorganizationDTO implements Serializable {

    private static final long serialVersionUID = 4632494208017857190L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String organizationName;

    private String feature;
}
