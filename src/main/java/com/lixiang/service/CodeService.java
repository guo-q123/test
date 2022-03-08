package com.lixiang.service;

/**
 * @author 郭强
 * @version 1.0
 * @date 2022/3/1 21:36
 */
public class CodeService {
    /**
     * ALPHABET34 34进制编字符表
     */
    public static final String ALPHABET34 = "123456789abcdefghijkmnopqrstuvwxyz";

    /**
     * ALPHABET58 58进制字符表
     */
    public static final String ALPHABET58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static void main(String[] args) {
//        System.out.println(to10("aqz"));
//        System.out.println(to34(11253));
//        System.out.println(recurve(22));
        int n="abc".charAt(1);
        System.out.println(n);
    }

    public static Integer to10(String str) {
        char[] chars = str.toCharArray();
        int nums = 0;
        for (int i = chars.length - 1, j = 0; i >= 0; i--, j++) {
            int index = ALPHABET34.indexOf(chars[i]);
            nums += index * Math.pow(34, j);
        }
        return nums;
    }

    public static String to34(int num) {
        char[] chars = ALPHABET34.toCharArray();
        StringBuilder result = new StringBuilder();

        String number = num/34 +"";
        int remainder=num%34;
        String[] array = number.split("");
        for (int i = array.length-1; i >=0; i--) {
            result.append(chars[Integer.parseInt(array[i])]);
        }
        result.append(chars[remainder]);

        return result.toString();
    }

    public static int recurve(int num) {
        while (num / 34 >= 1) {
            num /= 34;
        }
        return num;
    }

    /**
     * 转换方法
     * @param num		元数据字符串
     * @param fromRadix	元数据的进制类型
     * @param toRadix	目标进制类型
     * @return
     */
//    static String transRadix(String num, int fromRadix, int toRadix) {
//        int number = Integer.valueOf(num, fromRadix);
//        StringBuilder sb = new StringBuilder();
//        while (number != 0) {
//            sb.append(chs[number%toRadix]);
//            number = number / toRadix;
//        }
//        return sb.reverse().toString();
//
//    }
}
