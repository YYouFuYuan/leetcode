package dp;

import java.util.Arrays;

public class P576 {
    /**
     * 暴力递归
     * @param m
     * @param n
     * @param maxMove
     * @param curRow
     * @param curCol
     * @return
     */
    public int process(int m,int n,int maxMove,int curRow,int curCol){
        if(curRow < 0 || curCol < 0 || curRow >= m || curCol >=n){
            return 1;
        }
        if(maxMove == 0)
            return 0;
        return process(m,n,maxMove - 1,curRow + 1,curCol)
                + process(m,n,maxMove - 1,curRow - 1,curCol)
                + process(m,n,maxMove - 1,curRow,curCol + 1)
                + process(m,n,maxMove - 1,curRow,curCol - 1);
    }
    public int findPathsRe(int m, int n, int maxMove, int startRow, int startColumn) {
        return process(m,n,maxMove,startRow,startColumn);
    }

    /**
     * 三维dp
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m+1][n+1][maxMove+1];
        //走出格子
        for(int i=0;i<=m;i++){
            for(int j=0;j<=maxMove;j++){
                dp[i][n][j] = 1;
            }
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=maxMove;j++){
                dp[m][i][j] = 1;
            }
        }
        for(int k=1;k<=maxMove;k++){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    dp[i][j][k] = dp[i+1][j][k-1] + dp[i][j+1][k-1];
                    dp[i][j][k] %= 1000000007;
                    if(i-1<0){
                        dp[i][j][k] += 1;
                        dp[i][j][k] %= 1000000007;
                    }
                    else {
                        dp[i][j][k] += dp[i-1][j][k-1];
                        dp[i][j][k] %= 1000000007;
                    }
                    if(j-1<0){
                        dp[i][j][k] += 1;
                    }
                    else {
                        dp[i][j][k] += dp[i][j-1][k-1];
                        dp[i][j][k] %= 1000000007;
                    }
                    dp[i][j][k] %= 1000000007;
                }
            }
        }
        return dp[startRow][startColumn][maxMove];
    }



    public static void main(String[] args) {
        new P576().findPaths(2,2,2,0,0);
    }
}
