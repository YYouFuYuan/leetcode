package rand;

public class P374 {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right){
            int middle = left + ( (right - left) >> 1);
            int ans = guess(middle);
            if(ans == 0){
                return middle;
            }
            else if(ans == 1){
                left = middle + 1;
            }
            else right = middle - 1;
        }
        return -1;
    }

    private int guess(int middle) {
        return 0;
    }
}
