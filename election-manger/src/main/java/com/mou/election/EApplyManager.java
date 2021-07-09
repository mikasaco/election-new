package com.mou.election;

import com.mou.election.convert.EapplyConvert;
import com.mou.election.dal.domian.EApplyDO;
import com.mou.election.dal.domian.EApplyDOExample;
import com.mou.election.dal.mapper.EApplyDOMapper;
import com.mou.election.model.EApplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 沈林强(四笠) on 2021/7/8.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/8
 */
@Component
public class EApplyManager {

    @Autowired
    private EApplyDOMapper applyDOMapper;

    public void add(EApplyDTO applyDTO) {
        EApplyDO applyDO = EapplyConvert.dto2do(applyDTO);
        applyDO.setGmtCreate(new Date());
        applyDO.setGmtModified(new Date());
        applyDOMapper.insert(applyDO);
    }

    public void update(EApplyDTO applyDTO) {
        EApplyDO applyDO = EapplyConvert.dto2do(applyDTO);
        applyDO.setGmtModified(new Date());
        applyDOMapper.updateByPrimaryKeySelective(applyDO);
    }

    public void delete(Long id) {
        applyDOMapper.deleteByPrimaryKey(id);
    }

    public EApplyDTO get(EApplyDTO getDTO) {
        EApplyDO applyDO = applyDOMapper.selectByPrimaryKey(getDTO.getId());
        return EapplyConvert.do2dto(applyDO);
    }

    public List<EApplyDTO> query(EApplyDTO queryDTO) {
        EApplyDOExample example = new EApplyDOExample();
        EApplyDOExample.Criteria criteria = example.createCriteria();
        if (queryDTO.getStatus() != null) {
            criteria.andStatusEqualTo(queryDTO.getStatus());
        }
        if (queryDTO.getDelay() != null) {
            criteria.andDelayEqualTo(queryDTO.getDelay());
        }
        List<EApplyDO> eApplyDOS = applyDOMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(eApplyDOS)){
            return null;
        }
        return eApplyDOS.stream().map(EapplyConvert::do2dto).collect(Collectors.toList());
    }

}
