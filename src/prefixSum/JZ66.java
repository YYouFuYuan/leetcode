package prefixSum;

import java.util.Arrays;

public class JZ66 {
    //难点在于不能用除法，两边分别求前缀乘积
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] preSum = new int[n+1];
        preSum[0] = 1;
        int[] postSum = new int[n+1];
        postSum[n] = 1;
        //pre[i] = a[0] * ... * a[i-1]
        for(int i=1;i<=n;i++){
            preSum[i] = preSum[i-1] * a[i-1];
        }
        //post[i] = a[i] * a[i+1] * ... * a[n-1]
        for(int i=n-1;i>=0;i--){
            postSum[i] = postSum[i+1] * a[i+1];
        }
        System.out.println(Arrays.toString(preSum));
        System.out.println(Arrays.toString(postSum));
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            res[i] = preSum[i] * postSum[i];
        }
        return res;
    }
}
