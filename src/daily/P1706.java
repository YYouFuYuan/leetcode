package daily;

public class P1706 {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        if(m == 1 && n == 1){
            return new int[]{-1};
        }
        for(int i=0;i<n;i++){   //最后一层的结果
            if(i == 0){
                dp[m-1][i] = grid[m-1][i] == -1 ? -1 : i+1 < n && grid[m-1][i+1] == 1 ? i+1 : -1;
            }
            else if(i == n-1){
                dp[m-1][i] = grid[m-1][i] == 1 ? -1 : i-1 >= 0 && grid[m-1][i-1] == -11 ? i-1 : -1;
            }
            else {
                if(grid[m-1][i] == 1){
                    dp[m-1][i] = grid[m-1][i+1] == 1 ? i+1 : -1;
                }
                else {
                    dp[m-1][i] = grid[m-1][i-1] == -1 ? i-1 : -1;
                }
            }
        }

        for(int i=m-2;i>=0;i--){    //上层依赖下层
            for(int j=0;j<n;j++){
                if(j == 0){
                    if(grid[i][j] == -1){
                        dp[i][j] = -1;
                    }
                    else {
                        dp[i][j] = j+1 < n && grid[i][j+1] == 1 ? dp[i+1][j+1] : -1;
                    }
                }
                else if(j == n-1){
                    if(grid[i][j] == 1){
                        dp[i][j] = -1;
                    }
                    else {
                        dp[i][j] = j-1>=0 && grid[i][j-1] == -1 ? dp[i+1][j-1] : -1;
                    }
                }
                else {
                    if(grid[i][j] == -1){
                        dp[i][j] = grid[i][j-1] == 1 ? -1 : dp[i+1][j-1];
                    }
                    else {
                        dp[i][j] = grid[i][j+1] == -1 ? -1 : dp[i+1][j+1];
                    }
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        new P1706().findBall(new int[][]{
                {1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}
        });
    }
}
