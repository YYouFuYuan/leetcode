package daily;

import java.util.*;

public class P2100 {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        List<Integer> res = new ArrayList<>();
        if(time >= n){
            return res;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i=1;i<n;i++){
            if(security[i] <= security[i-1]){
                left[i] = left[i-1] + 1;
            }
        }
        for(int i=n-2;i>=0;i--){
            if(security[i] <= security[i+1]){
                right[i] = right[i+1] + 1;
            }
        }

        for(int i=time;i<n-time;i++){
            int leftIndex = left[i];
            int rightIndex = right[i];
            if(leftIndex >= time && rightIndex >= time){
                res.add(i);
            }
        }
        return res;
    }



    public static void main(String[] args) {
        List<Integer> res = new P2100().goodDaysToRobBank(new int[]{1},5);
        System.out.println(res);
    }
}
