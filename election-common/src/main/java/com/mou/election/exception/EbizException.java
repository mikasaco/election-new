package com.mou.election.exception;

import com.mou.election.enums.ErrorCodeEnum;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *          业务异常
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class EbizException extends RuntimeException{

    private static final long serialVersionUID = -4730408625622687747L;

    private String errorCode;

    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public EbizException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public EbizException(ErrorCodeEnum errorCodeEnum){
        this.errorCode = errorCodeEnum.getErrorCode();
        this.errorMsg = errorCodeEnum.getErrorMessage();
    }
}
