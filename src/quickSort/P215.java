package quickSort;

public class P215 {

    public void swap(int[] nums,int i,int j){
        if(i!= j){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    boolean flag = false;
    int result = Integer.MAX_VALUE;

    public void quickSort(int[] nums,int left,int right,int k){
        if(left >= right)
            return;
        int[] privotIndex = partition(nums,left,right);
        if(privotIndex[0] == k || privotIndex[1] == k){
            flag = true;
            result = nums[k];
            return;
        }
        if(!flag){
            quickSort(nums,left,privotIndex[0]-1,k);
            quickSort(nums,privotIndex[1]+1,right,k);
        }


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
            else if(nums[i] > target){
                swap(nums,i,R-1);
                R--;
            }
            else {
                swap(nums,i,L+1);
                i++;
                L++;
            }
        }
        return new int[]{L+1,R-1};
    }

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1,k);
        return result;
    }
}
