package dp;

public class P334 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] minVaue = new int[n];
        int[] maxValue = new int[n];
        maxValue[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            maxValue[i] = Math.max(maxValue[i+1],nums[i]);
        }
        minVaue[0] = nums[0];
        for(int i=1;i<n;i++){
            minVaue[i] = Math.min(nums[i],minVaue[i-1]);
        }
        //双向遍历枚举 i 前面是否存在比nums[i]小的数，i后面是否存在比nums[i]大的数
        for(int i=1;i<n-1;i++){
            if(nums[i] > minVaue[i-1] && nums[i] < maxValue[i+1])
                return true;
        }
        return false;
    }
}
