import com.sun.tools.javac.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mian {

    public void swap(int[] arr,int i,int j){
        if(i != j){
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public void quickSort(int[] nums,int left,int right){
        if(left >= right){
            return;
        }
        swap(nums,left + (int)(Math.random() * (right - left + 1)),left);
        int[] privotIndex = partition(nums,left,right);
        quickSort(nums,left,privotIndex[0]-1);
        quickSort(nums,privotIndex[1]+1,right);
    }

    private int[] partition(int[] nums, int left, int right) {
        int L = left - 1;
        int R = right + 1;
        int i = left;
        int target = nums[left];
        while (i < R){
            if(nums[i] == target){
                i++;
            }
            else if(nums[i] < target){
                swap(nums,i,L+1);
                i++;
                L++;
            }
            else {
                swap(nums,i,R-1);
                R--;
            }
        }
        return new int[]{L+1,R-1};
    }

    public static void main(String[] args) {
        int[] nums = {4,5,2,1,8,7,9,3};
        new Mian().quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
