package prefixSum;

public class P1442 {

    //n^3
    public int countTripletsN3(int[] arr) {
        int n = arr.length;
        int[] xor = new int[n+1];
        //xor[i] = arr[0] ^ ... arr[i-1]
        for(int i=1;i<=n;i++){
            xor[i] = xor[i-1] ^ arr[i-1];
        }
        int ans = 0;
        for (int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j;k<n;k++){
                    //i j k 满足条件
                    //a = arr[i] ^ ... arr[j-1]
                    //b = arr[j] ^ ... arr[k]
                    if((xor[i] ^ xor[j]) == (xor[j] ^ xor[k+1]))
                        ans++;
                }
            }
        }
        return ans;
    }

    //n^2
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] xor = new int[n+1];
        //xor[i] = arr[0] ^ ... arr[i-1]
        for(int i=1;i<=n;i++){
            xor[i] = xor[i-1] ^ arr[i-1];
        }
        //i j k 满足条件
        //a = arr[i] ^ ... arr[j-1]
        //b = arr[j] ^ ... arr[k]
        //要使得 a == b 即 a ^ b = 0
        // => xor[i] ^ xor[j] ^ xor[j] ^ xor[k+1] = 0
        // => xor[i] ^ xor[k+1] = 0
        // j 可以取 i+1...k
        int ans = 0;
        for (int i=0;i<n;i++){
            for(int k=i+1;k<n;k++){
                if((xor[i] ^ xor[k+1]) == 0)
                    ans += (k - i);
            }

        }
        return ans;
    }
}
