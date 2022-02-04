package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {
    //-----------------------------------子集树------------------------------------------------
    public static void printRes(List<Character> res){
        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
        System.out.println();
    }

    public static void process(char[] str,int i,List<Character> res){
        if( i == str.length){
            printRes(res);
            return;
        }

        res.add(str[i]);
        process(str,i+1,res);
        res.remove(res.size()-1);
        process(str,i+1,res);
    }

    /*** 子集树 */
    public static void strChildCollection(){
        process(new char[]{'a','b','c'},0,new ArrayList<>());
    }


    //--------------------------------排列树--------------------------------------


    public static void strAllPermutations(){
        List<List<Character>> list = new ArrayList<>();
        List<Character> str = new ArrayList<>();
        strAllPermutationsProcess(new char[]{'a','b','c','d','e'},0,list,new boolean[26],str);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }

    private static void strAllPermutationsProcess(char[] chars, int i, List<List<Character>> list, boolean[] visited, List<Character> str) {
        if(i == chars.length){
            List<Character> r = new ArrayList<>(str);
            list.add(r);
            return;
        }
        for(int j=0;j<chars.length;j++){
            if(!visited[chars[j] - 'a']){
                visited[chars[j] - 'a'] = true;
                str.add(chars[j]);
                strAllPermutationsProcess(chars,i+1,list,visited,str);
                str.remove(str.size() - 1);
                visited[chars[j] - 'a'] = false;
            }
        }
    }

    public static void main(String[] args) {
        strAllPermutations();
    }
}
