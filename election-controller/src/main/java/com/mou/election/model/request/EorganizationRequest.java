package com.mou.election.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Getter
@Setter
@ToString
public class EorganizationRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1357082285397931768L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String organizationName;

    private String feature;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date changeTermTime;
}
