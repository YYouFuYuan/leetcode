package map;

import java.util.*;

public class P909 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int count = 1;
        int flag = 0;
        //map存储捷径
        for(int r=n-1;r>=0;r--){
            if(flag == 0){  //矩阵是蛇形的 flag反转
                for(int c=0;c<n;c++){
                    map.put(count,board[r][c]);
                    count++;
                }
                flag = 1;
            }
            else {
                for(int c=n-1;c>=0;c--){
                    map.put(count,board[r][c]);
                    count++;
                }
                flag = 0;
            }
        }
        Set<Integer> status = new HashSet<>();  //统计走过的格子，不重复走
        int step = 0;   //步数
        Queue<Integer> queue = new LinkedList<>();  //bfs
        queue.offer(1); //起点
        status.add(1);
        while (!queue.isEmpty()){
            step++;        //bfs，走1层，step+1
            int size = queue.size();    //当前层的操作全部枚举
            for(int i=0;i<size;i++){
                int prev = queue.poll();
                for(int k=1;k<=6;k++){  //6个骰子情况
                    int cur = prev + k;
                    if(cur == n * n){   //走到终点
                        return step;
                    }
                    else if(cur > n * n){ //超过的就不管
                        continue;
                    }
                    else {
                        if(!status.contains(cur)){  //当前路径还没走过，走过的不管
                            status.add(cur);
                            if(map.get(cur) != -1){
                                cur = map.get(cur); //有捷径走捷径
                                if(cur == n * n){   //走到终点
                                    return step;
                                }else if(cur > n * n){ //超过的就不管
                                    continue;
                                }
                                //这里不需要再tatus.add(cur);
                                //因为捷径的地点是通过捷径而来。本身直接走到该点还没有出现过。
                            }
                            queue.add(cur);
                        }

                    }

                }
            }
        }

        return -1;
    }
}
