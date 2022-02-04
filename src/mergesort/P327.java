package mergesort;

public class P327 {

    public static int mergeSort(int left,int right,long[] sum,int lower,int upper){
        if(left == right)
            return 0;
        int mid = left + ((right - left) >> 1);
        int n1 = mergeSort(left,mid,sum,lower,upper);
        int n2 = mergeSort(mid+1,right,sum,lower,upper);
        int l = mid + 1;
        int r = mid + 1;
        int k = left;
        int ret = n1 + n2;
        while (k <= mid){
            while (l<=right && sum[l] - sum[k] < lower){
                l++;
            }
            while (r<=right && sum[r] - sum[k] <= upper){
                r++;
            }
            ret += (r-l);
            k++;
        }
        merge(sum,left,mid,right);
        return ret;
    }

    public static void merge(long[] nums, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right){
            if(nums[p1] < nums[p2]){
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

        for(int i=0;i<right-left+1;i++){
            nums[left + i] = temp[i];
        }
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length-1;
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return mergeSort(0,n-1,sum,lower,upper);
    }

    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2,5,-1},-2,2));
    }
}
