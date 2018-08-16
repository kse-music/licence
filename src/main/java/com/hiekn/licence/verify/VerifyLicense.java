package com.hiekn.licence.verify;

import de.schlichtherle.license.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

/**
 * VerifyLicense
 * @author LL
 */
public class VerifyLicense {

    //common param
    private static String PUBLICALIAS = "";
    private static String STOREPWD = "";
    private static String SUBJECT = "";
    private static String licPath = "";
    private static String pubPath = "";

    private static LicenseManager licenseManager;

    public VerifyLicense(String propertiesPath) {
        // 获取参数
        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesPath);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PUBLICALIAS = prop.getProperty("PUBLICALIAS");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = prop.getProperty("licPath");
        pubPath = prop.getProperty("pubPath");

        install();
    }

    public void install(){
        /************** 证书使用者端执行 ******************/

        licenseManager = LicenseManagerHolder.getLicenseManager(initLicenseParams());
        // 安装证书
        try {
            licenseManager.install(new File(licPath));
            System.out.println("客户端安装证书成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端证书安装失败!");
        }
    }

    public boolean verify() {
        // 验证证书
        try {
            LicenseContent content = licenseManager.verify();
            Object extra = content.getExtra();
//            boolean macFlag = ListNets.validateMacAddress(extra.toString());
//            if(!macFlag){
//                throw new IllegalStateException("客户端证书验证失效!");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端证书验证失效!");
            return false;
        }
        return true;
    }

    // 返回验证证书需要的参数
    private static LicenseParam initLicenseParams() {
        Preferences preference = Preferences.userNodeForPackage(VerifyLicense.class);
        CipherParam cipherParam = new DefaultCipherParam(STOREPWD);
        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
}
