package daily;

import java.util.HashMap;
import java.util.Map;

public class P2006 {
    public int countKDifference(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for(int i=0;i<nums.length;i++){
            res += map.getOrDefault(nums[i] - k,0) + map.getOrDefault(nums[i] + k,0);
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }
        return res;
    }
}
