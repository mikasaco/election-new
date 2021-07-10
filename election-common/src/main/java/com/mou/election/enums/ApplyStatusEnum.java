package com.mou.election.enums;

import com.mou.election.exception.EbizException;

public enum ApplyStatusEnum {
    PROCESSING("PROCESSING", "审核中"),

    PASS("PASS", "通过"),

    REJECTED("REJECTED", "拒绝");


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

    ApplyStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ApplyStatusEnum getApplyStatusByCode(String code) {
        ApplyStatusEnum[] values = ApplyStatusEnum.values();
        for (ApplyStatusEnum value : values) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new EbizException(ErrorCodeEnum.ENUM_NOT_EXIST);
    }
}
