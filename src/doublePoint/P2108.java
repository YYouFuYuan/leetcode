package doublePoint;

public class P2108 {
    public String firstPalindrome(String[] words) {
        for(String word : words){
            char[] w = word.toCharArray();
            int left = 0;
            int right = w.length-1;
            boolean flag = true;
            while (left < right){
                if(w[left] == w[right]){
                    left++;
                    right--;
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag)
                return word;
        }
        return "";
    }
}
