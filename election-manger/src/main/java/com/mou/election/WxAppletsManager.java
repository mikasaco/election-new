package com.mou.election;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.mou.election.constants.EConstants;
import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.utils.EDateUtils;
import com.mou.election.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class WxAppletsManager {
   @Autowired
   private EdataDictionaryManager edataDictionaryManager;

    /**
     * 获取微信接口调用凭证
     * @return
     */
    public String getWxAccessToken() {
        EdataDictionaryDO edataDictionaryDO = new EdataDictionaryDO();
        edataDictionaryDO.setDataType("wx");
        edataDictionaryDO.setDataCode("accessToken");
        List<EdataDictionaryDO> edataDictionaryDOList = edataDictionaryManager.queryByDataDict(edataDictionaryDO);

        if(edataDictionaryDOList.size() > 0 &&
                new Date().before(EDateUtils.getAfterField(edataDictionaryDOList.get(0).getGmtModified(),Calendar.SECOND,7000))){
            return edataDictionaryDOList.get(0).getDataDesc();
        }

        String accessToken =  doGetWxAccessTokenHttp();
        edataDictionaryDO.setDataDesc(accessToken);
        if(StringUtil.isEmpty(accessToken)){
            return null;
        }else if(edataDictionaryDOList==null || edataDictionaryDOList.size()<=0){
            edataDictionaryManager.add(edataDictionaryDO);
        }else{
            EdataDictionaryDO data = edataDictionaryDOList.get(0);
            data.setDataDesc(accessToken);
            edataDictionaryManager.updateByPrimaryKey(data);
        }
        return accessToken;
    }

    public String doGetWxAccessTokenHttp() {
        Map<String, String> params = new HashMap<>();
        params.put("appid", EConstants.WEIXIN_APP_ID);
        params.put("secret", EConstants.WEIXIN_SECRET);
        params.put("grant_type", "client_credential");
        String httpResult = HttpClientUtils.doGet(EConstants.ACCESS_TOKEN, params);
        try {
            if (StringUtil.isNotEmpty(httpResult)) {
                Map map = JSON.parseObject(httpResult, Map.class);
                return (String) map.get("access_token");
            }
        } catch (Exception e) {
            throw new EbizException(ErrorCodeEnum.TOKEN_OPEN_ID_FAILED);
        }
        return null;
    }

}
