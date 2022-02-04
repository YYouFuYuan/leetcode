package heap;

import java.util.*;

public class P373 {


    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        List<List<Integer>> result = new ArrayList<>();
        //小根堆，存的是nums1和nums2的点对下标
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (nums1[o1[0]] + nums2[o1[1]]) -(nums1[o2[0]] + nums2[o2[1]]);
            }
        });
        for(int i=0;i<Math.min(n,k);i++)
            queue.add(new int[]{i,0});
        while (result.size() < k && !queue.isEmpty()){
            int[] p = queue.poll();
            int a = p[0];
            int b = p[1];
            result.add(Arrays.asList(nums1[a],nums2[b]));

            if( b + 1  < m){
                queue.add(new int[]{a,b+1});
            }
        }
        return result;
    }
}
