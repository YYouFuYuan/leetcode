package daily;

import java.util.Arrays;

public class P1601 {

    public int res = 0;

    public boolean check(int[] count){
        for(int i=0;i<count.length;i++){
            if(count[i] != 0)
                return false;
        }
        return true;
    }

    public void dfs(int index,int n,int[][] requests,int[] count,int ans){
        if(index >= n){
            if(check(count)){
                res = Math.max(res,ans);
            }
            return;
        }

        //选
        count[requests[index][0]]--;
        count[requests[index][1]]++;
        dfs(index+1,n,requests,count,ans+1);
        count[requests[index][0]]++;
        count[requests[index][1]]--;

        //不选
        dfs(index+1,n,requests,count,ans);

    }

    public int maximumRequests(int n, int[][] requests) {
        int[] count = new int[n];
        dfs(0,requests.length,requests,count,0);
        return res;
    }

    public static void main(String[] args) {
        new P1601().maximumRequests(5,new int[][]{{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}});

    }


}
