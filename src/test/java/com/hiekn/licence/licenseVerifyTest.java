package com.hiekn.licence;

import com.hiekn.licence.util.ListNets;
import com.hiekn.licence.verify.VerifyLicense;

import java.net.SocketException;

public class licenseVerifyTest {
    public static void main(String[] args){
        VerifyLicense vLicense = new VerifyLicense("lic-verify.properties");
        vLicense.verify();

        String ip = "192.168.1.119";
        String mac = "44-8A-5B-2B-8F-76";
        try {
            boolean ipMacFlag = ListNets.validatorIpAndMacAddress(ip, mac);
            boolean macFlag = ListNets.validateMacAddress(mac);
            System.out.println("mac is "+macFlag);
            System.out.println("ip and mac is "+ipMacFlag);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
