package prefixSum;

public class P1732 {
    public int largestAltitude(int[] gain) {
        int sum = 0;
        int res = 0;
        for(int height : gain){
            sum += height;
            res = Math.max(res,sum);
        }
        return res;
    }
}
