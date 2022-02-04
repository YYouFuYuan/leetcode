package binarysearch;

public class P154 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] < nums[right]){
                right = mid;
            }
            else if(nums[mid] == nums[right]){
                right--;
            }
            else left = mid + 1;
        }
        return nums[left];
    }
}
