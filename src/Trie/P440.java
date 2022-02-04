package Trie;

public class P440 {

    /**
     * 以当前cur为根节点的子树有多少个节点，不超过总数n的情况下
     * @param cur
     * @param n
     * @return
     */
    public int getNodes(long cur,long n){
        long total = 0;
        long next = cur + 1;    //隔壁树的根节点
        while (cur <= n){
            long nextNode =  next - cur;    //当前层应该有的总数
            total += Math.min(nextNode,n - cur + 1);    //可能超过总数n了，取最小值
            cur *= 10;
            next *= 10;
        }
        return (int) total;
    }

    /**
     * 十叉树类似前序遍历，但是要进行剪枝
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        long cur = 1;                       //当前节点位置，一开始为1
        k--;                                //还需要走k步，由于一开始走到1，默认-1
        while (k > 0){                      //走完k步后，返回最终节点的值
            int nodes = getNodes(cur,n);    //获得以当前节点为根的子树共有节点多少个
            if(nodes <= k){                 //如果当前子树下的节点小于k，说明节点没必要进入子树中，直接右移
                cur++;
                k -= nodes;
            }
            else {                          //当前子树下的节点大于k，说明答案就在子树中，进入子树
                cur *= 10;
                k--;
            }
        }
        return (int) cur;
    }

    public static void main(String[] args) {
        int res = new P440().findKthNumber(10000,10000);
        System.out.println(res);
    }
}
