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
     * a-A方法
     */
    public int getPublicKey() {
        return (int) (Math.pow(DH_G, mPriKey) % DH_P);
    }

    /**
     * B-s方法
     */
    public int getPrivateKey(long publicKey) {
        return (int) (Math.pow(publicKey, mPriKey) % DH_P);
    }

    public byte[] getPrivateKeyByte(long publicKey) {
        return sha256((int) (Math.pow(publicKey, mPriKey) % DH_P));
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
