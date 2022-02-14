package prefixSum;

import java.util.Arrays;
import java.util.Comparator;

public class P1893 {
    //排序 nlogn
    public boolean isCoveredNLOGN(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for(int[] range : ranges){
            if(range[0] <= left){
                if(range[1] >= left){
                    left = Math.min(range[1],right) + 1;
                }
            }
            else return false;
            if(left > right)
                return true;
        }
        return left > right;
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        //题目数值[0,50]，但由于差分数组需要i+1的位置
        int[] diff = new int[52];
        for(int[] range : ranges){
            diff[range[0]]++;
            diff[range[1]]--;
        }
        int sum = 0;
        for(int i=1;i<=50;i++){
            sum += diff[i];
            if(i>= left && i<=right && sum <= 0)
                return false;
        }
        return true;
    }
}
