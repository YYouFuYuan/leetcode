package map;

import java.util.LinkedList;
import java.util.Queue;

public class P1034 {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] flag = new int[m][n];
        int[][] result = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row,col});
        int oriColor = grid[row][col];
        if(row == 0 || row == m-1 || col == 0 || col == n-1 || grid[row-1][col] != oriColor
                || grid[row][col-1] != oriColor || grid[row+1][col] != oriColor || grid[row][col+1] != oriColor) {
            result[row][col] = color;
        }
        flag[row][col] = 1;
        int[][] dirt = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0;i<4;i++){
                int newX = cur[0] + dirt[i][0];
                int newY = cur[1] + dirt[i][1];
                if(newX >= 0 && newX < m && newY >=0 && newY < n && flag[newX][newY] == 0 && grid[newX][newY] == oriColor){
                    if(newX == 0 || newX == m-1 || newY == 0 || newY == n-1 || grid[newX-1][newY] != oriColor
                    || grid[newX][newY-1] != oriColor || grid[newX+1][newY] != oriColor || grid[newX][newY+1] != oriColor) {
                        result[newX][newY] = color;
                    }
                    flag[newX][newY] = 1;
                    queue.offer(new int[]{newX,newY});
                }
            }

        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(result[i][j] == 0){
                    result[i][j] = grid[i][j];
                }
            }
        }

        return result;
    }
}
