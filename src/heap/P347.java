package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            //小根堆
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int value = entry.getValue();
            //堆满
            if(queue.size() == k){
                if(value > queue.peek()[1]){
                    queue.poll();
                    queue.add(new int[]{num,value});
                }
            }
            else queue.add(new int[]{num,value});
        }
        int[] result = new int[k];
        for(int i=0;i<k;i++){
            result[i] = queue.poll()[0];
        }
        return result;
    }


}
