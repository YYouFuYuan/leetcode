package week;

import java.util.Arrays;


public class P6006 {
    public long minimumRemoval(int[] beans) {
        //从小到大进行排序
        Arrays.sort(beans);
        int n = beans.length;
        //s[i] 表示beans[0..i-1]的累加和
        //s[1] = beans[0]
        //s[2] = beans[0] + beans[1]
        long[] s = new long[n + 1];//前缀和
        for(int i=1;i<=n;i++){
            s[i] = beans[i-1] + s[i-1];
        }
        long ans = Long.MAX_VALUE;
        //枚举最后相等的糖果数，必定是beans[i]的其中一个
        for(int i=1; i<=n;i++){
            //前面的糖果全部移除
            long t = s[i-1];
            //后面的糖果需要统计与beans[i]的差 求和， s[n] - s[i]表示后面的框的和
            t += (s[n] - s[i]) - (long) beans[i - 1] * (n - i);
            ans = Math.min(ans,t);
        }
        return ans;

    }

    public static void main(String[] args) {
        long r = new P6006().minimumRemoval(new int[]{2,10,3,2});
        System.out.println(r);
    }
}
