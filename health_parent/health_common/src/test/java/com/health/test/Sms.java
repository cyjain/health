package com.health.test;

import com.aliyuncs.exceptions.ClientException;
import com.health.utils.SMSUtils;
import org.junit.Test;

public class Sms {
    @Test
    public void test1(){
        try {
            SMSUtils.sendShortMessage("SMS_180352616","15207158623","12345");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
