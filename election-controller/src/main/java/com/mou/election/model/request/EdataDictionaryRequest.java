package com.mou.election.model.request;

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
public class EdataDictionaryRequest implements Serializable {

    private static final long serialVersionUID = -462469232014652574L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String dataType;

    private String dataCode;

    private String dataDesc;

    private String featureKey;

    private List<String> featureValue;

    private String dataFeature;
}
