package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class P322 {
    /*
        暴力递归
     */
    public int processRe(int curIndex,int rest,int[] coins){
        if(rest == 0)
            return 0;
        if(curIndex < 0 || rest < 0){
            return -1;
        }
        //不使用当前curIndex的硬币来凑成rest
        int res = Integer.MAX_VALUE;
        int r1 = processRe(curIndex - 1,rest,coins);
        if(r1 != -1){
            res = Math.min(res,r1);
        }
        //使用当前的硬币来凑数
        if(rest >= coins[curIndex]){
            int r2 =  processRe(curIndex,rest - coins[curIndex],coins);
            if(r2 != -1){
                res = Math.min(r2 + 1,res);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int coinChangeRe(int[] coins, int amount) {
        return processRe(coins.length-1,amount,coins);
    }


    static class Point{
        public int curIndex;
        public int rest;
        public Point(int curIndex,int rest){
            this.curIndex = curIndex;
            this.rest = rest;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return curIndex == point.curIndex && rest == point.rest;
        }

        @Override
        public int hashCode() {
            return Objects.hash(curIndex, rest);
        }
    }
    /*
        记忆化搜索
     */
    public int processMemory(int curIndex, int rest, int[] coins, Map<Point,Integer> map){
        if(rest == 0)
            return 0;
        if(curIndex < 0 || rest < 0){
            return -1;
        }
        if(map.containsKey(new Point(curIndex,rest))){
            return map.get(new Point(curIndex,rest));
        }
        //不使用当前curIndex的硬币来凑成rest
        int res = Integer.MAX_VALUE;
        int r1 = processMemory(curIndex - 1,rest,coins,map);
        if(r1 != -1){
            res = Math.min(res,r1);
        }
        //使用当前的硬币来凑数
        if(rest >= coins[curIndex]){
            int r2 =  processMemory(curIndex,rest - coins[curIndex],coins,map);
            if(r2 != -1){
                res = Math.min(r2 + 1,res);
            }
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        map.put(new Point(curIndex,rest),res);
        return res;
    }

    public int coinChangeMemory(int[] coins, int amount) {
        Map<Point,Integer> map = new HashMap<>();
        return processMemory(coins.length-1,amount,coins,map);
    }


    public int coinChange2DP(int[] coins, int amount) {
        int length = coins.length;
        int[][] dp = new int[length][amount + 1];
        for(int i=0;i<length;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<=amount;i++){
            if(i % coins[0] == 0){
                dp[0][i] = i / coins[0];
            }
            else dp[0][i] = -1;
        }

        for(int i=1;i<length;i++){
            for(int j=1;j<=amount;j++){
                int res = Integer.MAX_VALUE;
                //不选当前硬币
                int r1 = dp[i - 1][j];
                if(r1 != -1){
                    res = r1;
                }
                //使用当前的硬币来凑数
                if(j >= coins[i]){
                    int r2 =  dp[i][j-coins[i]];
                    if(r2 != -1){
                        res = Math.min(r2 + 1,res);
                    }
                }
                res = res == Integer.MAX_VALUE ? -1 : res;
                dp[i][j] = res;
            }
        }

        return dp[length-1][amount];
    }
}
