package doublePoint;

import java.util.Locale;

public class P1768 {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int p1 = 0;
        int p2 = 0;
        while (p1 < str1.length && p2 < str2.length ){
            sb.append(str1[p1++]);
            sb.append(str2[p2++]);
        }
        while (p1 < str1.length){
            sb.append(str1[p1++]);
        }
        while (p2 < str2.length){
            sb.append(str2[p2++]);
        }
        return sb.toString();
    }
}
