package daily;

public class P717 {

    public boolean process(int n,int curIndex,int[] bits){
        //可以只有最有的0
        if(n -1 == curIndex){
            return true;
        }
        //最后必须2bit
        if(n == curIndex){
            return false;
        }
        if(bits[curIndex] == 0){
            return process(n,curIndex + 1,bits);
        }
        else {
            return process(n,curIndex + 2,bits);
        }
    }

    public boolean isOneBitCharacterRe(int[] bits) {
        int n = bits.length;
        return process(n,0,bits);
    }

    //dp版本
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        boolean[] dp = new boolean[n+1];
        dp[n] = false;
        dp[n-1] = true;
        for(int i=n-2;i>=0;i--){
            dp[i] = bits[i] == 0 ? dp[i+1] : dp[i+2];
        }
        return dp[0];
    }

}
