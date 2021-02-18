package com.xuegao.数据结构与算法.leetcode.leetcode1;

import java.util.*;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.leetcode.leetcode1
 * <br/> @ClassName：TwoSum
 * <br/> @Description：两数相加
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:21
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int[] ints = twoSum(nums, 6);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int i1 = target - num;
            if (map.containsKey(i1) && map.get(i1) != i) {
                return new int[]{i, map.get(i1)};
            }
        }
        return new int[0];
    }

    public static int[] twoSum2(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            int i1 = target - num;
            if (set.contains(i1)) {
                return new int[]{num, target - num};
            }
            set.add(num);
        }
        return new int[0];
    }
}