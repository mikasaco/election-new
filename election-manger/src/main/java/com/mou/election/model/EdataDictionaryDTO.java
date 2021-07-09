package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class EdataDictionaryDTO implements Serializable {

    private static final long serialVersionUID = 8676097400204730080L;

    private Long id;


    private Date gmtCreate;


    private Date gmtModified;


    private String dataType;


    private String dataCode;


    private String dataDesc;


    private String dataFeature;

}
