package dp;

import java.util.ArrayList;
import java.util.List;
/*
    完全背包参考P322 比较标准例子，省去第三层循环
 */
public class P279 {

    /*
        完全背包，暴力求解
     */
    public int process(int curIndex,int rest,List<Integer> numSquare){
        if(rest == 0)
            return 0;
        if(rest <0 || curIndex >= numSquare.size())
            return -1;
        int res = rest;
        int num = numSquare.get(curIndex);
        for(int i=0;i * num <= rest;i++){
            int r = process(curIndex + 1,rest - i * num,numSquare);
            if(r != -1){
                res = Math.min(res,r + i);
            }
        }
        return res;

    }
    public int numSquares(int n) {
        //1.构造平方数
        List<Integer> numSquare = new ArrayList<>();
        for(int i=1;i * i <= n;i++){
            numSquare.add(i * i);
        }
        //2.暴力求解
        return process(0,n,numSquare);
    }


    public int numSquaresDP(int n){
        //1.构造平方数
        List<Integer> numSquare = new ArrayList<>();
        for(int i=1;i * i <= n;i++){
            numSquare.add(i * i);
        }
        //2.dp 两个变量curIndex [0,numSquare]
        //            rest [0,rest]
        int length = numSquare.size();
        int[][] dp = new int[length][n+1];
        //2.初始值
        for(int i=0;i<=n;i++){
            if(i % numSquare.get(0) ==0){
                dp[0][i] = i / numSquare.get(0);
            }
            else dp[0][i] = -1;
        }
        for(int i=0;i<length;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<length;i++){
            int num = numSquare.get(i);
            for(int j=1;j<=n;j++){
                int res = j;
                for(int k=0;k * num <= j;k++){
                    int r = dp[i - 1][j - k * num];
                    if(r != -1){
                        res = Math.min(res,r + k);
                    }
                }
                dp[i][j] = res;
            }
        }
        return dp[length-1][n];
    }
    public static void main(String[] args) {
        System.out.println(new P279().numSquaresDP(13));
    }
}
