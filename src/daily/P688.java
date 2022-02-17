package daily;

public class P688 {

    //n 太大会导致溢出
    public long getTotal(int n,int a){
        long total = 1;
        while (n != 0){
            if((n & 1) == 1){
                total *= a;
            }
            n >>= 1;
            a *= a;
        }
        return total;
    }

    public int[][] dirs = new int[][]{ {2,-1},{2,1},{-2,-1},{-2,1},
            {1,2},{1,-2},{-1,2},{-1,-2}};



    public int process(int n,int rest,int curRow,int curCol){
        if(curRow < 0 || curRow >= n || curCol < 0 || curCol >= n){
            return 0;
        }
        if(rest == 0)
            return 1;
        int res = 0;
        for(int i=0;i<dirs.length;i++){
            res += process(n,rest-1,curRow + dirs[i][0],curCol + dirs[i][1]);
        }
        return res;
    }

    //暴力，超时 + 数据溢出
    public double knightProbabilityRe(int n, int k, int row, int column) {
        long count = process(n,k,row,column);
        long total = getTotal(k,8);
        System.out.println(count);
        System.out.println(total);
        return 1.0 * count / total;
    }


    public double knightProbabilityDP(int n, int k, int row, int column) {

        //
        long[][][] dp = new long[n][n][k+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0] = 1;
            }
        }

        for(int i=1;i<=k;i++){
            for(int r=0;r<n;r++){
                for(int c=0;c<n;c++){
                    dp[r][c][i] = 0;

                    //为了防止统计方案数的时候溢出，每次走一步累加的时候都 / 8 直接得到当前步往下走存活的概率

                    for (int[] dir : dirs) {
                        int nR = r + dir[0];
                        int nC = c + dir[1];
                        if (nR < 0 || nR >= n || nC < 0 || nC >= n) {
                            dp[r][c][i] += 0;
                        } else dp[r][c][i] += (dp[nR][nC][i - 1] / 8);
                    }
                }
            }
        }

        return dp[row][column][k];
    }

    public static void main(String[] args) {
        new P688().getTotal(30,8);
    }
}
