package daily;

public class P6 {

    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        int n = str.length;
        int first = (numRows - 2) * 2 + 2;
        for(int i=0;i<n;i+=first){
            sb.append(str[i]);
        }

        for(int i=1;i<=numRows-2;i++){
            int flag = 0;
            for(int j=i;j<n;){
                sb.append(str[j]);
                if(flag == 0){
                    j+= ((numRows-1-i)*2);
                    flag = 1;
                }
                else {
                    j += (i*2);
                    flag = 0;
                }

            }
        }

        for(int i=numRows-1;i<n;i+=first){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = new P6().convert("AB",2);
        System.out.println(s);
    }
}
