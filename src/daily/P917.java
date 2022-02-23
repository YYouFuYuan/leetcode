package daily;

public class P917 {
    public String reverseOnlyLetters(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        int right = str.length - 1;
        while (left < right){
            while (!Character.isAlphabetic(str[left]) && left < right){
                left++;
            }
            while (!Character.isAlphabetic(str[right]) && left < right){
                right--;
            }
            char temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }
        return new String(str);
    }


    public static void main(String[] args) {
        String res = new P917().reverseOnlyLetters("Test1ng-Leet=code-Q!");
        System.out.println(res);
    }
}
