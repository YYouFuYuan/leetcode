package daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P969 {
    //类似冒泡排序，每次确定右边的元素
    //1）首先找到i位置应该要对应的元素arr[i]
    //2)    1）如果arr[i]在0位置，直接reversed
    //      2) 如果不再0位置，先对当前的的位置reversed，将arr[i]换到0位置
    //      3) 再来reversed
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int n = arr.length;
        //存储元素的所在下标
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(arr[i],i);   
        }
        for(int i=n-1;i>0;i--){
            //最右边元素已经有序
            if(arr[i] == i + 1){
                continue;
            }
            //找到当前 i + 1的位置
            int index = map.get(i+1);
            if(index != 0){
                reversed(arr,index+1,map);
                ans.add(index+1);
            }
            reversed(arr,i+1,map);
            ans.add(i+1);
        }
        return ans;
    }

    private void reversed(int[] arr, int index, Map<Integer, Integer> map) {
        int left = 0;
        int right = index - 1;
        while (left < right){
            int temp = arr[left];


            arr[left] = arr[right];
            map.put(arr[left],left);

            arr[right] = temp;
            map.put(arr[right],right);

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        List<Integer> ans = new P969().pancakeSort(new int[]{3,2,4,1});
        System.out.println(ans);
    }
}
