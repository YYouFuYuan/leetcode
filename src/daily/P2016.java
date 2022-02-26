package daily;

import java.util.Arrays;

public class P2016 {
    //暴力求解 O(n^2)
    public int maximumDifference2(int[] nums) {
        int ans = -1;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j] > nums[i]){
                    ans = Math.max(ans,nums[j] - nums[i]);
                }
            }
        }
        return ans;
    }

    //归并排序 O(nlogn)
    public int mergeSort(int[] nums,int left,int right){
        if(left >= right)
            return -1;
        int mid = left + ((right - left) >> 1);
        int lResult = mergeSort(nums,left,mid);
        int rResult = mergeSort(nums,mid+1,right);
        int curResult = Math.max(lResult,rResult);
        curResult = nums[right] > nums[left] ? Math.max(curResult,nums[right] - nums[left]) : curResult;
        merge(nums,left,mid,right);
        return curResult;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right){
            if(nums[p1] <= nums[p2]){
                temp[index++] = nums[p1++];
            }
            else temp[index++] = nums[p2++];
        }

        while (p1 <= mid){
            temp[index++] = nums[p1++];
        }
        while (p2 <= right){
            temp[index++] = nums[p2++];
        }

        for(int i=0;i<temp.length;i++){
            nums[left + i] = temp[i];
        }
    }

    public int maximumDifference1(int[] nums) {
        return mergeSort(nums,0,nums.length-1);
    }

    //O(n)
    public int maximumDifference(int[] nums) {
        int minValue = nums[0];
        int ans = -1;
        for(int i=1;i<nums.length;i++){
            if(nums[i] > minValue){
                ans = Math.max(ans,nums[i] - minValue);
            }
            else minValue = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,5,2,10};
        int r = new P2016().maximumDifference(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(r);
    }


}
