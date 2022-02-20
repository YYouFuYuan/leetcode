package week;

public class P6012 {
    public boolean check(int num){
        int sum = 0;
        while (num > 0){
            int a = num % 10;
            sum += a;
            num = num / 10;
        }
        if(sum % 2 == 0)
            return true;
        return false;
    }
    public int countEven(int num) {
        int ans = 0;
        for(int i=1;i<=num;i++){
            if (check(i)){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = new P6012().countEven(30);
        System.out.println(a);
    }
}
