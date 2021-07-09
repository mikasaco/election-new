package com.mou.election.enums;

import com.mou.election.exception.EbizException;

public enum ApplyDelayEnum {

    PUNCTUAL("PUNCTUAL","如期"),

    DELAY("DELAY","延期");


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

    ApplyDelayEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ApplyDelayEnum getApplyDelayByCode(String code) {
        ApplyDelayEnum[] values = ApplyDelayEnum.values();
        for (ApplyDelayEnum value : values) {
            if(value.code == code){
                return value;
            }
        }
        throw new EbizException(ErrorCodeEnum.ENUM_NOT_EXIST);
    }
}
