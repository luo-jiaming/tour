package cn.edu.hlju.tour.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Sole on 2017/3/1.
 */
public class MD5Utils {

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String charset) {
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String charset) {
        String mysign = DigestUtils.md5Hex(getContentBytes(text, charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}
