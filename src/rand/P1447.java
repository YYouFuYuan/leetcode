package rand;

import java.util.ArrayList;
import java.util.List;

public class P1447 {
    public List<String> simplifiedFractions(int n) {
        List<String> list = new ArrayList<>();
        for(int i=2;i<=n;i++){
            for(int j=1;j<i;j++){
                if(gcd(i,j) == 1){
                    list.add(j + "/" + i);
                }
            }
        }
        return list;
    }



    public static void main(String[] args) {
        System.out.println(gcd(252,105));
        System.out.println(f(105,252));
        System.out.println(lcm(252,105));
    }

    /**
     * 辗转相除法递归版
     * @param a 无所谓 a > b 还是 b > a
     * @param b
     * @return
     */
    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a % b);
    }

    /**
     * 辗转相除法循环版
     * @param a
     * @param b
     * @return
     */
    public static int f(int a,int b){
        while (b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * 最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a,int b){
        int gcd = gcd(a,b);
        return a * b / gcd;
    }



}
