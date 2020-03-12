package com.zyj.crypto;

public class Test {
    private static final String PUB_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC29IaQyMvpdQbJePEz/aphREwE\n" +
                    "QrMgyuqwf3rk0BEu1iA8IsxGZY0EIugXjgWjt3P4MwESJyDfsoHUkYx8F3M3zqhN\n" +
                    "dLtp2LS9U68OzgHFW1lpEnN8/omCTXEL/Rr6MHxi5Sb5G8MTJc6T44evobXRZDK2\n" +
                    "ZgdJLN450uFbztrc3wIDAQAB";

    private static final String PRI_KEY =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALb0hpDIy+l1Bsl4\n" +
                    "8TP9qmFETARCsyDK6rB/euTQES7WIDwizEZljQQi6BeOBaO3c/gzARInIN+ygdSR\n" +
                    "jHwXczfOqE10u2nYtL1Trw7OAcVbWWkSc3z+iYJNcQv9GvowfGLlJvkbwxMlzpPj\n" +
                    "h6+htdFkMrZmB0ks3jnS4VvO2tzfAgMBAAECgYBApVRrElhi7HPyeqaX3Vj3t384\n" +
                    "viy3OJwGs3TEJvT7XLPVK+KMOVPDk2X49LjvaWIz4CnPtT27bULMzoUaT9ro0qeX\n" +
                    "bbeLXNaX8D1/OTHP+6R47SX+UL2WcklLhIATju8fYOYjj0rEW00IaCoO6HoqUOLV\n" +
                    "kmUGnTXEDL0h6G+I+QJBAO1duQvxhAdJVLhrCmO5P5tEGiJaAHZbCz1Esj21LHaS\n" +
                    "WCn+QQWscmPQuZtLXVQ3qT8axmbbtzdDWlgvXayafF0CQQDFUVKErj+Uh32ftHlv\n" +
                    "eo4oezqZ1qdtnhLzY5KEZtpqzvFWa6E4Rx5zuqns8EMyGQc4/aTJiTHSn3Jk7+S2\n" +
                    "TkprAkAW3lK3rdsUgKIi6l0j4nMYWGVULeuhe4AHtRifDVdtTQglc5N8InMa3r8j\n" +
                    "EQ260WoC5Gd8/WoXbuvDVzzlJjUZAkBsNeYAN6NMrGV7gTkbpuVxU+tWVL7rQcZ4\n" +
                    "zgGbNODRtH3r/AilWXNc2mC4PSdMwScR3SBTGjdFoAXXTyxpwlPTAkEAhz57S2Z4\n" +
                    "7QgLmYdsxMJujY7FnEFafTbHr+v/oaGRzfFq5+xg08PyryUVFCvK6JX93MX0P/6l\n" +
                    "Q4mnmEp9MWkWlw==";

    public static void main(String[] args) {
//        testRsa();
//        testAES();
        testDH();
    }

    public static void testRsa() {
        int content = 123456;
        String encrypted = RSA.encrypt(content, PUB_KEY);
        System.out.println("源数据：" + content);
        System.out.println("加密后数据：" + encrypted);
        System.out.println("解密后数据：" + RSA.decrypt(encrypted, PRI_KEY));
    }

    public static void testAES() {
        AES aes = new AES();
        String content = "this is aes";
        System.out.println("源数据：" + content);
        byte[] encrypted = aes.encrypt(content);
        System.out.println("加密后数据：" + new String(encrypted));
        byte[] decrypted = aes.decrypt(encrypted);
        System.out.println("解密后数据：" + new String(decrypted));
    }

    public static void testDH() {
        DH dhClient = new DH();
        DH dhServer = new DH();
        int pubKeyClient = dhClient.getPublicKey();
        int pubKeyServer = dhServer.getPublicKey();
        //
        int priKeyClient = dhClient.getPrivateKey(pubKeyServer);
        int priKeyServer = dhServer.getPrivateKey(pubKeyClient);
        System.out.println("priKeyClient" + priKeyClient);
        System.out.println("priKeyServer" + priKeyServer);
        //
        byte[] priKeyClientByte = dhClient.getPrivateKeyByte(pubKeyServer);
        byte[] priKeyServerByte = dhServer.getPrivateKeyByte(pubKeyClient);
        System.out.println("priKeyClientByte" + new String(priKeyClientByte));
        System.out.println("priKeyServerByte" + new String(priKeyServerByte));
    }
}
