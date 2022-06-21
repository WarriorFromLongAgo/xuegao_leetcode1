package com.xuegao.数据结构与算法.其他算法.循环小数;

public class 循环小数 {
    public static void main(String[] args) {
        String inputStr = "3";



        System.out.println(1d / 3d);
        System.out.println(1d % 3d);
        System.out.println(1d / 7d);
        System.out.println(1d % 7d);

        System.out.println(1d / 6d);
        System.out.println(1d % 6d);
    }

    public String 小数转分数(String inputStr) {
        int input = Integer.parseInt(inputStr);
        // 目前不支持小于0
        if (input < 0) {
            throw new RuntimeException("不可以小于0");
        }
        if (input == 0) {
            return "";
        }


        return "000000";
    }
}
