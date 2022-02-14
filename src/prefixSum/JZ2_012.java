package prefixSum;

import java.util.Arrays;

public class JZ2_012 {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if(n==1)
            return 0;
        int[] sum1 = new int[n+1];
        for(int i=1;i<=n;i++){
            sum1[i] = sum1[i-1] + nums[i-1];
        }
        System.out.println(Arrays.toString(sum1));
        for(int i=0;i<n;i++){
            //sum[i] 是 num[0] + ... num[i-1]
            //sum[n] 是 num[0] + ... num[n-1]
            //sum[n] - sum[i] 是 num[i] + ... num[n-1]  这不是我们想要的，我们要num[i+1] ... num[n-1]
            //
            if(sum1[i] == sum1[n] - sum1[i+1])
                return i;
        }
        return -1;
    }
}
