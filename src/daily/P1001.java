package daily;

import java.util.*;

public class P1001 {

    //题目的n太大，10^9 暴力模拟肯定超时
    //将所有的问题进行简化
    //同行同列，对角线上只要1栈灯，全部变量，给行列及对角线取编号
    //行列简单，对角线可以用 x + y, x-y 为什么可以这样？
    //因为正反对角线，斜率固定，我们可以用经过灯坐标的直线与x轴的交点坐标来求截距，用截距来给对角线进行编号
    // 如正对角线 y = x + b ，它与x轴的截距 b = x + y
    // 反对角线  b = x - y
    //有了编号，在查询的时候，只要查询灯在上述4个线的编号内，即亮
    public int[][] dirs = new int[][]{{0,0},{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}}; //9个点
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int length = queries.length;
        int[] res = new int[length];
        Long N = Long.valueOf(n);
        Map<Integer,Integer> row = new HashMap<>(); //key为线编号，value为点亮次数
        Map<Integer,Integer> col = new HashMap<>();
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();
        Set<Long> set = new HashSet<>();    //保存有灯的一维坐标
        for(int[] lamp : lamps){
            int r = lamp[0];
            int c = lamp[1];
            Long point = r * N + c; //二维坐标转一维
            if(set.contains(point))
                continue;
            //更新4条线的亮灯次数
            int L = r + c;
            int R = r - c;
            row.put(r,row.getOrDefault(r,0) + 1);
            col.put(c,col.getOrDefault(c,0) + 1);
            left.put(L,left.getOrDefault(L,0) + 1);
            right.put(R,right.getOrDefault(R,0) + 1);
            set.add(point);
        }
        for(int i=0;i<length;i++){
            int[] q = queries[i];
            int x = q[0];
            int y = q[1];
            //如果查询坐标，在编号内即两
            if(row.containsKey(x) || col.containsKey(y) || left.containsKey(x + y) || right.containsKey(x - y))
                res[i] = 1;
            //灭灯，周围9个点都要灭
            for(int[] d : dirs){
                int newX = x + d[0];
                int newY = y + d[1];
                int newL = newX + newY;
                int newR = newX - newY;
                //周围点超出范围不管
                if(newX < 0 || newX >= N || newY < 0 || newY >= N)
                    continue;
                //有灯
                if(set.contains(newX * N + newY)){
                    set.remove(newX * N + newY); //先灭灯
                    //更新4条线的亮灯次数，灭掉光线
                    if(row.containsKey(newX)){
                        if(row.get(newX) == 1){
                            row.remove(newX);
                        }
                        else row.put(newX,row.get(newX) - 1);
                    }
                    if(col.containsKey(newY)){
                        if(col.get(newY) == 1){
                            col.remove(newY);
                        }
                        else col.put(newY,col.get(newY) - 1);
                    }
                    if(left.containsKey(newL)){
                        if(left.get(newL) == 1){
                            left.remove(newL);
                        }
                        else left.put(newL,left.get(newL) - 1);
                    }
                    if(right.containsKey(newR)){
                        if(right.get(newR) == 1){
                            right.remove(newR);
                        }
                        else right.put(newR,right.get(newR) - 1);
                    }

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new P1001().gridIllumination(5,new int[][]{ {0,0},{4,4}},new int[][]{{1,1},{1,0}});
    }
}
