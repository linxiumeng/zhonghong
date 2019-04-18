package org.springblade.common.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JiguangSmsUtils {

    @Autowired(required = false)
    private SMSClient smsClient;

    public boolean sendSMSCode(String phone, String code) {
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(phone)
                .setTempId(1)
                .addTempPara("code", code)
                .build();
        try {
            SendSMSResult res = smsClient.sendTemplateSMS(payload);
            System.out.println(res.toString());
            return true;
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            System.err.println(e.getStatus());
            System.err.println(e.getErrorCode());
            System.err.println(e.getErrorMessage());
            e.printStackTrace();
        }
        return false;
    }

}
