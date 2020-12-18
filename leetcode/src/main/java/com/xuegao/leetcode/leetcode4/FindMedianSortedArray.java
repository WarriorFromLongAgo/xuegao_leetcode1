package com.xuegao.leetcode.leetcode4;

/**
 * <br/> @PackageName：com.xuegao.leetcode.leetcode204
 * <br/> @ClassName：findMedianSortedArray
 * <br/> @Description：寻找两个正序数组的中位数
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:26
 */
public class FindMedianSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{3};
        int[] nums2 = new int[]{-2, -1};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums2 的 数组长度，一定小于 num1 的数组长度
        // if (nums1.length < nums2.length) {
        //     findMedianSortedArrays(nums2, nums1);
        // }
        if (nums1.length == 0) {
            if (nums2.length % 2 == 1) {
                return nums2[nums2.length / 2];
            } else {
                return (double) (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2;
            }
        }
        if (nums2.length == 0) {
            if (nums1.length % 2 == 1) {
                return nums1[nums1.length / 2];
            } else {
                return (double) (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2;
            }
        }

        // 作者：ookurumi
        // 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/4-xun-zhao-liang-ge-zheng-xu-shu-zu-de-zhong-we-54/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int nums1Index = 0;
        int nums2Index = 0;
        int sumLength = nums1.length + nums2.length;
        int left = -1;
        int right = -1;

        // 有序数组，所以只需要找一半就可以了
        for (int i = 0; i <= sumLength / 2; i++) {
            // 每次开始的时候，给上一个进行赋值
            left = right;
            // 先计算，较小的值，确保较小的值，已经计算过了
            if (nums1Index < nums1Length && (nums2Index >= nums2Length || nums1[nums1Index] < nums2[nums2Index])) {
                // 如果 nums1 中取出的值，小于nums2 中取出的值
                // 那么 将nums2 较小的值，拿出来，中位数进行赋值
                right = nums1[nums1Index];
                // 将较小的数组的 index ++
                nums1Index++;
            } else {
                // 如果 nums1 中取出的值，大于等于 nums2 中取出的值
                // 那么 将nums1 较大的值，拿出来，中位数进行赋值
                right = nums2[nums2Index];
                // 将较小的数组的 index ++
                nums2Index++;
            }
        }
        // 偶数
        if ((sumLength & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // nums2 的 数组长度，一定小于 num1 的数组长度
        // if (nums1.length < nums2.length) {
        //     findMedianSortedArrays(nums2, nums1);
        // }

        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int nums1Index = 0;
        int nums2Index = 0;
        int sumLength = nums1.length + nums2.length;
        int left = -1;
        int right = -1;

        // 有序数组，所以只需要找一半就可以了
        for (int i = 0; i <= sumLength / 2; i++) {
            // 每次开始的时候，给上一个进行赋值
            left = right;
            if (nums1Index < nums1Length && (nums2Index <= nums2Length || nums1[nums1Index] < nums2[nums2Index])) {
                right = nums1[nums1Index];
                nums1Index++;
            } else {
                right = nums2[nums2Index];
                nums2Index++;
            }
        }
        // 偶数
        if ((sumLength & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }
}