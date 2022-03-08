package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2055 {
    //查找左边界并返回值和位置
    public int[] binarySearchLarge(List<Integer> list,int num){
        int left = 0;
        int n = list.size();
        int right = n;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(list.get(mid) >= num){
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        if(left == n)
            return new int[]{-1,-1};
        return list.get(left) >= num ? new int[]{list.get(left),left} : new int[]{-1,-1};
    }
    //查找右边界
    public int[] binarySearchSmall(List<Integer> list,int num){
        int left = 0;
        int n = list.size();
        int right = n;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(list.get(mid) <= num){
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        if(left == 0)
            return new int[]{-1,-1};
        return list.get(left-1) <= num ? new int[]{list.get(left-1),left-1} : new int[]{-1,-1};
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        List<Integer> la = new ArrayList<>();
        char[] str = s.toCharArray();
        for(int i=0;i<str.length;i++){
            if(str[i] == '|'){
                la.add(i);
            }
        }
        int[] res = new int[queries.length];
        int count = 0;
        for(int[] q : queries){
            int[] left = binarySearchLarge(la,q[0]);
            int[] right = binarySearchSmall(la,q[1]);
            if(left[0] == -1 || right[0] == -1 || left[0] > q[1] || right[0] < q[0])
                res[count++] = 0;
            else res[count++] = (right[0] - left[0] - (right[1] - left[1]));
        }
        return res;

    }

    public static void main(String[] args) {
        int[] res = new P2055().platesBetweenCandles("***|**|*****|**||**|*",new int[][]{
                {1,17},{4,5},{14,17},{5,11},{15,16}
        });
        System.out.println(Arrays.toString(res));
    }
}
