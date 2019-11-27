package test;

public class RsaTest {

	String filePath = this.getClass().getClassLoader().getResource("/")
			.getPath()
			+ "keys";

	public static void main(String[] args) throws Exception {

		// String filepath = CommonUtil.fileUrl("keys");
		;
		// String relate =
		// this.getClass().getClassLoader().getResource("/").getPath();
		// String path = relate + "data/"+cate +".txt";
		// test.RSAEncrypt.genKeyPair(filepath);

		System.out.println("--------------公钥加密私钥解密过程-------------------");
		String plainText = "ceshi";
		// 公钥加密过程 ;
		String decrypt_rsa = EncryptUtils.encrypt_rsa(plainText);

		String encrypt_ = EncryptUtils.decrypt_rsa(decrypt_rsa);
		byte[] cipherData = RSAEncrypt.encrypt(
				RSAEncrypt.loadPublicKeyByStr(EncryptUtils.pubilckey),
				plainText.getBytes());
		String cipher = Base64.encode(cipherData);
		// 私钥解密过程
		byte[] res = RSAEncrypt.decrypt(
				RSAEncrypt.loadPrivateKeyByStr(EncryptUtils.privatekey),
				Base64.decode(cipher));
		String restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("zyj加密：" + decrypt_rsa);
		System.out.println("解密：" + restr);
		System.out.println();

		System.out.println("--------------私钥加密公钥解密过程-------------------");
		plainText = "12-13603584575";
		// 私钥加密过程
		cipherData = RSAEncrypt.encrypt(
				RSAEncrypt.loadPrivateKeyByStr(EncryptUtils.privatekey),
				plainText.getBytes());
		cipher = Base64.encode(cipherData);
		// 公钥解密过程
		res = RSAEncrypt.decrypt(
				RSAEncrypt.loadPublicKeyByStr(EncryptUtils.pubilckey),
				Base64.decode(cipher));
		restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("解密：" + restr);
		System.out.println();

		System.out.println("---------------私钥签名过程------------------");
		String content = "ihep_这是用于签名的原始数据";
		String signstr = RSASignature.sign(content, EncryptUtils.privatekey);
		System.out.println("签名原串：" + content);
		System.out.println("签名串：" + signstr);
		System.out.println();

		System.out.println("---------------公钥校验签名------------------");
		System.out.println("签名原串：" + content);
		System.out.println("签名串：" + signstr);

		System.out.println("验签结果："
				+ RSASignature
						.doCheck(content, signstr, EncryptUtils.pubilckey));
		System.out.println();

	}
}
