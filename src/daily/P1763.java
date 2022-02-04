package daily;

public class P1763 {

    public String longestNiceSubstring(String s) {
        int resLength = 0;
        int resBegin = 0;
        int n = s.length();
        char[] charArray = s.toCharArray();
        //枚举满足题意的最长美好子串的字符种类个数有i个
        for(int i=1;i<=26;i++){
            int[] lowerCnt = new int[26];   //小写统计
            int[] upperCnt = new int[26];   //大写统计
            int tot = 0;    //当前子串中所有出现过的字符种类数
            int sum = 0;    //当前子串中满足大小写都出现的字符种类数
            //由以上两个变量可知，当且仅当 tot == sum 时，当前子串内的所有出现过的字符都满足 大小写都出现
            //tot < sum 时，说明当前子串中出现部分字符仅出现过大小写中的一个
            int L = 0;
            int R = 0;
            int curR = 0;
            int curL = 0;
            while (R < n){
                if(Character.isLowerCase(charArray[R])){
                    curR = charArray[R] - 'a';
                    lowerCnt[curR]++;
                    if(lowerCnt[curR] == 1) {
                        if(upperCnt[curR] == 0){
                            tot++;
                        }
                        else if (upperCnt[curR] > 0) {
                            sum++;
                        }
                    }
                }
                if(Character.isUpperCase(charArray[R])){
                    curR = charArray[R] - 'A';
                    upperCnt[curR]++;
                    if(upperCnt[curR] == 1){
                        if(lowerCnt[curR] == 0){
                            tot++;
                        }
                        if(lowerCnt[curR] > 0){
                            sum++;
                        }
                    }
                }
                while (tot > i){
                    if(Character.isLowerCase(charArray[L])){
                        curL = charArray[L] - 'a';
                        lowerCnt[curL]--;
                        if(lowerCnt[curL] == 0) {
                            if (upperCnt[curL] > 0) {
                                sum--;
                            }
                            else if(upperCnt[curL] == 0){
                                tot--;
                            }
                        }
                    }
                    if(Character.isUpperCase(charArray[L])){
                        curL = charArray[L] - 'A';
                        upperCnt[curL]--;
                        if(upperCnt[curL] == 0){
                            if (lowerCnt[curL] > 0) {
                                sum--;
                            }
                            else if(lowerCnt[curL] == 0){
                                tot--;
                            }
                        }
                    }
                    L++;
                }
                if(tot == sum){
                    if(resLength < R - L + 1){
                        resLength = R - L + 1;
                        resBegin = L;
                    }
                }
                R++;
            }
        }
        return s.substring(resBegin,resBegin + resLength);
    }

    //P395
    public int longestSubstring(String s, int k) {
        int res = 0;
        int n = s.length();
        char[] charArray = s.toCharArray();
        //枚举满足题意的最长子串中出现字符种类的个数
        for(int i=1;i<=26;i++){
            int tot = 0;    //当前子串中所有出现过的字符种类数
            int sum = 0;    //当前子串中满足出现次数>=k的字符种类数
            //由以上两个变量可知，当且仅当 tot == sum 时，当前子串内的所有出现过的字符都满足次数 >= k
            //tot < sum 时，说明当前子串中出现部分字符的出现次数 < k
            int L = 0;
            int R = 0;
            int[] cnt = new int[26]; //记录当前子串中各个字符的出现个数
            while (R < n){
                int curR = charArray[R] - 'a';
                cnt[curR]++;
                if(cnt[curR] == 1){  //新出现的字符
                    tot++;
                }
                if(cnt[curR] == k){  //该字符出现次数 >= k
                    sum++;
                }
                while (tot > i){    //如果当前子串中出现的字符种类 > 规定的种类，说明子串太长，左指针要滑动
                    int curL = charArray[L]-'a';
                    cnt[curL]--;
                    if(cnt[curL] == 0){ //左指针移动时，字符种类减少
                        tot--;
                    }
                    if(cnt[curL] == k - 1){ //该字符的出现次数不满足k
                        sum--;
                    }
                    L++;
                }
                if( tot == sum ){   //当前仅当 该条件，当前字符才是满足题意的子串，但最不最长需要统计
                    res = Math.max(res, R - L + 1);
                }
                R++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int res = new P1763().longestSubstring("bbaaacbd",3);
//        System.out.println(res);
        System.out.println(new P1763().longestNiceSubstring("YazaAay"));
    }
}
