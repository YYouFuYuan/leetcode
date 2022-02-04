package daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P884 {

    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String,Integer> s1Map = new HashMap<>();
        Map<String,Integer> s2Map = new HashMap<>();
        String[] s1Char = s1.split(" ");
        String[] s2Char = s2.split(" ");
        for(String s : s1Char){
            s1Map.put(s,s1Map.getOrDefault(s,0) + 1);
        }
        for(String s : s2Char){
            s2Map.put(s,s2Map.getOrDefault(s,0) + 1);
        }
        List<String> result = new ArrayList<>();
        for(String s : s1Map.keySet()){
            if(s1Map.get(s) == 1 && !s2Map.containsKey(s)){
                result.add(s);
            }
        }
        for(String s : s2Map.keySet()){
            if(s2Map.get(s) == 1 && !s1Map.containsKey(s)){
                result.add(s);
            }
        }
        String[] res = new String[result.size()];
        for(int i=0;i<result.size();i++){
            res[i] = result.get(i);
        }
        return res;
    }

}
