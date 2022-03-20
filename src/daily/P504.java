package daily;

public class P504 {
    public String convertToBase7(int num) {
        if(num == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if(num < 0){
            flag = true;
            num = -1 * num;
        }
        while (num > 0){
            int mod = num % 7;
            sb.insert(0,mod);
            num /= 7;
        }
        if(flag)
            sb.insert(0,'-');
        return sb.toString();
    }

    public static void main(String[] args) {
        String res = new P504().convertToBase7(0);
        System.out.println(res);
    }
}
