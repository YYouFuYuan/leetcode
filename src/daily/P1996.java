package daily;

import java.util.*;

public class P1996 {

    public int numberOfWeakCharacters(int[][] properties) {

        Arrays.sort(properties, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                //先按攻击力从大到小排序
                //攻击力相等的，按防御力从大到小
                return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
            }
        });
        for(int i=0;i<properties.length;i++){
            System.out.println(Arrays.toString(properties[i]));
        }
        int ans = 0;
        Deque<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<properties.length;i++){
            if(!queue.isEmpty() && queue.peek() < properties[i][1]){
                ans++;
                queue.pop();
            }
            queue.add(properties[i][1]);
        }
        return ans;
    }
}
