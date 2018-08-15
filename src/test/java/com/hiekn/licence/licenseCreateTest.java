package com.hiekn.licence;

import com.hiekn.licence.create.CreateLicense;

public class licenseCreateTest {
    public static void main(String[] args){
        CreateLicense cLicense = new CreateLicense();
        //获取参数
        cLicense.setParam("lic-create.properties");
        //生成证书
        cLicense.create();
    }
}
