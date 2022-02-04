package binarysearch;

public class P540 {

    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int n = right;
        while (left <= right){
            int middle = left + (right - left) / 2 ;
            if(middle == 0 || middle == n){
                return nums[middle];
            }
            else {
                int l = middle - left;
                if(nums[middle] == nums[middle + 1]){
                    if(l % 2 == 1)
                        right = middle - 1;
                    else left = middle;
                }
                else if(nums[middle] == nums[middle - 1]){
                    if(l % 2 == 0)
                        right = middle;
                    else left = middle + 1;
                }
                else {
                    return nums[middle];
                }
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(new P540().singleNonDuplicate(new int[]{1,1,2,2,3}));
    }
}
