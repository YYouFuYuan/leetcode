package daily;

import java.util.ArrayList;
import java.util.List;

public class P564 {
    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n);
        long ans = -1;
        List<Long> condidaes = getCandidates(n);
        for(long condidate : condidaes){
            if(condidate != selfNumber){
                //更新答案
                if(ans == -1 || Math.abs(condidate - selfNumber) < Math.abs(ans - selfNumber)
                        || (Math.abs(condidate - selfNumber) < Math.abs(ans - selfNumber) && ans > condidate ) ){
                    ans = condidate;
                }
            }
        }
        return Long.toString(ans);
    }

    private List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> condidates = new ArrayList<>();
        condidates.add((long) (Math.pow(10,len-1) - 1));
        condidates.add((long) (Math.pow(10,len) +1 ));
        //(len+1) / 2 如果n是奇数就会截取多一位，如果是偶数刚好对半
        long selfPrefix = Long.parseLong(n.substring(0,(len+1) / 2));
        for(long i = selfPrefix - 1; i <= selfPrefix + 1; i++){
            StringBuilder sb = new StringBuilder();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            //反转前半部分的字符串
            StringBuilder suffix = new StringBuilder(prefix).reverse();
            //如果是偶数
            if(len % 2 == 0){
                sb.append(suffix);
            }
            else {  //奇数 第一位就不要了
                sb.append(suffix.substring(1));
            }
            condidates.add(Long.parseLong(sb.toString()));
        }
        return condidates;
    }

    public static void main(String[] args) {
        new P564().nearestPalindromic("99");
    }
}
