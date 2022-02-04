package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P786 {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        //小根堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double a = (double)arr[o1[0]] / arr[o1[1]];
                double b = (double)arr[o2[0]] / arr[o2[1]];
                if(a == b)
                    return 0;
                return  a - b < 0 ? -1 : 1;
            }
        });
        for(int i=0;i<Math.min(n,k);i++){
            queue.add(new int[]{i,n-1});
        }
        while (k-- > 0 && !queue.isEmpty()){
            int[] p = queue.poll();
            if(p[1] > 0){
                queue.add(new int[]{p[0],p[1] - 1});
            }
        }
        int[] ans = queue.poll();
        return new int[]{arr[ans[0]],arr[ans[1]]};
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        int k = 3;
        new P786().kthSmallestPrimeFraction(arr,k);
    }
}
