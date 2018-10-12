package com.hiekn.licence.verify;

import de.schlichtherle.license.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

/**
 * VerifyLicense
 * @author DH
 * @since 2018年8月16日14:55:44
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
//        Properties prop = new Properties();
//        InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesPath);
//        try {
//            prop.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PUBLICALIAS = prop.getProperty("PUBLICALIAS");
//        STOREPWD = prop.getProperty("STOREPWD");
//        SUBJECT = prop.getProperty("SUBJECT");
//        licPath = prop.getProperty("licPath");
//        pubPath = prop.getProperty("pubPath");

        PUBLICALIAS = "publiccert";
        STOREPWD = "adminpk123";
        SUBJECT = "KgMs";
        String path = new File("").getAbsolutePath();
        licPath = path+File.separator+"cert"+File.separator+"kg.lic";
        pubPath = path+File.separator+"cert"+File.separator+"publicCerts.store";
        System.out.println(licPath);
        System.out.println(pubPath);
        install();
    }

    private void install(){
        /************** 证书使用者端执行 ******************/
        Preferences preference = Preferences.userNodeForPackage(VerifyLicense.class);
        CipherParam cipherParam = new DefaultCipherParam(STOREPWD);
        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
        licenseManager = LicenseManagerHolder.getLicenseManager(licenseParams);
        try {
            // 安装证书
            licenseManager.install(new File(licPath));
        } catch (Exception e) {
            throw new IllegalStateException("licence install failed",e);
        }
    }

    public boolean verify() {
        // 验证证书
        try {
            LicenseContent content = licenseManager.verify();
            Object extra = content.getExtra();
//            boolean macFlag = ListNets.validateMacAddress(extra.toString());
//            if(!macFlag){
//                throw new IllegalStateException("Client certificate verification is invalid!");
//            }
        } catch (Exception e) {
            throw new IllegalStateException("Client certificate verification is invalid!",e);
        }
        return true;
    }

}
