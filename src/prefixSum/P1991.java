package prefixSum;

import java.util.Arrays;

public class P1991 {
    public int findMiddleIndex(int[] nums) {
        //数组求和
        int total = Arrays.stream(nums).sum();
        int sum = 0;    //当前元素之前的累加和
        for(int i=0;i<nums.length;i++){
            //累加和的两倍 + 中间元素 = 总和
            //意味着左边的累加和 = 右边的累加和
            if(2 * sum + nums[i] == total)
                return i;
            sum += nums[i];
        }
        return -1;
    }
}
