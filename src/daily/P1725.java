package daily;

import java.util.HashMap;
import java.util.Map;

public class P1725 {

    public int countGoodRectangles(int[][] rectangles) {
        int resK = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<rectangles.length;i++){
            int k = Math.min(rectangles[i][0],rectangles[i][1]);
            int count = map.getOrDefault(k,0);
            count += 1;
            map.put(k,count);
            resK = Math.max(resK,k);
        }
        return map.get(resK);
    }
}
