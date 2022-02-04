package heap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class P506 {

    static class Entry{
        public int score;
        public int index;
        public Entry(int score,int index){
            this.index = index;
            this.score = score;
        }
        public Entry(){}
    }

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        //按键建立大根堆
        PriorityQueue<Entry> queue = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o2.score - o1.score;
            }
        });
        for (int i=0;i<n;i++){
            queue.add(new Entry(score[i],i));
        }
        int count = 0;
        while (!queue.isEmpty()){
            count++;
            Entry entry = queue.poll();
            if(count == 1){
                result[entry.index] = "Gold Medal";
            }
            else if(count == 2){
                result[entry.index] = "Silver Medal";
            }
            else if(count == 3){
                result[entry.index] = "Bronze Medal";
            }
            else result[entry.index] = Integer.toString(count);
        }
        return result;
    }
}
