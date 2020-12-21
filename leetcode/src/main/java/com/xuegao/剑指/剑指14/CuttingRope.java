package com.xuegao.剑指.剑指14;

/**
 * <br/> @PackageName：com.xuegao.剑指.剑指14
 * <br/> @ClassName：CuttingRope
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 10:45
 */
public class CuttingRope {
    public static void main(String[] args) {
        int i = cuttingRope(9);
        System.out.println(i);
    }

    public static int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(2 * dp[i - 2], 3 * dp[i - 3]);
        }
        return dp[n];

    }

    public int cuttingRope2(int n) {
        int []dp =new int [n+1];
        dp[0]=0;
        dp[1]=0;
        for(int i =2; i <=n; i++){
            for(int j =1; j< i ;j++){
                dp[i]=Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));

            }
        }
        return dp[n];
    }
}