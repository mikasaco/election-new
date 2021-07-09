package com.mou.election.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Getter
@Setter
@ToString
public class EUserReqeust extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 3721219084320732000L;

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private String userName;

    private String phone;

    private String password;

    private String openId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date changeTermDate;

    private Long organizationId;

    private String post;

    private String status;

    private String loginType;

    private List<String> roleCodes;
}
