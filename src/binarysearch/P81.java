package binarysearch;

public class P81 {
    //部分有序且不重复，
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + ( (right - left) >> 1);
            if(nums[mid] == target)
                return true;
            //此时前半部分有序
            if(nums[mid] >= nums[0]){

                if(target > nums[0] && target < nums[mid]){
                    right = mid - 1;
                }
                else left = mid + 1;
            }else {
                //此时后半部分有序
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }
                else right = mid - 1;
            }
        }
        return false;
    }
}
