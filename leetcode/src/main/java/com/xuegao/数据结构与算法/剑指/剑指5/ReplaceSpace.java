package com.xuegao.数据结构与算法.剑指.剑指5;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指5
 * <br/> @ClassName：ReplaceSpace
 * <br/> @Description：替换空格
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 18:30
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        String s = "We are happy.";
        String s1 = replaceSpace(s);
        System.out.println(s1);
    }

    public static String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (' ' == s.charAt(i)) {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}