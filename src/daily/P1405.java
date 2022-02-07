package daily;

import java.util.Arrays;
import java.util.Comparator;

public class P1405 {

    static class Pair{
        public int count;
        public char ch;
        public Pair(char ch,int count){
            this.ch = ch;
            this.count = count;
        }
    }
    //贪心模拟
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        Pair[] pairs = new Pair[]{new Pair('a',a),new Pair('b',b),new Pair('c',c)};

        while (true){
            //按出现次数从大到小排序,这里可以换成大根堆
            Arrays.sort(pairs, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o2.count - o1.count;
                }
            });
            //从所有字符中最多的那个选取一个字符
            boolean hasNext = false;    //是否还有可操作的字符
            for(Pair pair : pairs){
                if(pair.count <= 0){
                    break;
                }
                int size = sb.length();
                //当前字符与前两个一样，跳过
                if(size >= 2 && sb.charAt(size - 1) == sb.charAt(size - 2) && sb.charAt(size - 1) == pair.ch){
                    continue;
                }
                //找到当前可操作的字符
                sb.append(pair.ch);
                pair.count--;
                hasNext = true;
                break;
            }
            //没有可操作的字符了，退出
            if(!hasNext){
                break;
            }
        }
        return sb.toString();
    }
}
