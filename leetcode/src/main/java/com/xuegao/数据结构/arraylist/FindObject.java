package com.xuegao.数据结构.arraylist;

/**
 * <br/> @PackageName：com.xuegao.数据结构.arraylist
 * <br/> @ClassName：FindObject
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/31 17:45
 */
public class FindObject {

    public static void main(String[] args) {
        int[] intArr = {3, 4, 5, 6, 7};
        int index = findIndex(intArr, 4);
        System.out.println("index = " + index);
    }

    private static int findIndex(int[] intArr, int value) {
        for (int i = 0; i < intArr.length; i++) {
            if (value == intArr[i]) {
                return i;
            }
        }
        // -1 不存在
        return -1;
    }
}