package com.chinapay.cip.util;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

import com.chinapay.util.Base64;
/**
 * 
 */
public class EncryUtil {
	/**.
	 * 密钥算法
	 * */
	private static final String KEY_ALGORITHM = "DES"; // 定义
	/**.
	 * 加密解密算法/工作模式/填充方式.
	 * */
	private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	/**
	 * 
	 * @param mac mac
	 * @param key key
	 * @return String String
	 */
	public static String getEncryStr(String mac, String key) {
		String MD5Str = get32MD5(new String(Base64.encode(mac.getBytes())));
		String encryStr = getEncString(MD5Str, key);
		return encryStr;
	}

	/**.
	 * 根据参数生成KEY
	 * @param strKey strKey
	 * @return Key Key
	 */
	public static Key getKey(String strKey) {

		try {
//			KeyGenerator _generator = KeyGenerator.getInstance("DES");
//			_generator.init(new SecureRandom(strKey.getBytes()));
//			Key key = _generator.generateKey();
//			_generator = null;
//			return key;
			
			SecretKeySpec keySpec = null;
			DESKeySpec deskey = null;
			deskey = new DESKeySpec(strKey.getBytes("UTF-8"));
			keySpec = new SecretKeySpec(deskey.getKey(), KEY_ALGORITHM);
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**.
	 * 加密String明文输入,String密文输出
	 * @param strMing strMing
	 * @param key key
	 * @return String String
	 */
	public static String getEncString(String strMing, String key) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = getEncCode(byteMing, getKey(key));
			strMi = new String(Base64.encode(byteMi));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**.
	 * 解密 以String密文输入,String明文输出
	 * @param strMi strMi
	 * @param key key
	 * @return String String
	 */
	public static String getDesString(String strMi, String key) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = Base64.decode(strMi.toCharArray());
			byteMing =getDesCode(byteMi, getKey(key));
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**.
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS byteS
	 * @param key key
	 * @return byte byte
	 */
	private static byte[] getEncCode(byte[] byteS, Key key) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**.
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * @param byteD byteD
	 * @param key key
	 * @return byte byte
	 */
	private static byte[] getDesCode(byte[] byteD, Key key) {
		Cipher cipher;
		byte[] byteFina = null;
		try { 
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}
    /**.
     * 
     * 2012-8-6上午09:41:30 
     * @Title: get32MD5 
     * @Description: TODO  Md5加密字符串
     * @param s s
     * @return String String
     * @throws
     */
	public final static String get32MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * SHA-512摘要算法.
	 * @param plainText 摘要明文
	 * @return 摘要值（Base64编码）
	 */
	public static String getSha512(String plainText) {
		return DigestUtils.sha512Hex(plainText);
	}
	/**
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		String key = "fenglin12324coAQFEWEEGFG";
		System.out.println("密钥:" + key);
		String minwen = "fengye666666666马海测试3DES";
		System.out.println("明文:" + minwen);
		String strEnc = EncryUtil.getEncString(minwen,key);// 加密字符串,返回String的密文
		System.out.println("加密:" + strEnc);

		String strDes = EncryUtil.getDesString(strEnc,key);// 把String 类型的密文解密
		System.out.println("解密:" + strDes);
		
		System.out.println("SHA-512:" + EncryUtil.getSha512("hello world"));
	}
}
