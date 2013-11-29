package com.rocky.qq.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * QQ加密算法
 */
public class QQEncrypt {
    /**
     * 计算登录时密码HASH值
     *
     * @param uin
     * @param plain
     * @param verify
     * @return
     */
    public static String encrypt(long uin, String plain, String verify) {
        byte[] data = concat(md5(plain.getBytes()), long2bytes(uin));
        String code = byte2HexString(md5(data));
        data = md5((code + verify.toUpperCase()).getBytes());
        return byte2HexString(data);
    }

    private static byte[] concat(byte[] bytes1, byte[] bytes2) {
        byte[] big = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, big, 0, bytes1.length);
        System.arraycopy(bytes2, 0, big, bytes1.length, bytes2.length);
        return big;
    }

    /**
     * 计算一个字节数组的Md5值
     *
     * @param bytes
     * @return
     */
    private static byte[] md5(byte[] bytes) {
        MessageDigest dist = null;
        byte[] result = null;
        try {
            dist = MessageDigest.getInstance("MD5");
            result = dist.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    /**
     * 把字节数组转换为16进制表示的字符串
     *
     * @param b
     * @return
     */
    private static String byte2HexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        char[] hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        if (b == null)
            return "null";

        int offset = 0;
        int len = b.length;

        // 检查索引范围
        int end = offset + len;
        if (end > b.length)
            end = b.length;

        sb.delete(0, sb.length());
        for (int i = offset; i < end; i++) {
            sb.append(hex[(b[i] & 0xF0) >>> 4]).append(hex[b[i] & 0xF]);
        }
        return sb.toString();
    }

    /**
     * 把整形数转换为字节数组
     *
     * @param i
     * @return
     */
    public static byte[] long2bytes(long i) {
        byte[] b = new byte[8];
        for (int m = 0; m < 8; m++, i >>= 8) {
            b[7 - m] = (byte) (i & 0x000000FF); // 奇怪, 在C# 整型数是低字节在前 byte[]
            // bytes =
            // BitConverter.GetBytes(i);
            // 而在JAVA里，是高字节在前
        }
        return b;
    }
}
