package Trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    static class TrieNode{
        public int path;    //记录经过当前节点的字符串个数
        public int end;     //记录以当前节点终止的字符串个数
        public Map<Character,TrieNode> nexts; //记录当前节点的后续节点
        public TrieNode(){
            this.path = 0;
            this.end = 0;
            nexts = new HashMap<>();
        }
    }

    private TrieNode root;
    public Trie(){
        root = new TrieNode();
    }


    /**
     * 前缀树保存了多少字符串
     * @return
     */
    public int size(){
        return this.root.path;
    }

    /**
     * 插入字符串
     * @param word
     */
    public void insert(String word){
        if(word == null)
            return;
        char[] chars = word.toCharArray();
        TrieNode node = this.root;
        node.path++;
        for(int i=0;i<chars.length;i++){
            if(!node.nexts.containsKey(chars[i])){
                node.nexts.put(chars[i],new TrieNode());
            }
            node = node.nexts.get(chars[i]);
            node.path++;
        }
        node.end++;
    }

    /**
     * 查询字符串是否在前缀树中
     * @param word
     * @return
     */
    public int search(String word){
        if(word == null)
            return 0;
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for(int i=0;i<chars.length;i++){
            if(!node.nexts.containsKey(chars[i])){
                return 0;
            }
            node = node.nexts.get(chars[i]);
        }
        return node.end;
    }


    /**
     * 删除字符串
     * @param word
     */
    public void delete(String word){
        if(this.search(word) != 0){
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.path--;
            for(int i=0;i<chars.length;i++){
                if(--node.nexts.get(chars[i]).path == 0){   //节点-1为0，后续节点无用
                    node.nexts.remove(chars[i]);
                    return;
                }
                node = node.nexts.get(chars[i]);
            }
            node.end--;
        }
    }

    /**
     * 查询以pre为前缀的字符串有几个
     * @param pre
     * @return
     */
    public int prefixNumber(String pre){
        if(pre == null)
            return 0;
        char[] chars = pre.toCharArray();
        TrieNode node = root;
        for(int i=0;i<chars.length;i++){
            if(!node.nexts.containsKey(chars[i])){
                return 0;
            }
            node = node.nexts.get(chars[i]);
        }
        return node.path;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] strs = new String[]{"ab","abc","abd","abe","abe"};
        for(String str : strs){
            trie.insert(str);
        }
        System.out.println("前缀树中有多少字符串："+trie.size());
        trie.delete("abe");
        System.out.println("前缀树中有多少以当前字符串为前 缀："+trie.prefixNumber("ab"));
        trie.delete("ab");
        System.out.println("前缀树中有多少以当前字符串为前 缀："+trie.prefixNumber("ab"));
        System.out.println("前缀树中有多少字符串："+trie.size());
        trie.delete("abc");
        System.out.println("前缀树中有多少字符串："+trie.size());
    }
}
