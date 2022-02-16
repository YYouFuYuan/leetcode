package doublePoint;

public class JZ58 {
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        char[] result = new char[chars.length];
        for(int i=0;i<length - n;i++){
            result[i] = chars[n + i];
        }
        for(int i=length - n,j=0; i<length;i++,j++){
            result[i] = chars[j];
        }
        return new String(result);
    }
}
