package doublePoint;

import java.util.Arrays;

public class P2161 {
    public void swap(int[] nums,int i,int j){
        if(i!=j){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    public int[] pivotArray(int[] nums, int pivot) {
        int length = nums.length;
        int left = -1;
        int right = length;
        int i = 0;
        int[] res = new int[length];
        Arrays.fill(res,pivot);
        while (i++ < length){
            if(nums[i] < pivot){
                res[left] = nums[i];
                left++;
            }
            else if(nums[i] > pivot){
                res[right] = nums[i];
                right--;
            }
        }
        left = right +1;
        right = length - 1;
        while (left < right){
            swap(res,left,right);
            left++;
            right--;
        }
        return res;
    }
}
