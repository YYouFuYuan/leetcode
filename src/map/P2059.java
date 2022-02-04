package map;

import java.util.*;

public class P2059 {
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Set<Integer> set = new HashSet<>();
        int step = -1;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                int cur = queue.poll();
                if(cur == goal)
                    return step;

                for(int j=0;j<nums.length;j++){
                    int newNum = cur + nums[j];
                    if(newNum == goal)
                        return step;
                    if(newNum > 1000 || cur < 0)
                        continue;
                    if(!set.contains(newNum)){
                        set.add(newNum);
                        queue.offer(newNum);
                    }
                    newNum = cur - nums[j];
                    if(newNum == goal)
                        return step;
                    if(newNum > 1000 || newNum < 0)
                        continue;
                    if(!set.contains(newNum)){
                        set.add(newNum);
                        queue.offer(newNum);
                    }
                    newNum = cur ^ nums[j];
                    if(newNum == goal)
                        return step;
                    if(newNum > 1000 || newNum < 0)
                        continue;
                    if(!set.contains(newNum)){
                        set.add(newNum);
                        queue.offer(newNum);
                    }
                }

            }

        }
        return -1;
    }
}
