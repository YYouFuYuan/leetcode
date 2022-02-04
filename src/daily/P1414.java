package daily;

import java.util.ArrayList;
import java.util.List;

public class P1414 {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> list = new ArrayList<>();
        int a = 1;
        int b = 1;
        list.add(a);
        list.add(b);
        while (a + b <= k){
            int c = a + b;
            list.add(c);
            a = b;
            b = c;
        }
        int ans = 0;
        for(int i=list.size()-1;i>=0 && k > 0;i--){
            int num = list.get(i);
            if(num <= k){
                k -= num;
                ans++;
            }
        }
        return ans;
    }
}
