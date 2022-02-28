package week;

import java.util.Arrays;

public class P6009 {
    public int minSteps(String s, String t) {
        int[] sNum = new int[26];
        int[] tNum = new int[26];
        char[] sstr = s.toCharArray();
        for(int i=0;i<sstr.length;i++){
            sNum[sstr[i] - 'a']++;
        }
        char[] tstr = t.toCharArray();
        for(int i=0;i<tstr.length;i++){
            tNum[tstr[i] - 'a']++;
        }
        int[] sum = new int[26];
        for(int i=0;i<26;i++){
            sum[i] = Math.max(sNum[i],tNum[i]);
        }
        int allSum = Arrays.stream(sum).sum();
        int sSum = Arrays.stream(sNum).sum();
        int tSum = Arrays.stream(tNum).sum();
        return 2 * allSum - sSum - tSum;
    }
}
