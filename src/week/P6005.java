package week;

import java.util.HashMap;
import java.util.Map;

public class P6005 {

    public int minimumOperations(int[] nums) {
        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        for(int i=0;i<nums.length;i+=2){
            map1.put(nums[i],map1.getOrDefault(nums[i],0) + 1);
        }
        for(int i=1;i<nums.length;i+=2){
            map2.put(nums[i],map2.getOrDefault(nums[i],0) + 1);
        }
        int key1 = 0;
        int max1 = 0;
        int key2 = 0;
        int max2 = 0;
        for(Integer k : map1.keySet()){
            if(map1.get(k) >= max1){
                key1 = k;
                max1 = map1.get(k);
            }
        }
        for(Integer k : map2.keySet()){
            if(map2.get(k) >= max2){
                key2 = k;
                max2 = map2.get(k);
            }
        }
        int length = nums.length;
        int flag = length % 2 == 1 ? 1 : 0;
        if(key1 == key2){
            if(max2 >= max1){
                int nK = key1;
                max1 = 0;
                key1 = 0;
                for(Integer k : map1.keySet()){
                    if(k != nK &&map1.get(k) >= max1){
                        key1 = k;
                        max1 = map1.get(k);
                    }
                }
            }
            else {
                int nK = key2;
                max2 = 0;
                key2 = 0;
                for(Integer k : map2.keySet()){
                    if(k != nK &&map2.get(k) >= max2){
                        key2 = k;
                        max2 = map2.get(k);
                    }
                }
            }
        }
        return length - max1 - max2;

    }

    public static void main(String[] args) {
        int r = new P6005().minimumOperations(new int[]{3,3,3,3,3,3,1});
        System.out.println(r);
    }
}
