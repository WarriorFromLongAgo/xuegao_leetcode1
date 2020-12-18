package com.xuegao.剑指.剑指4;

/**
 * <br/> @PackageName：com.xuegao.剑指.剑指4
 * <br/> @ClassName：findNumberIn2DArray
 * <br/> @Description：二维数组中的查找
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:58
 */
public class FindNumberIn2DArray {
    public static void main(String[] args) {
        // int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int[][] matrix = {{}};
        int target = 5;
        boolean numberIn2DArray = findNumberIn2DArray(matrix, target);
        System.out.println(numberIn2DArray);
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int[] intArr2 : matrix) {
            if (intArr2.length < 1) {
                return false;
            }
            // 如果 第一个数 大于 查询值，直接
            if (intArr2[0] > target || intArr2[intArr2.length - 1] < target) {
                continue;
            }
            for (int i : intArr2) {
                if (target == i) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}