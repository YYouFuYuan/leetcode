package map;

import java.util.*;

public class JZ113 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer,Integer> inDegree = new HashMap<>();
        for(int i=0;i<prerequisites.length;i++){
            //建立图的先后连接关系
            List<Integer> nexts = graph.getOrDefault(prerequisites[i][1], new ArrayList<>());
            nexts.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1],nexts);

            //统计入度
            inDegree.put(prerequisites[i][0],inDegree.getOrDefault(prerequisites[i][0],0) + 1);
        }
        int[] res = new int[numCourses];
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses;i++){
            if(inDegree.getOrDefault(i,0) == 0){
                res[index++] = i;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            List<Integer> nexts = graph.getOrDefault(cur,null);
            if(nexts != null){
                for(int i=0;i<nexts.size();i++){
                    inDegree.put(nexts.get(i), inDegree.get(nexts.get(i)) - 1);
                    if(inDegree.get(nexts.get(i)) == 0){
                        queue.add(nexts.get(i));
                        res[index++] = nexts.get(i);
                    }
                }
            }
        }
        return index == numCourses - 1 ? res : new int[]{};
    }

    public static void main(String[] args) {
        new JZ113().findOrder(2,new int[][]{{1,0}});
    }
}
