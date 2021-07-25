package com.mou.election.enums;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public enum ErrorCodeEnum {
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    TOKEN_NOT_EXIST("TOKEN_NOT_EXIST","请重新登录"),
    WEIXIN_NOT_BIND("WEIXIN_NOT_BIND","微信账号还未绑定"),
    USER_NOT_EXIST("USER_NOT_EXIST","用户不存在"),
    USER_EXIST("USER_EXIST","用户已存在"),
    PASSWORD_NOT_CORRECT("PASSWORD_NOT_CORRECT","密码不正确"),
    PARAM_ERROR("PARAM_ERROR","参数不合法"),
    ENUM_NOT_EXIST("ENUM_NOT_EXIST","枚举不存在"),
    TOKEN_OPEN_ID_FAILED("TOKEN_OPEN_ID_ERROR","获取微信凭证失败"),
    OPEN_ID_BIND("OPEN_ID_BIND","用户已绑定微信，不允许重复绑定"),
    OPEN_ID_BIND_OTHER_USER("OPEN_ID_BIND_OTHER_USER","该微信已绑定其他用户"),
    PROCESSIONG_APPLY_EXIST("PROCESSIONG_APPLY_EXIST","已存在审核中的换届申请，请先处理完后再提交新的申请")
    ;


    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
