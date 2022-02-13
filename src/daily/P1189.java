package daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1189 {

    public int maxNumberOfBalloons(String text) {
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = text.toCharArray();
        Set<Character> set = new HashSet<>();
        set.add('b');
        set.add('a');
        set.add('l');
        set.add('o');
        set.add('n');
        for(int i=0;i<chars.length;i++){
            if(set.contains(chars[i])){
                map.put(chars[i],map.getOrDefault(chars[i],0) + 1);
            }
        }
        int res1 = Math.min(
                Math.min(map.getOrDefault('b',0),map.getOrDefault('a',0)),
                        map.getOrDefault('n',0));
        int rest = Math.min(map.getOrDefault('o',0),map.getOrDefault('l',0));
        rest /= 2;
        return Math.min(res1,rest);
    }
}
