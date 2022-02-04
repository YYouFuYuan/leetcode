package binarysearch;

import java.util.Arrays;

public class P34 {



    public int[] searchRange2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if(nums[middle] > target){
                right = middle - 1;
            }
            else if(nums[middle] < target){
                left = middle + 1;
            }
            else {
                index = middle;
                break;
            }
        }
        if(left > right)
            return new int[]{-1,-1};

        left = 0;
        right = index;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if(nums[middle] == nums[index]){
                right = middle - 1;
            }
            else left = middle + 1;
        }
        int L = left;


        left = index;
        right = nums.length - 1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if(nums[middle] == nums[index]){
                left = middle + 1;
            }
            else right = middle - 1;
        }
        int R = right;
        return new int[]{L,R};
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int n = nums.length - 1;
        if(n < 0)
            return new int[]{-1,-1};
        int right = n;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] < target){
                left = mid + 1;
            }
            else right = mid;
        }
        int L = nums[left] == target ? left : -1;
        int R = L;
        if( L == -1){
            return new int[]{L,R};
        }
        else {
            right = n;
            while (left < right){
                int mid = left + ((right - left) >> 1) + 1;
                if(nums[mid] > target){
                    right = mid - 1;
                }
                else left = mid;
            }
        }
        return new int[]{L,right};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new P34().searchRange(new int[]{1},1)));
    }
}
