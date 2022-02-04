package mergesort;

import java.util.Arrays;

public class P493 {


    public int mergeSort(int[] nums,int left,int right){
        if(left >= right)
            return 0;
        int mid = left + ( ( right - left ) >> 1);
        int n1 = mergeSort(nums,left,mid);
        int n2 = mergeSort(nums,mid+1,right);
        int ret = n1 + n2;
        int i = left;
        int j = mid + 1;
        while (i <= mid){
            while (j<=right && (long)nums[i] > (long)nums[j] * 2){
                j++;
            }
            ret += (j - (mid+1));
            i++;
        }
        merge(nums,left,mid,right);
        return ret;
    }
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right){
            if(nums[p1] < nums[p2]){

                temp[index++] = nums[p1++];
            }
            else {
                temp[index++] = nums[p2++];
            }
        }

        while (p1 <= mid){
            temp[index++] = nums[p1++];
        }
        while (p2 <= right){
            temp[index++] = nums[p2++];
        }

        for(int i=0;i<right-left+1;i++){
            nums[left + i] = temp[i];
        }
    }

    public int reversePairs(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
        return 1;
    }

    public static void main(String[] args) {
        new P493().reversePairs(new int[]{5,4,3,2,1});
    }
}
