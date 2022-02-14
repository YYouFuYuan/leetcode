package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P49 {

    public boolean isEqual(Map<Character,Integer> map1, Map<Character,Integer> map2){
        if(map1.size() != map2.size())
            return false;
        for(Character c : map1.keySet()){
            int value1 = map1.get(c);
            int value2 = map2.getOrDefault(c,0);
            if(value1 != value2)
                return false;
        }
        return true;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,Map<Character,Integer>> map = new HashMap();
        Map<String,Integer> countMap = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for(String s : strs){
            if(countMap.containsKey(s)){
                countMap.put(s,countMap.get(s) + 1);
                continue;
            }
            char[] chars = s.toCharArray();
            Map<Character,Integer> chMap = new HashMap<>();
            for(int i=0;i<chars.length;i++){
                chMap.put(chars[i],chMap.getOrDefault(chars[i],0) + 1);
            }
            map.put(s,chMap);
            countMap.put(s,1);
        }

        for(String s : map.keySet()){
            Map<Character,Integer> chMap = map.get(s);
            int index = -1;
            for(int i=0;i<res.size();i++){
                String head = res.get(i).get(0);
                if(isEqual(map.get(head),chMap)){
                    index = i;
                }
            }
            if(index != -1){
                for(int i=0;i<countMap.get(s);i++)
                    res.get(index).add(s);

            }
            else {
                List<String> row = new ArrayList<>();
                for(int i=0;i<countMap.get(s);i++)
                    row.add(s);
                res.add(row);
            }
        }

        return res;

    }

    public static void main(String[] args) {
        String[] strs = {"", ""};
        new P49().groupAnagrams(strs);
    }
}
