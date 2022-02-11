package daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class P1984 {



    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i+k-1<n;i++){
            ans = Math.min(nums[i+k-1] - nums[i],ans);
        }
        return ans;
    }
}
