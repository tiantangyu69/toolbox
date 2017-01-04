package me.flyness.toolbox.encrypt;

import java.security.MessageDigest;
/**
 * 
 * @author LIZHITAO
 * SHA加密类
 */
public class EncryptSHA {

	/**
	 * SHA加密方法
	 * @name encode
	 * @active
	 * @state
	 * @type String
	 * @time 下午3:04:43
	 * @exception/throws
	 * @see
	 * @since
	 * @param inputText 输入的字符串
	 * @return String 加密后的字符串
	 */
	public static String encode(String inputText) {
		return encrypt(inputText, "sha-1");
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