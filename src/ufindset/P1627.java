package ufindset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class P1627 {
    //并查集
    static class UFindSet{
        public HashMap<Integer,Integer> fatherMap = new HashMap<>();
        public HashMap<Integer,Integer> rankMap = new HashMap<>();

        public UFindSet(int n){
            for(int i=1;i<=n;i++){
                fatherMap.put(i,i);
                rankMap.put(i,1);
            }
        }

        public int findFather(int value){
            Stack<Integer> path = new Stack<>();
            while (value != fatherMap.get(value)){
                path.push(value);
                value = fatherMap.get(value);
            }
            while (!path.isEmpty()){
                int e = path.pop();
                fatherMap.put(e,value);
            }
            return value;
        }

        public void union(int a,int b){
            int aFather = fatherMap.get(a);
            int bFather = fatherMap.get(b);
            if(aFather != bFather){
                int aRank = rankMap.get(aFather);
                int bRank = rankMap.get(bFather);
                if(aRank <= bRank){
                    fatherMap.put(aFather,bFather);
                    rankMap.put(bFather,aRank + bRank);
                }
                else {
                    fatherMap.put(bFather,aFather);
                    rankMap.put(aFather,aRank + bRank);
                }
            }
        }
    }
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UFindSet set = new UFindSet(n);
        int m = queries.length;
        for(int i=threshold+1;i<=n;i++){
            for(int j=2;j*i <=n;j++ ){
                set.union(i,j);
            }
        }
        List<Boolean> res = new ArrayList<>();
        for(int[] q : queries){
            if(set.findFather(q[0]) == set.findFather(q[1]))
                res.add(true);
            else res.add(false);
        }
        return res;
    }

    public static void main(String[] args) {
        new P1627().areConnected(6,0,new int[][]{{4,5},{3,4},{3,2},{2,6},{1,3}});
    }
}
