package daily;

import java.util.*;

public class P1380 {

    public List<Integer> luckyNumbers (int[][] matrix) {
            Set<Integer> row = new HashSet<>();
            Set<Integer> col = new HashSet<>();
            int m = matrix.length;
            int n = matrix[0].length;
            for(int i=0;i<m;i++){
                int minValue = Arrays.stream(matrix[i]).min().getAsInt();
                row.add(minValue);
            }
            for(int i=0;i<n;i++){
                int maxValue = Integer.MIN_VALUE;
                for(int j=0;j<m;j++){
                    maxValue = Math.max(matrix[j][i],maxValue);
                }
                col.add(maxValue);
            }
            row.retainAll(col);
            List<Integer> res = new ArrayList<>(row);
            return res;
    }
}
