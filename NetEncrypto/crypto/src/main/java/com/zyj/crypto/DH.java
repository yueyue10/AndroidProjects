package com.zyj.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class DH {
    public static final int DH_P = 23;
    public static final int DH_G = 5;

    private int mPriKey;

    public DH() {
        Random random = new Random();
        mPriKey = random.nextInt(10);
        System.out.println("dh prikey is:" + mPriKey);
    }

    /**
     * a->A方法
     * 通过a生成明文A的方法
     */
    public int getPublicKey() {
        return (int) (Math.pow(DH_G, mPriKey) % DH_P);
    }

    /**
     * a+B->s方法
     * 通过自己的a和对方的明文B生成密钥s
     */
    public int getPrivateKey(long publicKey) {
        return (int) (Math.pow(publicKey, mPriKey) % DH_P);
    }

    /**
     * 方法同上
     */
    public byte[] getPrivateKeyByte(long publicKey) {
        int buf = (int) (Math.pow(publicKey, mPriKey) % DH_P);
        return sha256(buf);
    }

    public byte[] getPrivateKeyByte(byte[] dhPubKeyServer) {
        int key = DataUtils.byte2Int(dhPubKeyServer);
        int buf = (int) (Math.pow(key, mPriKey) % DH_P);
        return sha256(buf);
    }

    /**
     * 将产生的s做一个hash转换
     * 目的是转成byte[128] 类型，用作AES密钥
     */
    private byte[] sha256(int data) {
        byte[] digest = new byte[]{-1};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(DataUtils.int2Byte(data));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

}
