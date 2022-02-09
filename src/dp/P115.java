package dp;

public class P115 {
    /**
     * 暴力递归
     * @param s
     * @param t
     * @param curIndex1
     * @param curIndex2
     * @return
     */
    public int process(char[] s,char[] t,int curIndex1,int curIndex2){
        //base case
        if(curIndex1 <= s.length && curIndex2 == t.length)
            return 1;
        if(curIndex1 >= s.length || curIndex2 >= t.length)
            return 0;

        int res = 0;
        if(s[curIndex1] == t[curIndex2]){
            //目前二者相等,可以删除也可以不删除
            res = process(s,t,curIndex1 + 1,curIndex2)
                    + process(s,t,curIndex1 + 1,curIndex2+1);
        }
        else {
            //二者不等，只能删除s的字符
            res = process(s,t,curIndex1 + 1,curIndex2);
        }
        return res;
    }

    public int numDistinct(String s, String t) {
        char[] str = s.toCharArray();
        char[] ttr = t.toCharArray();
        return process(str,ttr,0,0);
    }

    /**
     * dp版本
     * @param s
     * @param t
     * @return
     */
    public int numDistinctDP(String s, String t) {
        char[] str = s.toCharArray();
        char[] ttr = t.toCharArray();
        int m = str.length;
        int n = ttr.length;
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            dp[i][n] = 1;
        }
        for(int i=0;i<n;i++){
            dp[m][i] = 0;
        }
        for(int i=m-1;i>=0;i--){
            char s1 = str[i];
            for(int j=n-1;j>=0;j--){
                char t1 = ttr[j];
                if(s1 == t1){
                    dp[i][j] = dp[i+1][j] + dp[i+1][j+1];
                }
                else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        new P115().numDistinct("rabbbit","rabbit");
    }
}
