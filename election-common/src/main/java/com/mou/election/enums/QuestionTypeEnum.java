package com.mou.election.enums;

import com.mou.election.exception.EbizException;

public enum QuestionTypeEnum {
    ONE(0, "单选"),
    MORE(1, "多选");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    QuestionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static QuestionTypeEnum getQuestionTypeByCode(Integer code) {
        QuestionTypeEnum[] values = QuestionTypeEnum.values();
        for (QuestionTypeEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new EbizException(ErrorCodeEnum.ENUM_NOT_EXIST);
    }
}
