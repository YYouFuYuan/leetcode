package map;

import java.util.*;

public class Graph {

    static class Node{
        public int value;   //当前节点的值
        public int in;      //入度
        public int out;     //出度
        public ArrayList<Node> nexts; //当前点能到达的点
        public ArrayList<Edge> edges; //当前点相连的边
        public Node(int value){
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    static class Edge{
        public int weight;  //权重
        public Node from;   //起点
        public Node to;     //终点
        public Edge(int weight,Node from,Node to){
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

    }


    public HashMap<Integer,Node> nodes; //点集
    public HashSet<Edge> edges;         //边集

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    //kruscal
//    public static class UnionFind{
//        private HashMap<Node,Node> fatherMap;   //父亲节点
//        private HashMap<Node,Integer> rankMap;  //子集的个数
//
//        public UnionFind(){
//            fatherMap = new HashMap<>();
//            rankMap = new HashMap<>();
//        }
//
//        private Node findFather(Node n){
//            Stack<Node> path = new Stack<>();
//            while (fatherMap.get(n) != n){
//                path.push(n);
//                n = fatherMap.get(n);
//            }
//            while (!path.isEmpty()){
//                fatherMap.put(path.pop(),n);
//            }
//            return n;
//        }
//
//        public void makeSets(Collection<Node> nodes){
//            fatherMap.clear();
//            rankMap.clear();
//            for(Node node : nodes){
//                fatherMap.put(node,node);
//                rankMap.put(node,1);
//            }
//        }
//
//        public boolean isSameSet(Node a,Node b){
//            return findFather(a) == findFather(b);
//        }
//
//        public void union(Node a,Node b){
//            if(a == null || b == null){
//                return;
//            }
//            Node aFather = findFather(a);
//            Node bFather = findFather(b);
//            if(aFather != bFather){
//                int aRank = rankMap.get(aFather);
//                int bRank = rankMap.get(bFather);
//                if(aRank <= bRank){
//                    fatherMap.put(aFather,bFather);
//                    rankMap.put(bFather,aRank + bRank);
//                }
//                else {
//                    fatherMap.put(bFather,aFather);
//                    rankMap.put(aFather,aRank + bRank);
//                }
//            }
//        }
//    }

    static class UnionFind{
        public HashMap<Node,Node> fatherMap;
        public HashMap<Node,Integer> rankMap;
        public UnionFind(){
            this.fatherMap = new HashMap<>();
            this.rankMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            rankMap.clear();
            for(Node node : nodes){
                this.rankMap.put(node,1);
                this.fatherMap.put(node,node);
            }
        }

        public Node findFather(Node n){
            Stack<Node> path = new Stack<>();
            while (this.fatherMap.get(n) != n){
                path.add(n);
                n = this.fatherMap.get(n);
            }
            while (!path.isEmpty()){
                this.fatherMap.put(path.pop(),n);
            }
            return n;
        }

        public boolean isSameSet(Node a,Node b){
            return this.findFather(a) == this.findFather(b);
        }

        public void union(Node a,Node b){
            Node aFather = this.fatherMap.get(a);
            Node bFather = this.fatherMap.get(b);
            if(a != b){
                int aFank = this.rankMap.get(aFather);
                int bFank = this.rankMap.get(bFather);
                if(aFank <= bFank){
                    this.fatherMap.put(bFather,aFather);
                    this.rankMap.put(aFather,aFank + bFank);
                }
                else {
                    this.fatherMap.put(aFather,bFather);
                    this.rankMap.put(bFather,aFank + bFank);
                }
            }
        }
    }

    public static Set<Edge> kruscalMST(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        queue.addAll(graph.edges);
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()){
            Edge edge = queue.poll();
            if(!unionFind.isSameSet(edge.from, edge.to)){ //没有连通的边
                unionFind.union(edge.from,edge.to);
                result.add(edge);
            }
        }
        return result;
    }
}
