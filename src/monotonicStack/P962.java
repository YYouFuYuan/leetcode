package monotonicStack;

import java.util.Stack;

public class P962 {

    //题目要求满足i<j && nums[i] <= nums[j] 的 max(j-i)
    //假设满足条件的i不存在于递减的单调栈中，反证法：
    //如果i不存在栈中且满足条件，说明栈中存在元素 num[k] < num[i]，那么应该有更满足的条件 j - k > j - i
    //与假设矛盾，i必在单调栈中
    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        //创建一个递减的单调栈
        for(int i=0;i<n;i++){
            if(stack.isEmpty() || nums[stack.peek()] > nums[i]){
                stack.add(i);
            }
        }
        int ans = 0;
        //要使得j - i 最大，反序遍历
        for(int j=n-1;j>=0;j--){
            //已经满足的 j-i 不可能比 [i..j] - i 来的大，直接出栈
            while(!stack.isEmpty() && nums[j] >= nums[stack.peek()]){
                ans = Math.max(ans,j - stack.peek());
                stack.pop();
            }
        }
        return ans;
    }
}
