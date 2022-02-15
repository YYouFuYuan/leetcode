package prefixSum;

public class P1685 {
    //通过题意，需要对每个元素求
    // |num[i] - num[0]| + |num[i] - num[1]| + ... + |num[i] - num[i+1]| + num[i] - num[i+2]| + ...
    // 由于数组非递减可得
    // num[i] - num[i-1] >=0          num[i] - num[i+1] <=0
    // 去绝对值可得
    // num[i] - num[0] + num[i] - num[1] + ... + num[i+1] - num[i] + num[i+2] - num[i] + ...
    // 因此可以将整个过程归纳为
    // i * nums[i] - (num[0] + num[1] + ... num[i-1]) + (num[i+1] + num[i+2] + ...)- (n-i-1) * num[i]
    // 其中的累加和可以通过前缀和进行优化
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] sum = new int[n+1];
        //前缀和 sum[i] = num[0] + ... + num[i-1]
        for(int i=1;i<=n;i++){
            sum[i] = sum[i-1] + nums[i-1];
        }

        for(int i=0;i<n;i++){
            int ans = i * nums[i] - sum[i] + sum[n] - sum[i+1] - (n - i -1) * nums[i];
            res[i] = ans;
        }
        return res;

    }
}
