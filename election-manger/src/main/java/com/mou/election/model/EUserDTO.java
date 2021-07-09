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

    private List<String> roleCodes;

    public void putRoleCode(String roleCode) {
        if (this.roleCodes == null) {
            this.roleCodes = new ArrayList<>();
        }
        this.roleCodes.add(roleCode);
    }

    private List<String> permissionCodes;

    public void putpermissionCode(String permissionCode) {
        if (this.permissionCodes == null) {
            this.permissionCodes = new ArrayList<>();
        }
        this.permissionCodes.add(permissionCode);
    }

}
