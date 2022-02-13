package week;

public class P6004 {
    public int countOperations(int num1, int num2) {
        int res = 0;
        while (num1 != 0 && num2 != 0){
            if(num1 >= num2 ){
                num1 -= num2;
            }
            else num2 -= num1;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        int r = new P6004().countOperations(10,10);
        System.out.println(r);
    }

}
