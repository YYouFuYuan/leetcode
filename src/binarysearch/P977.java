package binarysearch;

import com.sun.source.tree.WhileLoopTree;

public class P977 {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        //1.先找到离0最小的数
        int left = 0;
        int right = n-1;
        while (left < right){
            int mid = left + ( (right - left) >> 1);
            if(nums[mid] < 0){
                left = mid + 1;
            }
            else right = mid;
        }
        if(left >= 1){
            left = Math.abs(nums[left] - 0) <= Math.abs(nums[left - 1] -0) ? left : left - 1;
        }
        int L = left;
        int R = left + 1;
        int index = 0;
        while (L>=0 && R < n){
            if(Math.abs(nums[L] - 0 ) <= Math.abs(nums[R] - 0)){
                res[index++] = nums[L] * nums[L];
                L--;
            }
            else {
                res[index++] = nums[R] * nums[R];
                R++;
            }
        }
        while (L >= 0){
            res[index++] = nums[L] * nums[L];
            L--;
        }
        while (R < n){
            res[index++] = nums[R] * nums[R];
            R++;
        }
        return res;
    }

    public static void main(String[] args) {
        new P977().sortedSquares(new int[]{-3,-2,-1});
    }
}
