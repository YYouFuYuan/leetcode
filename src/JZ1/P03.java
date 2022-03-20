package JZ1;

import java.util.HashSet;

public class P03 {
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num))
                return num;
            set.add(num);
        }
        return -1;
    }
}
