package dp;

import java.util.Arrays;
import java.util.Comparator;

public class P1751 {




    public int maxValue(int[][] events, int k) {
        //按结束时间从小到大排序
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int n = events.length;
        //考虑n个会议选择k个所获得的最大价值
        int[][] dp = new int[n+1][k+1];

        for(int i=1;i<=n;i++){
            int[] ev = events[i-1];
            int start = ev[0];
            int end = ev[1];
            int value = ev[2];

            int last = 0;
            for(int j=i-1;j>=1;j--){
                int[] prev = events[j-1];
                if(prev[1] < start){
                    last = j;
                    break;
                }
            }
            //每个物品可以选或者不选，当前的i物品如果要选，得先找到不冲突的last
            for(int r=1;r<=k;r++){
                dp[i][r] = Math.max(dp[i-1][r],dp[last][r-1] + value);

            }
        }

        return dp[n][k];

    }
    public static void main(String[] args) {
        new P1751().maxValue(new int[][]{ {1,2,4},{3,4,3},{2,3,10}},2);
    }
}
