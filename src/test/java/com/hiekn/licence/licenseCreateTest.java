package com.hiekn.licence;

import com.hiekn.licence.create.CreateLicense;

public class licenseCreateTest {
    public static void main(String[] args){
        CreateLicense cLicense = new CreateLicense("lic-create.properties");
        cLicense.create();
    }
}
