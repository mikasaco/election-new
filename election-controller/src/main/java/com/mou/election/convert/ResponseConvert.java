package com.mou.election.convert;

import com.mou.election.model.*;
import com.mou.election.model.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 * DTO和VO的转换器
 * 在controller域完成和下层数据层的模型转化
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class ResponseConvert {

    public static EdataDictionaryVO dataDictionaryDTO2VO(EdataDictionaryDTO edataDictionaryDTO) {
        EdataDictionaryVO edataDictionaryVO = new EdataDictionaryVO();
        BeanUtils.copyProperties(edataDictionaryDTO, edataDictionaryVO);
        return edataDictionaryVO;
    }

    public static EUserVO userDTO2VO(EUserDTO userDTO) {
        EUserVO euserVO = new EUserVO();
        BeanUtils.copyProperties(userDTO, euserVO);
        return euserVO;
    }

    public static EmessageVO messageDTO2VO(EmessageDTO dto) {
        EmessageVO vo = new EmessageVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EuserMessageVO messageDTO2VO(EuserMessageDTO dto) {
        EuserMessageVO vo = new EuserMessageVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EorganizationVO organizationDTO2VO(EorganizationDTO dto) {
        EorganizationVO vo = new EorganizationVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static EroleVO roleDTO2VO(EroleDTO dto) {
        EroleVO vo = new EroleVO();
        BeanUtils.copyProperties(dto, vo);
        if(!CollectionUtils.isEmpty(dto.getPermissionDTOList())){
            List<EPermissionVO> epermissionVOS = dto.getPermissionDTOList().stream().map(ResponseConvert::permissionDTO2VO).collect(Collectors.toList());
            epermissionVOS.forEach(permissionVO->{
                vo.putpermissionCode(permissionVO.getPermissionCode());
            });
        }
        return vo;
    }

    public static EPermissionVO permissionDTO2VO(EPermissionDTO dto){
        EPermissionVO vo = new EPermissionVO();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }

    public static EuserMessageJoinVO messageDTO2VO(EuserMessageJoinDTO dto) {
        EuserMessageJoinVO vo = new EuserMessageJoinVO();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }

    public static EuserMessageJoinUserVO messageDTO2VO(EuserMessageJoinUserDTO dto) {
        EuserMessageJoinUserVO vo = new EuserMessageJoinUserVO();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }


}