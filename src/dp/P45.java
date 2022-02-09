package dp;

import java.util.HashMap;
import java.util.Map;

public class P45 {
    /*
    *   暴力递归写法
    * */
    public int process(int curIndex,int target,int[] nums){
        if(curIndex > target)
            return -1;
        if(curIndex == target)
            return 0;
        int step = nums[curIndex];
        int res = -1;
        for(int i=1;i<=step;i++){
            int next = process(curIndex + i,target,nums);
            if(next != -1){
                if(res == -1){
                    res = next;
                }
                else res = Math.min(res,next);
            }
        }
        return res == -1 ? res : res + 1;

    }
    public int jump(int[] nums) {
        int n = nums.length;
        return process(0,n-1,nums);
    }


    /*
     *   暴力递归写法 => 记忆化搜索
     * */
    public int processMemory(int curIndex, int target, int[] nums, Map<Integer,Integer> map){
        if(curIndex > target)
            return -1;
        if(curIndex == target)
            return 0;
        int step = nums[curIndex];
        int res = -1;
        for(int i=1;i<=step;i++){
            int next;
            if(map.containsKey(curIndex + i)){
                next = map.get(curIndex + i);
            }
            else {
                next = processMemory(curIndex + i,target,nums,map);
                map.put(curIndex + i,next);
            }
            if(next != -1){
                if(res == -1){
                    res = next;
                }
                else res = Math.min(res,next);
            }
        }
        map.put(curIndex,res == -1 ? res : res + 1);
        return res == -1 ? res : res + 1;

    }
    public int jumpMemory(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        return processMemory(0,n-1,nums,map);
    }

    public int jumpDP(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for(int i=n-2;i>=0;i--){
            int step = nums[i];
            int res = -1;
            for(int j=1;j<=step;j++){
                int next = i + j >= n ? -1 : dp[i+j];
                if(next != -1){
                    if(res == -1){
                        res = next;
                    }
                    else res = Math.min(res,next);
                }
            }
            dp[0] = res == -1 ? res : res + 1;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        new P45().jumpMemory(new int[]{2,3,1,1,4});
    }
}
