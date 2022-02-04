package daily;

public class P8 {
    public int myAtoi(String s) {
        long low = Integer.MIN_VALUE;
        long high = Integer.MAX_VALUE;
        s = s.trim();
        char[] str = s.toCharArray();
        int flag = 1; //1为正，-1为负
        long res = 0;
        for(int i=0;i<str.length;i++){
            if(i == 0){
                if(str[i] == '-'){
                    flag = -1;
                    continue;
                }
                else if(str[i] == '+'){
                    flag = 1;
                    continue;
                }
            }
            if(str[i] > '9' || str[i] < '0'){
                break;
            }
            res = res * 10 + str[i] - '0';
            if(res >= high){
                return (int) (flag == 1 ? high : low);
            }
        }
        return (int) (res * flag);

    }
}
