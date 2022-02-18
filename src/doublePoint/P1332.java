package doublePoint;

public class P1332 {

    //整个字符串只有a和b
    //最多只需删除2次。第一次删除所有的a，第二次删除所有的b
    //如果整个字符串回文就1次
    public int removePalindromeSub(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        int right = str.length - 1;
        while ( left < right){
            if(str[left] != str[right]){
                return 2;
            }
            left++;
            right--;
        }
        return 1;
    }
}
