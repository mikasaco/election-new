package com.mou.election.model;

import com.github.pagehelper.PageInfo;
import com.mou.election.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Getter
@Setter
@ToString
public class EPageResult<T> extends EResult<T> {

    /**
     * 当前页数，默认值1
     */
    private Integer currentPageNo = 1;

    /**
     * 页大小，默认值10
     */
    private Integer pageSize = 10;

    /**
     * 总条数
     */
    private Long totalItems;

    /**
     * 搜索关键字
     */
    private String keyWord;

    public static EPageResult newSuccessInstance(Long total, Object data) {
        EPageResult pageResult = new EPageResult();
        pageResult.setTotalItems(total);
        pageResult.setSuccess(true);
        pageResult.setData(data);
        return pageResult;
    }

    public static <T> EPageResult<T> newErrorInstance(String errorCode, String errorMsg) {
        EPageResult<T> result = new EPageResult<T>();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    /**
     * @return 返回失败的结果
     */
    public static <T> EPageResult<T> newErrorInstance(ErrorCodeEnum errorCodeEnum) {
        EPageResult<T> result = new EPageResult<T>();
        result.setSuccess(false);
        result.setErrorCode(errorCodeEnum.getErrorCode());
        result.setErrorMsg(errorCodeEnum.getErrorMessage());
        return result;
    }

}
