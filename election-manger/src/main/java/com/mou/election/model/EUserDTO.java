package com.mou.election.model;

import com.mou.election.enums.LoginTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
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
public class EUserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 4261822153033843520L;

    private Long id;

    private Date gmtCreate;


    private Date gmtModified;


    private String userName;


    private String phone;


    private String password;


    private String openId;

    private Date changeTermDate;

    private Long organizationId;

    private String post;

    private String status;

    private String feature;

    private LoginTypeEnum loginTypeEnum;

    private List<EroleDTO> roleDTOS;

    private String jsCode;


    public void putRoleDTO(EroleDTO roleDTO) {
        if (this.roleDTOS == null) {
            this.roleDTOS = new ArrayList<>();
        }
        this.roleDTOS.add(roleDTO);
    }

    private List<EPermissionDTO> permissionDTOS;

    public void putpermissionDTO(EPermissionDTO permissionDTO) {
        if (this.permissionDTOS == null) {
            this.permissionDTOS = new ArrayList<>();
        }
        this.permissionDTOS.add(permissionDTO);
    }

}
