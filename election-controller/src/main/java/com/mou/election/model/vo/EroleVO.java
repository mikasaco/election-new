package com.mou.election.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 沈林强(四笠) on 2021/7/4.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/4
 */
@Getter
@Setter
@ToString
public class EroleVO implements Serializable {
    private static final long serialVersionUID = -4875988650761906524L;
    private Long id;

    private Date gmtCreate;


    private Date gmtModified;

    private String roleCode;

    private String roleName;

    private List<EPermissionVO> permissionVOS;

    private String feature;

    public void putpermissionVO(EPermissionVO permissionCode){
        if(this.permissionVOS ==null){
            this.permissionVOS = new ArrayList<>();
        }
        this.permissionVOS.add(permissionCode);
    }

}
