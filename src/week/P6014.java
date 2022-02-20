package week;

public class P6014 {
    public String repeatLimitedString(String s, int repeatLimit) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        for(int i=0;i<str.length;i++){
            count[ str[i] - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int index = 25;
        int preIndex = -1;
        while (index > 0 ){
            while (index >=0 &&count[index] == 0){
                index--;
            }
            if(preIndex == index)
                break;
            int secondIndex = index-1;
            if(index > 0){
                while (secondIndex >= 0 && count[secondIndex] == 0){
                    secondIndex--;
                }

                int k = 0;
                while (k < repeatLimit && count[index] > 0){
                    sb.append((char)(index + 'a'));
                    count[index]--;
                    k++;
                    preIndex = index;
                }
                if(count[index] != 0 && secondIndex>=0){
                    sb.append((char) (secondIndex + 'a'));
                    count[secondIndex]--;
                    preIndex = secondIndex;
                }
            }
            else {
                if(index < 0)
                    break;
                int k = 0;
                while (k < repeatLimit && count[index] > 0){
                    sb.append((char)(index + 'a'));
                    count[index]--;
                    k++;
                    preIndex = index;
                }
                break;
            }


        }


        return sb.toString();
    }

    public static void main(String[] args) {
        String s = new P6014().repeatLimitedString("babbbbb",1);
        System.out.println(s);
    }
}
