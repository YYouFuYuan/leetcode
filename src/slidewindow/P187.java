package slidewindow;

import java.util.*;

public class P187 {

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String,Integer> map = new HashMap();
        int n = s.length();
        List<String> res = new ArrayList<>();
        for(int i=0;i<n-10;i++){
            String sub = s.substring(i,i+10);
            if(map.containsKey(sub) && map.get(sub) == 1){
                res.add(sub);
            }
            map.put(sub,map.getOrDefault(sub,0)+1);

        }
        return res;
    }

    public static void main(String[] args) {
        new P187().findRepeatedDnaSequences("AAAAAAAAAAA");
    }
}
