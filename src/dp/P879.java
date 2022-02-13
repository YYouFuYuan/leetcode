package dp;

public class P879 {
    public int process(int n,int minProfit,int[] group,int[] profit,int curIndex,int curProfit){
        if(n < 0)
            return 0;
        if(curIndex == profit.length && curProfit < minProfit)
            return 0;
        if(curIndex == profit.length)
            return 1;
        //该index有两种可能，要么选，要么不选
        //选
        int res1 = process(n - group[curIndex],minProfit,group,profit,curIndex + 1,curProfit + profit[curIndex]);
        //不选
        int rest = process(n,minProfit,group,profit,curIndex + 1, curProfit);
        return res1 + rest;
    }
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        return process(n,minProfit,group,profit,0,0);
    }

    /**
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemesDP(int n, int minProfit, int[] group, int[] profit) {
        int length = group.length;
        //n [0,n]
        //index [0,length]
        //p [0,minProfit]
        long[][][] dp = new long[n+1][length + 1][minProfit + 1];
        for(int i=0;i<=n;i++){
            dp[i][length][minProfit] = 1;
        }
        for(int i=0;i<=n;i++){
            for(int index=length-1;index>=0;index--){
                for(int p=minProfit;p>=0;p--){
                    dp[i][index][p] = dp[i][index+1][p];
                    if(i - group[index] < 0){
                        continue;
                    }
                    else if(p + profit[index] > minProfit){
                        dp[i][index][p] += 1;
                    }
                    else dp[i][index][p] += dp[i-group[index]][index+1][p + profit[index]];
                }
            }
        }

        return (int) dp[n][0][0];
    }

    public static void main(String[] args) {
        new P879().profitableSchemesDP(10,5,new int[]{2,3,5},new int[]{6,7,8});
    }
}
