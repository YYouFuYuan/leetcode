package daily;

import java.util.HashMap;
import java.util.Map;

public class P1748 {

    public int sumOfUnique(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        int res = 0;
        for(int key : map.keySet()){
            if(map.get(key) == 1)
                res += key;
        }
        return res;
    }
}
