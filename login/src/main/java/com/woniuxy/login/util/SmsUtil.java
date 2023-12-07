package com.woniuxy.login.util;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SmsUtil {
   public static void sendCode(String phone,String code){
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI5tFgWSyAHd5L46FZhhrM", "r4Tu73bCUljDQfwTUJbbmav0r6wmul");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");//请求阿里云哪里，默认不能改
        request.setSysVersion("2017-05-25");//版本号
        request.setSysAction("SendSms");//请求哪个方法
        request.setSysMethod(MethodType.POST); //提交方式，默认不能改
        //设置发送相关参数
        request.putQueryParameter("PhoneNumbers", phone);//设置要发送的【手机号】
        request.putQueryParameter("SignName", "卢鹏杰的博客");//申请阿里云短信服务的【签名名称】
        request.putQueryParameter("TemplateCode", "SMS_464070633");//申请阿里云短信服务的【模版中的 模版CODE】

        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getHttpResponse().isSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
