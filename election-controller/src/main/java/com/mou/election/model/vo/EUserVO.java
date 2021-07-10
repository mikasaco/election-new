package com.mou.election.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Getter
@Setter
@ToString
public class EUserVO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String userName;

    private String phone;

    //private String password;

    private String openId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date changeTermDate;

    private String organization;

    private Long organizationId;

    private String post;

    private String status;

    private String feature;

    private List<EroleVO> roleVOS;

    private List<EPermissionVO> permissionVOS;


}
