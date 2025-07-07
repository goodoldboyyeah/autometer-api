package com.zoctan.api.util;



import com.zoctan.api.util.MD5;
import com.zoctan.api.util.AES;
import com.zoctan.api.util.Base64;
import com.zoctan.api.util.DES;

public class test {
    public String AutoMeterPreScriptFun() throws Exception {
        AutoMeter.GetRequestValue("creator", "Body");
        AutoMeter.caseid = new Long(23);
        try {
            String name = AutoMeter.GetRequestValue("creator", "Body") + AutoMeter.GetRequestValue("enviromentname", "Body");
            String Result = MD5.encrypt(name);
            AutoMeter.SetRequestValue("memo", "Body", Result);
            return Result;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}
