package com.ding.kotlin.util;

import android.os.Build;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtil {

    private static final String PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2UT30L2Dydkec1Z1sv2e7ABvKTYbjRJf4lqLHKkDJgOdHvvm1H/3u6pxm4zEmvjH/gaJ2CslOr+zzm/vPHjUvcYWw73i9oAi0b+MEhrFUe6jqRfbo8/yizHIWDbov+zUu+mVYbpdO+FtJj6KbAsaKKa9JOcpElX/Sqh2wMmgoK0BE4TorGPKJ3ZOISgnVNsC9fGBV3fnbkQUNIpM1OCjunixNdPMdaaODM1gdW8aBTWCM7llU7yCgDBksbLkDQTYqLKZkMrwgJ/BXW3H8/4533gqMeWLKdMZlITAtOlGAdaqXw/NEQnQGEIXCePFZBP2IatMdEJY4Hkm15Rstj7E2wIDAQAB";


    /**
     * RSA私钥解密
     *
     * @param text 解密字符串
     * @return 明文
     */
    public static String decrypt(String text) {
        //64位解码加密后的字符串
        byte[] inputByte = null;
        String outStr = "";
        try {
            byte[] decoded = new byte[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                inputByte = Base64.getMimeDecoder().decode(text.getBytes("UTF-8"));
                decoded = Base64.getDecoder().decode(PRIVATE_KEY);
            }else{

            }
            //base64编码的私钥
            PrivateKey priKey = KeyFactory.getInstance("RSA","BC").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            // 密文解码
            outStr = new String(cipher.doFinal(inputByte));
        }  catch (Exception e) {
            e.printStackTrace();
            outStr = null;
        }
        return outStr;
    }
}
