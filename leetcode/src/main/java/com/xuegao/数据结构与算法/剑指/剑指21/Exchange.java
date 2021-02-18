package com.xuegao.数据结构与算法.剑指.剑指21;

import java.util.Arrays;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指21
 * <br/> @ClassName：Exchange
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/25 23:24
 */
public class Exchange {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 4};
        int[] exchange = exchange(nums);
        System.out.println(Arrays.toString(exchange));
    }

    public static int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) != 0) {
                left++;
                continue;
            }
            if ((nums[right] & 1) != 1) {
                right--;
                continue;
            }
            int leftItem = nums[left];
            int rightItem = nums[right];
            nums[right] = leftItem;
            nums[left] = rightItem;
            left++;
            right--;
        }
        return nums;
    }

    public static int[] exchange2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 如果是偶数
            if ((nums[left] & 1) == 0) {


            }
            // 如果是奇数
            if ((nums[left] & 1) == 1) {

            }

        }
        return nums;
    }
}