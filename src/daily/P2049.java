package daily;

import java.util.ArrayList;
import java.util.List;

public class P2049 {

    List<Integer>[] children;
    int n;

    public int countHighestScoreNodes1(int[] parents) {
        n = parents.length;
        children = new List[n];
        for(int i=0;i<n;i++){
            children[i] = new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            int p = parents[i];
            if(p == -1){
                continue;
            }
            children[p].add(i);
        }
        dfs(0);
        return res;
    }

    /**
     * 返回以自身节点为根的子树节点个数
     * @param i
     */
    private int dfs(int i) {
        long score = 1;
        int parentSize = n - 1; //扣除自身
        for(int c : children[i]){
            int t = dfs(c);
            score *= t; // 乘上左右子树的个数
            parentSize -= t;  // 扣除左右子树，所以parentSize是父节点所在子树的节点个数
        }
        if(i != 0){ //不是根节点，必然有父节点子树
            score *= parentSize;
        }
        //统计
        if(score == maxScore){
            res++;
        }
        else if(score > maxScore){
            res = 1;
            maxScore = score;
        }
        //返回自身子树的节点个数
        return n - parentSize;
    }



    //树形dp
    static class TreeNode{
        public TreeNode left;
        public TreeNode right;
    }

    public TreeNode getRoot(int[] parents){
        int n = parents.length;
        TreeNode[] nodes = new TreeNode[n];
        for(int i=0;i<n;i++){
            nodes[i] = new TreeNode();
        }
        for(int i=0;i<n;i++){
            int p = parents[i];
            if(p == -1){
                continue;
            }
            if(nodes[p].left == null){
                nodes[p].left = nodes[i];
            }
            else nodes[p].right = nodes[i];
        }
        return nodes[0];
    }



    public int dfs(TreeNode root){
        if(root == null)
            return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        long score = 1;
        if(l != 0){
            score *= l;
        }
        if(r != 0){
            score *= l;
        }
        int parentSize = size - l - r - 1;
        if(parentSize != 0){
            score *= parentSize;
        }
        //统计
        if(score == maxScore){
            res++;
        }
        else if(score > maxScore){
            res = 1;
            maxScore = score;
        }
        return l + r + 1;
    }

    public int size;

    long maxScore = 0;
    int res = 0;
    public int countHighestScoreNodes(int[] parents) {
        TreeNode root = getRoot(parents);
        size = parents.length;
        dfs(root);
        return res;
    }

    public static void main(String[] args) {
        new P2049().countHighestScoreNodes(new int[]{-1,2,0,2,0});
    }
}
