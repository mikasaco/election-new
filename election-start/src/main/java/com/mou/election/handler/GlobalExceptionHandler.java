package com.mou.election.handler;

import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    EResult handleException(Exception ex){
        log.error("system error",ex);
        return EResult.newErrorInstance(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(EbizException.class)
    EResult handleBizException(EbizException ebizException){
        return EResult.newErrorInstance(ebizException.getErrorCode(),ebizException.getErrorMsg());
    }


}
