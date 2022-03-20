package daily;

public class P258 {

    public int addDigits(int num) {
        while (num > 9){
            int temp = num;
            num = 0;
            while (temp > 0){
                num += (temp % 10);
                temp /= 10;
            }
        }
        return num;
    }
}
