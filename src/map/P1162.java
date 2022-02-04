package map;

import java.util.LinkedList;
import java.util.Queue;

public class P1162 {

    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m][n];
        int[][] flag = new int[m][n];
        int[][] dirt = new int[][]{ {1,0},{-1,0},{0,1},{0,-1} };
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    queue.offer(new int[]{i,j});
                    flag[i][j] = 1;
                }
            }
        }
        int ans = -1;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0;i<4;i++){
                int newX = cur[0] + dirt[i][0];
                int newY = cur[0] + dirt[i][1];
                if(newX < m && newX >= 0 && newY < n && newY >=0 && flag[newX][newY] == 0){
                    flag[newX][newY] = 1;
                    result[newX][newY] = result[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{newX,newY});
                    ans = Math.max(ans,result[newX][newY]);
                }
            }
        }
        return ans;

    }

}
