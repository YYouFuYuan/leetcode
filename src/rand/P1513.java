package rand;

import java.util.Arrays;

public class P1513 {
    public int numSub(String s) {
        long res = 0;
        char[] str = s.toCharArray();
        int count = 0;
        long[] dp = new long[str.length];
        dp[0] = 1;
        for(int i=1;i<str.length;i++){
            dp[i] = dp[i-1] + (i + 1);
        }
        //System.out.println(Arrays.toString(dp));
        for(int i=0;i<str.length;i++){
            if(str[i] == '0'){
                if(count != 0){
                    res += dp[count-1];
                    res = res % 1000000007;
                    count = 0;
                }
            }
            else {
                count++;
            }
        }
        if(count != 0){
            res += dp[count-1];
            res = res % 1000000007;
        }
        return (int) res;
    }
}
