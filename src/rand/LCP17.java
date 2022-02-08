package rand;

public class LCP17 {
    public int calculate(String s) {
        char[] cmd = s.toCharArray();
        int x = 1;
        int y = 0;
        for(char ch : cmd){
            if(ch == 'A'){
                x = 2 * x + y;
            }
            else {
                y = 2 * y + x;
            }
        }
        return x + y;
    }
}
