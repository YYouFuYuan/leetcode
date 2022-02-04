package daily;

public class P2047 {

    public int countValidWords(String sentence) {
        int ret = 0;
        String[] strs = sentence.split(" ");
        for(String str : strs){
            str = str.trim();
            char[] charArray = str.toCharArray();
            if(charArray.length > 0){
                int count = 0;
                int count2 = 0;
                boolean flag = true;
                for(int i=0;i<charArray.length;i++){
                    if(charArray[i] >= '0' && charArray[i] <='9') {
                        flag = false;
                        break;
                    }
                    if(charArray[i] >= 'a' && charArray[i] <='z')
                        continue;
                    if (charArray[i] == '-'){
                        count++;
                        if(count>1 || i == 0 || i == charArray.length-1
                                || charArray[i-1]>'z' || charArray[i-1]< 'a' || charArray[i+1]>'z' || charArray[i+1]< 'a') {
                            flag = false;
                            break;
                        }
                    }
                    else if(charArray[i] == '!' || charArray[i] == '.' || charArray[i] == ','){
                        count2++;
                        if(count2 > 1 || i != charArray.length - 1){
                            flag = false;
                            break;
                        }
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    ret++;
            }

        }
        return ret;
    }


    public static void main(String[] args) {
        String str = "cat and  dog";
        System.out.println(new P2047().countValidWords(str));
    }
}
