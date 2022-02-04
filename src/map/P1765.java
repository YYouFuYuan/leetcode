package map;

import java.util.LinkedList;
import java.util.Queue;

public class P1765 {

    public int[][] highestPeak(int[][] isWater) {
        Queue<int[]> queue = new LinkedList<>();
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] result = new int[m][n];
        int[][] flag = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(isWater[i][j] == 1){
                    queue.offer(new int[]{i,j});
                    flag[i][j] = 1;
                    result[i][j] = 0;
                }
            }
        }
        int[][] dirt = new int[][]{ {1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0;i<4;i++){
                int newX = cur[0] + dirt[i][0];
                int newY = cur[1] + dirt[i][1];
                if(newX>=0 && newX<m && newY>=0 && newY<n && flag[newX][newY]==0){
                    flag[newX][newY] = 1;
                    result[newX][newY] = result[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{newX,newY});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new P1765().highestPeak(new int[][]{{0,1},{0,0}});
    }
}
