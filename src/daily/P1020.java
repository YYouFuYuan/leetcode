package daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P1020 {

    public int ans = 0;
    public int[][] dirs = { {1,0},{-1,0},{0,1},{0,-1}};
    public void bfs(int startX,int startY,int m,int n,int[][] flag,int[][] grid){
        flag[startX][startY] = 1;
        ans--;
        Queue<int[] > queue = new LinkedList<>();
        queue.offer(new int[]{startX,startY});
        while (!queue.isEmpty()){
            int[] c = queue.poll();
            for(int i=0;i<4;i++){
                int nX = c[0] + dirs[i][0];
                int nY = c[1] + dirs[i][1];
                if(nX < 0 || nX >=m || nY < 0 || nY >=n || flag[nX][nY] == 1 || grid[nX][nY] == 0)
                    continue;
                ans--;
                queue.add(new int[]{nX,nY});
                flag[nX][nY] = 1;
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] flag = new int[m][n];
        //所有为1的数量
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1)
                    ans++;
            }
        }
        //从边缘为1的位置进行搜索
        for(int i=0;i<m;i++){
            if(grid[i][0] == 1 && flag[i][0] == 0){
                bfs(i,0,m,n,flag,grid);
            }
            if(grid[i][n-1] == 1 && flag[i][n-1] == 0){
                bfs(i,n-1,m,n,flag,grid);
            }
        }
        for(int i=1;i<n-1;i++){
            if(grid[0][i] == 1 && flag[0][i] == 0){
                bfs(0,i,m,n,flag,grid);
            }
            if(grid[m-1][i] == 1 && flag[m-1][i] == 0){
                bfs(m-1,i,m,n,flag,grid);
            }
        }
        int k = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)
            {
                if(flag[i][j] == 1)
                    k++;
            }
            System.out.println(Arrays.toString(flag[i]));
        }
        System.out.println(k);
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = { {0,0,0,1,1,1,0,1,0,0},
                        {1,1,0,0,0,1,0,1,1,1},
                        {0,0,0,1,1,1,0,1,0,0},
                        {0,1,1,0,0,0,1,0,1,0},
                        {0,1,1,1,1,1,0,0,1,0},
                        {0,0,1,0,1,1,1,1,0,1},
                        {0,1,1,0,0,0,1,1,1,1},
                        {0,0,1,0,0,1,0,1,0,1},
                        {1,0,1,0,1,1,0,0,0,0},
                        {0,0,0,0,1,1,0,0,0,1}};
        System.out.println(new P1020().numEnclaves(arr));
    }
}
