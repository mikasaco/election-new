package com.mou.election.model;

import com.mou.election.enums.ErrorCodeEnum;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@ToString
public class EResult<T> implements Serializable {

    private static final long serialVersionUID = -6708082555472716616L;

    private boolean success;

    private String errorCode;

    private String errorMsg;

    private T data;

    /**
     * @return 返回正确的结果
     */
    public static <T> EResult<T> newSuccessInstance(T data) {
        EResult<T> result = new EResult<T>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * @return 返回失败的结果
     */
    public static <T> EResult<T> newErrorInstance(String errorCode, String errorMsg) {
        EResult<T> result = new EResult<T>();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    /**
     * @return 返回失败的结果
     */
    public static <T> EResult<T> newErrorInstance(ErrorCodeEnum errorCodeEnum) {
        EResult<T> result = new EResult<T>();
        result.setSuccess(false);
        result.setErrorCode(errorCodeEnum.getErrorCode());
        result.setErrorMsg(errorCodeEnum.getErrorMessage());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
