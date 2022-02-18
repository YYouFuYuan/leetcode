package daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1791 {
    public int findCenter(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int[] edge : edges){
            set.add(edge[0]);
            set.add(edge[1]);
            Set<Integer> edge1 = map.getOrDefault(edge[0], new HashSet<>());
            edge1.add(edge[1]);
            map.put(edge[0],edge1);
            Set<Integer> edge2 = map.getOrDefault(edge[1], new HashSet<>());
            edge2.add(edge[0]);
            map.put(edge[1],edge2);
        }

        int n = set.size();
        for(Integer key : map.keySet()){
            Set<Integer> edge = map.getOrDefault(key, new HashSet<>());
            if(edge.size() == n-1)
                return key;

        }
        return -1;
    }
}
