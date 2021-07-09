package com.mou.election.enums;

import com.mou.election.exception.EbizException;

/**
 * Created by 沈林强(四笠) on 2021/7/6.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/6
 */
public enum PermissionEnum {
    /**
     * 审批权限
     */
    APPROVE("approve", "审批"),
    /**
     * 普通用户
     */
//    VIEW("view", "普通用户")
    ;

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PermissionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PermissionEnum getPermissionByCode(String code) {
        PermissionEnum[] values = PermissionEnum.values();
        for (PermissionEnum value : values) {
            if(value.code == code){
                return value;
            }
        }
        throw new EbizException(ErrorCodeEnum.PERMISSION_ENUM_NOT_EXIST);
    }
}
