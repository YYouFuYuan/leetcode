package week;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P6010 {

    public long minimumTime(int[] time, int totalTrips) {
        int minValue = time[0];
        for(int t : time){
            minValue = Math.min(minValue,t);
        }
        long left = 1;
        long right = (long) minValue * totalTrips;
        while (left < right){
            long mid = left + ((right - left) >> 1);
            if(check(mid,time,totalTrips)){
                right = mid;
            }
            else left = mid + 1;
        }
        return left;
    }

    private boolean check(long mid, int[] time, int totalTrips) {
        long sum = 0;
        for(int t : time){
            sum += (mid / t);
        }
        return sum >= totalTrips;
    }

    public static void main(String[] args) {
        long ans = new P6010().minimumTime(new int[]{2},1);
        System.out.println(ans);
    }


}
