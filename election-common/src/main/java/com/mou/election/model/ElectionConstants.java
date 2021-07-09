package com.mou.election.model;

public class ElectionConstants {

    /**
     * 消息状态
     * 0  默认状态可展示
     * 1  不可用
     */
    public static final Integer MESSAGE_STATUS_Y = 0;
    public static final Integer MESSAGE_STATUS_N = 1;

    /**
     * 分页参数
     * PAGE_DEFAULT_INDEX 默认当前页 1
     * PAGE_DEFAULT_SIZE  默认页面值 10
     */
    public static final Integer PAGE_DEFAULT_INDEX = 1;
    public static final Integer PAGE_DEFAULT_SIZE = 10;

    public static final String IS_RECEIPT_Y = "Y";
    public static final String IS_RECEIPT_N = "N";
    public static final String IS_POPUP_Y = "Y";
    public static final String IS_POPUP_N = "N";
    public static final String IS_DETAILS_Y = "Y";
    public static final String IS_DETAILS_N = "N";
    public static final String IS_CHANGE_TERM_Y = "Y";
    public static final String IS_CHANGE_TERM_N = "N";
    //弹窗状态
    public static final Integer POPUP_STATUS_Y = 0;
    public static final Integer POPUP_STATUS_N = 1;
    //消息回执状态
    public static final Integer                                                                                                                                                                                  REPLY_STATUS_Y = 0;
    public static final Integer REPLY_STATUS_N = 1;
    /**
     * 选举时间状态
     * 0： 9-6个月内
     * 1： 6-3个月内
     * 2:  3-0个月内
     * 3： 12-9个月内
     */
    public static final Integer CHANGE_TERM_STATUS_0 = 0;
    public static final Integer CHANGE_TERM_STATUS_1 = 1;
    public static final Integer CHANGE_TERM_STATUS_2 = 2;
    public static final Integer CHANGE_TERM_STATUS_3 = 3;

}
