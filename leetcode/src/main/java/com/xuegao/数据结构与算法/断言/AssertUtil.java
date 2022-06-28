package com.xuegao.数据结构与算法.断言;

public class AssertUtil {

    public static void isTrue(boolean bool, String message) {
        if (!bool) {
            throw new RuntimeException(message);
        }
    }

    public static void isTrue(boolean bool) {
        isTrue(bool, "断言异常");
    }
}
