package daily;

public class P2000 {
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if(index != -1){
            char[] array = word.toCharArray();
            for(int i=0;i< index / 2;i++){
                char temp = array[i];
                array[i] = array[index-i];
                array[index-i] = temp;
            }
            word = new String(array);
        }
        return word;
    }
}
