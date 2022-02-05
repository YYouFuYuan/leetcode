package daily;

import java.util.LinkedList;
import java.util.Queue;

public class P1219 {

    public int res = 0;
    //尝试暴力，对每个黄金点进行dfs搜索,多源dfs+回溯
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] != 0){
                    int[][] visited = new int[m][n];
                    visited[i][j] = 1;
                    dfs(visited,i,j,grid,m,n,grid[i][j]);
                }
            }
        }
        return res;
    }
    public int[][] dirt = new int[][]{ {1,0},{-1,0},{0,1},{0,-1}};
    private void dfs(int[][] visited, int i, int j, int[][] grid,int m,int n,int cur) {
        res = Math.max(cur,res);
        //System.out.println(i + "," + j);
        for(int k=0;k<4;k++) {
            int cX = i + dirt[k][0];
            int cY = j + dirt[k][1];
            if (cX < 0 || cX >= m || cY < 0 || cY >= n || visited[cX][cY] == 1 || grid[cX][cY] == 0) {
                continue;
            }
            cur += grid[cX][cY];
            visited[cX][cY] = 1;
            dfs(visited,cX,cY,grid,m,n,cur);
            visited[cX][cY] = 0;
            cur -= grid[cX][cY];
        }

    }


}
