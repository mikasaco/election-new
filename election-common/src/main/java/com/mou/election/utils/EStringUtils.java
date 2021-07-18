package com.mou.election.utils;

import java.util.HashMap;
import java.util.Map;

public class EStringUtils {

    public static String spliceWxSendTemplate(String toUser,String templateId,String page,Map<String,String> map ) {
        String dataValue = "";
        for(Map.Entry<String,String> entry : map.entrySet()){
            dataValue = dataValue + "\"" + entry.getKey() + "\"" + ": {" +
                    "\"" + "value" + "\":" + "\"" + entry.getValue() + "\"},";
        }
        return "{" +
                "\"" + "touser" + "\": " + "\"" + toUser + "\"," +
                "\"" + "template_id" + "\": " + "\"" + templateId + "\"," +
                "\"" + "page" + "\": " + "\"" + page + "\"," +
                "\"" + "data" + "\":" +
                "{" +
                dataValue.substring(0,dataValue.length()-1) +
                "}";
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("name01","某某");
        map.put("amount01","￥100");
        map.put("thing01","广州至北京");
        map.put("date01","2018-01-01");
        System.out.println(spliceWxSendTemplate("OPENID","TEMPLATE_ID","index",map));
    }

}
