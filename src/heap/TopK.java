package heap;

import java.util.HashMap;
import java.util.Map;

public class TopK {

    static class Node{
        public String str;      //字符串
        public int times;       //出现次数
        public Node(String str,int times){
            this.str = str;
            this.times = times;
        }
        public Node(){}
    }

    static class StrHeap{
        public Node[] nodeArray; //堆数组
        public int heapSize;    //堆容量
        public int curIndex;    //当前堆的最后一个元素索引
        public StrHeap(int K){
            this.heapSize = K;
            this.nodeArray = new Node[this.heapSize];
            this.curIndex = -1;
        }

        public void swap(int AIndex,int Bindex,Map<Node,Integer> map){
            Node c = nodeArray[AIndex];
            nodeArray[AIndex] = nodeArray[Bindex];
            nodeArray[Bindex] = c;

            map.put(nodeArray[Bindex],Bindex);
            map.put(nodeArray[AIndex],AIndex);
        }

        public void heapInsert(Node node,Map<Node,Integer> map){
            this.nodeArray[++this.curIndex] = node;
            int index = this.curIndex;
            map.put(node,index);
            while (nodeArray[index].times < nodeArray[(index-1) / 2].times){
                swap(index,(index-1) / 2,map);
                index = (index - 1) / 2;
            }
        }

        public void heapfipy(int index,Map<Node,Integer> map){
            int leftChilid = index * 2 + 1;
            while (leftChilid < this.heapSize){
                int smallest = (leftChilid + 1) < heapSize && this.nodeArray[leftChilid + 1].times < this.nodeArray[leftChilid].times
                        ? (leftChilid + 1) : leftChilid;
                smallest = this.nodeArray[index].times < this.nodeArray[smallest].times ? index : smallest;
                if(smallest == index)
                    break;
                swap(smallest,index,map);
                index = smallest;
                leftChilid = 2 * index + 1;
            }
        }
    }


    public StrHeap strHeap;
    public Map<String,Node> strToNode;
    public Map<Node,Integer> nodeToIndex;

    public TopK(int k){
        strHeap = new StrHeap(k);
        strToNode = new HashMap<>();
        nodeToIndex = new HashMap<>();
    }

    public void putStr(String str){
        //判断该字符串是否出现过
        Node node = strToNode.getOrDefault(str,null);
        if(node != null){   //出现过
            node.times++;
            //得到当前node在堆的位置
            int index = nodeToIndex.get(node);
            if(index == -1){ //之前没有在堆里
                //此时堆里明显是满的，因为没有删除操作,判断是否大于门槛
                if(strHeap.nodeArray[0].times < node.times){ //比门槛高 入堆
                    Node prevNode = strHeap.nodeArray[0];
                    nodeToIndex.put(prevNode,-1);
                    strHeap.nodeArray[0] = node;
                    nodeToIndex.put(node,0);

                }
            }
            else {
                strHeap.heapfipy(index,nodeToIndex); // 已经在堆。直接调整
            }
        }
        else {  //没出现过
            node = new Node(str,1);
            nodeToIndex.put(node,-1);
            strToNode.put(str,node);
            //堆没满
            if(strHeap.curIndex < strHeap.heapSize - 1){
                this.strHeap.heapInsert(node,nodeToIndex);
            }
            else {
             //堆满了，且只有1，不可能入堆
            }
        }

    }

    public void printTok(){
        System.out.println("Tok:");
        for(int i=0;i<= strHeap.curIndex;i++){
            System.out.println(strHeap.nodeArray[i].str + " times:" + strHeap.nodeArray[i].times );
        }
    }

    public static void main(String[] args) {
        TopK topK = new TopK(3); String[] str = new String[] {"abc","efg","hij","abc","lmn","opq","lmn","lmn","hij","hij"};
        for(int i=0;i< str.length;i++){
            topK.putStr(str[i]); System.out.println(i+"-------------------------------------------- ");
            topK.printTok();
        }
    }

}
