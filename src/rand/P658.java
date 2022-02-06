package rand;

import java.util.ArrayList;
import java.util.List;

public class P658 {
    //时间复杂度O(n)
    //找最接近x的元素用了O(n)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int n = arr.length;
        int[] dist = new int[n];
        int minIndex = 0;
        int minDist = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            dist[i] = Math.abs(arr[i] - x);
            if(minDist > dist[i]){
                minIndex = i;
                minDist = dist[i];
            }
        }
        List<Integer> list = new ArrayList<>();
        int L = minIndex;
        int R = minIndex;
        while (L >= 0 && R < n && R - L + 1 < k){
            if(L - 1 >= 0 && R + 1 < n){
                if(dist[L-1] <= dist[R+1]){
                    L--;
                }
                else R++;
            }
            else if(L - 1 >= 0){
                L--;
            }
            else {
                R++;
            }
        }
        for (int i=0;i<k;i++){
            list.add(arr[i+L]);
        }
        return list;
    }

    //二分查找 O(log n)找数组中 >= x的左边界元素
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        while (left < right){   //二分查找找左边界元素
            int mid = left + ( (right - left) >> 1);
            if(arr[mid] >= x){
                right = mid;
            }
            else left = mid + 1;
        }
        List<Integer> list = new ArrayList<>();
        //找到最接近x的元素，比较一下
        if(left > 0){
            left = Math.abs(left - x) < Math.abs(arr[left-1] - x) ? left : left - 1;
        }
        System.out.println(left);
        int L = left;
        int R = left;
        //双指针取边界
        while (L >= 0 && R < n && R - L + 1 < k){
            if(L - 1 >= 0 && R + 1 < n){
                if(Math.abs(arr[L-1] - x) <= Math.abs(arr[R+1] - x)){
                    L--;
                }
                else R++;
            }
            else if(L - 1 >= 0){
                L--;
            }
            else {
                R++;
            }
        }
        for (int i=0;i<k;i++){
            list.add(arr[i+L]);
        }
        return list;
    }

    public static void main(String[] args) {
        new P658().findClosestElements(new int[]{1,2,3,4,5},4,6);
    }
}
