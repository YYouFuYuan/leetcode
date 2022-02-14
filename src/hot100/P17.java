package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17 {

    public void process(char[] digits,int curIndex,List<String> res,StringBuilder sb,Map<Character,String> map){
        if(curIndex == digits.length){
            res.add(sb.toString());
            return;
        }
        String s = map.get(digits[curIndex]);
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i));
            process(digits,curIndex + 1,res,sb,map);
            sb.deleteCharAt(sb.length()-1);
        }

    }
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if("".equals(digits))
            return res;
        char[] chars = digits.toCharArray();
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        process(chars,0,res,new StringBuilder(),map);
        return res;
    }
}
