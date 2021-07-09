package com.mou.election.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class EroleDTO implements Serializable {

    private static final long serialVersionUID = -2047829356677327606L;
    private Long id;


    private Date gmtCreate;


    private Date gmtModified;


    private String roleName;

    private String roleCode;


    private String feature;

    private List<EPermissionDTO> permissionDTOList;

    public void putPermission(EPermissionDTO epermissionDTO){
        if(this.permissionDTOList == null){
            permissionDTOList = new ArrayList<>();
        }
        permissionDTOList.add(epermissionDTO);
    }


}
