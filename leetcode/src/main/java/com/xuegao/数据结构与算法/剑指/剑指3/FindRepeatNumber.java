package com.xuegao.数据结构与算法.剑指.剑指3;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指3
 * <br/> @ClassName：FindRepeatNumber
 * <br/> @Description：找出数组中重复的数字。
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:37
 */
public class FindRepeatNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println(repeatNumber);
    }

    public static int findRepeatNumber(int[] nums) {
        int[] boolIntArr = new int[nums.length];

        for (int num : nums) {
            if (boolIntArr[num] == 1) {
                return num;
            } else {
                boolIntArr[num] = 1;
            }
        }
        return 0;
    }

    // 原地置换，时间空间100%
    public static int findRepeatNumber2(int[] nums) {


        return 0;
    }
}