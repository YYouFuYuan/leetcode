package binarysearch;

import java.util.LinkedList;
import java.util.Queue;

public class P778 {

    public int swimInWater(int[][] grid) {
        int maxHeight = 0;
        int n = grid.length;
        for(int i = 0;i < n; i++ ){
            for(int j = 0; j< n; j++){
                maxHeight = Math.max(maxHeight,grid[i][j]);
            }
        }
        int left = 0;
        int right = maxHeight;
        int[][] dirt = new int[][]{ {1,0},{-1,0},{0,1},{0,-1}};
        while (left < right){
            int mid = left + ((right - left) >> 1);
            //bfs
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0,0});
            boolean curFlag = false;
            int[][] flag = new int[n][n];
            while (!queue.isEmpty()){
                int[] cur = queue.poll();
                if(cur[0] == n-1 && cur[1] == n-1){
                    //可以
                    curFlag = true;
                    break;
                }
                for(int i=0;i<4;i++){
                    int newX = cur[0] + dirt[i][0];
                    int newY = cur[1] + dirt[i][1];
                    if(newX >= 0  && newX < n && newY >=0 && newY < n && flag[newX][newY] == 0 && grid[newX][newY] <= mid && grid[cur[0]][cur[1]] <= mid){
                        queue.offer(new int[]{newX,newY});
                        flag[newX][newY] = 1;
                    }
                }
            }
            if(curFlag){
                right = mid;
            }
            else left = mid + 1;
        }
        return left >= maxHeight ? maxHeight : left;
    }
}
