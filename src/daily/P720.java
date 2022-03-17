package daily;

import java.util.*;

public class P720 {

    public String longestWord2(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if(s.length() != t1.length())
                    return s.length() - t1.length();//长度从小到大
                return t1.compareTo(s); //字典序
            }
        });

        Set<String> set = new HashSet<>();
        set.add("");
        String res = "";
        for(String word : words){
            if(set.contains(word.substring(0,word.length()-1))){
                res = word;
                set.add(word);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String res = new P720().longestWord(new String[]{"w","wo","wor","worl", "world"});
        System.out.println(res);
    }


    static class Trie{
        public Trie[] children = new Trie[26];
        public boolean isEnd = false;

    }
    public static void insert(String word,Trie root){

        char[] str = word.toCharArray();
        for(int i=0;i<str.length;i++){
            if(root.children[str[i] - 'a'] == null){
                root.children[str[i] - 'a'] = new Trie();
            }
            root = root.children[str[i] - 'a'];
        }
        root.isEnd = true;
    }

    public static boolean search(String word,Trie root){
        char[] str = word.toCharArray();
        for(int i=0;i<str.length;i++){
            if(root.children[str[i] - 'a'] == null || !root.isEnd){
                return false;
            }
            root = root.children[str[i] - 'a'];
        }
        return root != null && root.isEnd;
    }


    public String longestWord(String[] words) {
        Trie root = new Trie();
        root.isEnd = true;
        for(String word : words){
            insert(word,root);
        }
        String res = "";
        for(String word : words){
            if(search(word,root)){
                if(word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)){
                    res = word;
                }
            }
        }
        return res;
    }
}
