package dp;

import java.util.HashMap;

public class P213 {
    /*
        暴力递归版本
     */
    public int process(int curIndex,int[] nums,int[] flag){
        if(curIndex >= nums.length){
            return 0;
        }
        //最后一个房间比较特殊，要考虑第1个房间的状态
        if(curIndex == nums.length - 1){
            if(flag[0] == 0){   //偷
                return nums[curIndex] + process(curIndex + 2,nums,flag);
            }
            else return 0; //不偷
        }
        //当前房间可以偷
        if(curIndex == 0 || flag[curIndex-1] == 0){
            //选择偷
            flag[curIndex] = 1;
            int res1 = nums[curIndex] + process(curIndex + 2,nums,flag);
            //选择不偷
            flag[curIndex] = 0;
            int res2 = process(curIndex + 1,nums,flag);
            return Math.max(res2,res1);
        }
        else {
            //当前房间不能偷
            return process(curIndex+1,nums,flag);
        }

    }

    public int rob(int[] nums) {
        int[] flag = new int[nums.length];
        return process(0,nums,flag);
    }

    /*
            dp版本
     */
    public int robDP(int[] nums){
        if(nums.length < 2)
            return nums[0];
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];

        //最后一个房间不偷
        //倒2个肯定就要偷了
        dp1[nums.length-2] = nums[nums.length-2];
        for(int i=nums.length-3;i>=0;i--){  //剩余的所有房间都可以采取偷与不偷两种策略
            if(i + 2 < nums.length)
                dp1[i] = Math.max(nums[i] + dp1[i+2],dp1[i+1]);
            else dp1[i] = dp1[i+1];
        }
        //最后一个房间偷，倒2个肯定不能偷
        dp2[nums.length-1] = nums[nums.length-1];
        dp2[nums.length-2] = dp2[nums.length-1];
        for(int i=nums.length-3;i>0;i--){ //剩余的所有房间都可以采取偷与不偷两种策略，但第一个房间肯定不能偷
            if(i + 2 < nums.length)
                dp2[i] = Math.max(nums[i] + dp2[i+2],dp2[i+1]);
            else dp2[i] = dp2[i+1];
        }
        //直接比较dp2[1]就行，因为第一个房间不能偷
        return Math.max(dp1[0],dp2[1]);
    }




}
