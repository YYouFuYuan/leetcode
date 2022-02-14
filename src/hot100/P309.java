package hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class P309 {
    public int process(int[] prices,int curIndex,int flag){
        if(curIndex >= prices.length)
            return 0;
        //对于每一天的操作如下
        //1.手里没有股票
        if(flag == -1){
            //1.1 买入股票
            int res1 = process(prices,curIndex+1,curIndex);
            //1.2 不买
            int res2 = process(prices,curIndex+1,-1);
            return Math.max(res2,res1);
        }
        else {
            //2.手里有股票
            //2。1卖出股票
            int res1 = prices[curIndex] - prices[flag] + process(prices,curIndex + 2,-1);
            //2.2不卖
            int res2 = process(prices,curIndex + 1,flag);
            return Math.max(res2,res1);
        }

    }
    static class Point{
        public int curIndex;
        public int flag;
        public Point(int curIndex,int flag){
            this.curIndex = curIndex;
            this.flag = flag;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return curIndex == point.curIndex && flag == point.flag;
        }

        @Override
        public int hashCode() {
            return Objects.hash(curIndex, flag);
        }
    }
    public int processMemory(int[] prices,int curIndex,int flag,Map<Point,Integer> map){
        if(curIndex >= prices.length)
            return 0;
        //对于每一天的操作如下
        //1.手里没有股票
        if(map.containsKey(new Point(curIndex,flag))){
            return map.get(new Point(curIndex,flag));
        }
        int res1 = 0;
        int res2 = 0;
        if(flag == -1){
            //1.1 买入股票
            res1 = processMemory(prices,curIndex+1,curIndex,map);
            //1.2 不买
            res2 = processMemory(prices,curIndex+1,-1,map);
        }
        else {
            //2.手里有股票
            //2。1卖出股票
            res1 = prices[curIndex] - prices[flag] + processMemory(prices,curIndex + 2,-1,map);
            //2.2不卖
            res2 = processMemory(prices,curIndex + 1,flag,map);
        }
        int r = Math.max(res2,res1);
        map.put(new Point(curIndex,flag),r);
        return r;

    }
    public int maxProfit(int[] prices) {
        Map<Point,Integer> map = new HashMap<>();
        int r = processMemory(prices,0,-1,map);
        return r;
    }

    public int maxProfitDP(int[] prices) {
        int length = prices.length;
        int[][] dp = new int[length + 2][length + 1];
        for(int i=length-1;i>=0;i--){
            for(int j=length;j>=0;j--){
                if(j == 0){
                    //1.1 买入股票
                    int res1 = dp[i+1][i+1];
                    //1.2 不买
                    int res2 = dp[i+1][0];
                    dp[i][j] = Math.max(res1,res2);
                }
                else {
                    //2。1卖出股票
                    int res1 = prices[i] - prices[j-1] + dp[i+2][0];
                    //2.2不卖
                    int res2 = dp[i+1][j];
                    dp[i][j] = Math.max(res1,res2);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        new P309().maxProfitDP(new int[]{1,2,3,0,2});
    }
}
