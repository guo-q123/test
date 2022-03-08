package com.lixiang.util;

import java.util.function.Function;

/**
 * @author 郭强
 * @version 1.0
 * @date 2022/3/1 21:27
 */
public class CodeUtils {

    /** ALPHABET34 34进制编字符表 */
    public static final String ALPHABET34 = "123456789abcdefghijkmnopqrstuvwxyz";

    /** ALPHABET58 58进制字符表 */
    public static final String ALPHABET58  = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    /**
     * consistentShuffle 根据salt将字母表打乱
     *
     * @param String alphabet 字母表
     * @param String salt     用于打乱的盐
     * @return String         打乱后的字母表
     */
    public static String consistentShuffle(String alphabet, String salt) {
        if (salt.length() == 0) {
            return alphabet;
        }

        // Fisher–Yates洗牌算法变种：每次通过盐来选择一个数字交换到剩下的数字的最后一位。
        char[] shuffle = alphabet.toCharArray();
        for (int i = shuffle.length - 1, v = 0, p = 0; i >= 1; i--, v++) {
            v = v % salt.length();
            int integer = salt.charAt(v);
            p += integer;
            int j = (integer + v + p) % i;

            char tmp = shuffle[i];
            shuffle[i] = shuffle[j];
            shuffle[j] = tmp;
        }
        return new String(shuffle);
    }

    /**
     * encoderGenerator 编码器生成器
     *
     * @param String alphabet         字母表
     * @return Function<Long, String> 按给定字母表进行编码的编码器
     */
    public static Function<Long, String> encoderGenerator(String alphabet)  {
        int alphabetLen = alphabet.length();
        return num -> {
            String str = "";
            for (; num!=0; ) {
                str = alphabet.charAt((int) (num % alphabetLen)) + str;
                num = num / alphabetLen;
            }
            return str;
        };
    }

    /**
     * decoderGenerator 解码器生成器
     *
     * @param String alphabet         字母表
     * @return Function<String, Long> 按给定字母表进行解码的编码器
     */
    public static Function<String, Long> decoderGenerator(String alphabet) {
        int alphabetLen = alphabet.length();
        return str -> {
            Long num = 0L;
            int strLen = str.length();
            for (int i=0; i<strLen; i++) {
                int index = alphabet.indexOf(str.charAt(i));
                num = num + new Double(index * Math.pow(alphabetLen, strLen - 1 - i)).longValue();
            }
            return num;
        };
    }

    /**
     * b58Decoder 将58进制字符串解码为10进制数
     *
     * @param String input 待解码的58进制字符串
     * @param String salt  盐
     * @return Long        解码后的10进制数
     */
    public static Long b58Decoder(String input, String salt) {
        // TODO::输入字符串字符校验
        // ...

        return decoderGenerator(consistentShuffle(ALPHABET58, salt)).apply(input);
    }

    /**
     * b58Encoder 将10进制数编码为58进制字符串
     *
     * @param Loing num   待编码的10进制数
     * @param String salt 盐
     * @return String     编码后的58进制字符串
     */
    public static String b58Encoder(Long num, String salt) {
        return encoderGenerator(consistentShuffle(ALPHABET58, salt)).apply(num);
    }

    /**
     * b34Decoder 将34进制字符串解码为10进制数
     *
     * @param String input 待解码的34进制字符串
     * @param String salt  盐
     * @return Long        解码后的10进制数
     */
    public static Long b34Decoder(String input, String salt) {
        // TODO::输入字符串字符校验
        // ...

        return decoderGenerator(consistentShuffle(ALPHABET34, salt)).apply(input);
    }

    /**
     * b34Encoder 将10进制数编码为34进制字符串
     *
     * @param Long num    待编码的10进制数
     * @param String salt 盐
     * @return String     编码后的34进制字符串
     */
    public static String b34Encoder(Long num, String salt) {
        return encoderGenerator(consistentShuffle(ALPHABET34, salt)).apply(num);
    }

    /**
     * b34Decoder 将X进制字符串解码为10进制数
     *
     * @param String input    待解码的X进制字符串
     * @param String salt     盐
     * @param String alphabet x进制字母表
     * @return Long           解码后的10进制数
     */
    public static Long bXDecoder(String input, String salt, String alphabet) {
        // TODO::输入字符串字符校验
        // ...

        return decoderGenerator(consistentShuffle(alphabet, salt)).apply(input);
    }

    /**
     * bXEncoder 将10进制数编码为X进制字符串
     *
     * @param Long   num      待编码的10进制数
     * @param String salt     盐
     * @param String alphabet x进制字母表
     * @return String         编码后的X进制字符串
     */
    public static String bXEncoder(Long num, String salt, String alphabet) {
        return encoderGenerator(consistentShuffle(alphabet, salt)).apply(num);
    }
}
