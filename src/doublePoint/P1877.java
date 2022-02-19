package doublePoint;

import java.util.Arrays;

public class P1877 {
    //贪心 + 排序
    //最小的数组和必定要是第k大 + 第k小的值之一
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while (left < right){
            int sum = nums[left] + nums[right];
            ans = Math.max(ans,sum);
            left++;
            right--;
        }
        return ans;
    }
}
