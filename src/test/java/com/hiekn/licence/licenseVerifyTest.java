package com.hiekn.licence;

import com.hiekn.licence.verify.VerifyLicense;

public class licenseVerifyTest {
    public static void main(String[] args){
        VerifyLicense vLicense = new VerifyLicense();
        //获取参数
        vLicense.setParam("lic-verify.properties");
        //验证证书
        vLicense.verify();
    }
}
