package test;

/**
 * Created by block on 16/3/23.
 */
public class EncryptUtils {
	public static final boolean DEBUG_MODE_WITHOUT_ENCRYPT = false;
	public static String pubilckey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh7+u5FKHwDc/FZq4LCpfsuwa8eqDzsl++Bz5Z9fPi2Tar3exoN8xBRsT/XFsDX17xBm8JojGyzMZ5aBAS2mQMogq/gpj3Yd2b1XtmEHBlNbIb09SHJHtx5ojlIHUG6nPIMW9KphtDtoZE24B/3XzzAXA3NgTLbuhC+nAtSz7xqQIDAQAB";
	public static String privatekey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAKHv67kUofANz8VmrgsKl+y7Brx6oPOyX74HPln18+LZNqvd7Gg3zEFGxP9cWwNfXvEGbwmiMbLMxnloEBLaZAyiCr+CmPdh3ZvVe2YQcGU1shvT1Icke3HmiOUgdQbqc8gxb0qmG0O2hkTbgH/dfPMBcDc2BMtu6EL6cC1LPvGpAgMBAAECgYEAjJN2Ekky+u1CfJ7SX4tmr378LgpWRreGHbS0D+xPN5JQv4n7e81UZyZERVmrOJZGGP34zmgatJjHgwUvTu5/AoayY1sHXIp7JmQzqa8qvZghTXoeNpnqXbj8sS6HEY5itE1mZOtwOE8HcykMZGaXodGZz1f7NysCyAZWpnJkEcECQQDTu9qYR3kdij89+VsCAT2x7qDrirraUPs0PNnvXbJ1WWgtikCBs6foKKprYs8nXYs2xfDfKn/Xdp8DkoAJsmGNAkEAw8rqiulMgmM7ntw+6rRI/3nNiyBzdsf06JI66SFD1nn94lUXTypOG5Ue0YdWhd3+wwkEb9LmyX2Ky/G5YfjTjQJBALDtzSBquT6CA47aC76FIvTInxe0eads/D0OjF6FQVbmOzOyz2ySn8BUGvRZQRl4BZjjlwAlF2cI7J+jj/KKaRECQQC+9LMX1D4olPvbDlfWtQrVEEilvnqeiJqWEbifEzCEh+pTykW3kj0nraKnHGYIneEQ+0R2g61PKsAp9JGnufUpAkEApR8F3F6Kf5NK78xJOS7cbZ1UzdiuABncl8Hnzkb+QaD1d/o3IBHFwqSwhWNvA8JjInZoWsetLUaKvznZezAi+Q==";

	// 公钥加密第二步
	public static String encrypt_rsa(String source) {
		EncryptUtils utils = new EncryptUtils();
		return utils.encrypt_RSA(source);
	}

	// 解码
	public static String decrypt_rsa(String source) {
		EncryptUtils utils = new EncryptUtils();
		return utils.decrypt_RSA(source);
	}

	// 公钥加密
	public static String encrypt_RSA(String source) {
		try {
			// String path = CommonUtil.fileUrl("keys");
			byte[] cipherData = RSAEncrypt
					.encrypt(RSAEncrypt.loadPublicKeyByStr(pubilckey),
							source.getBytes());
			return Base64.encode(cipherData);
		} catch (Exception ex) {
			System.out.println("RSA加密错误");
			return null;
		}
	}

	// 私钥解密过程
	public static String decrypt_RSA(String source) {
		try {
			// String path = CommonUtil.fileUrl("keys");
			// String relate =
			// this.getClass().getClassLoader().getResource("/").getPath();
			byte[] res = RSAEncrypt.decrypt(
					RSAEncrypt.loadPrivateKeyByStr(privatekey),
					Base64.decode(source));
			return new String(res);
		} catch (Exception ex) {
			System.out.println("RSA解密错误");
			return null;
		}
	}

}
