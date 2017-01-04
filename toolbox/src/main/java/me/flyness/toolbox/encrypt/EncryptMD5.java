package me.flyness.toolbox.encrypt;

import java.security.MessageDigest;

/**
 * @author 李智涛
 *         使用MD5 进行字符串的加密
 */
public abstract class EncryptMD5 {

    /**
     * MD5 字符串加密方法
     *
     * @param inputText 输入的字符串
     * @return String 加密后的字符串
     * @name encode
     * @active
     * @state
     * @type String
     * @time 下午3:03:14
     * @exception/throws
     * @see
     */
    public static String encode(String inputText) {
        return encrypt(inputText, "md5");
    }

    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }
}