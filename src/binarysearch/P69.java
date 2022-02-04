package binarysearch;

public class P69 {



    public int mySqrt(int x) {
        if(x <= 1)
            return x;
        int left = 1;
        int right = x - 1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if(x / middle > middle){
                if(x / (middle+1) < (middle+1))
                    return middle;
                left = middle + 1;
            }
            else if(x / middle < middle){
                right = middle - 1;
            }
            else return middle;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
