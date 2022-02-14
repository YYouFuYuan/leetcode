package hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class P337 {
    static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
         this.right = right;
       }
    }

    static class Node{
        public TreeNode root;
        public boolean flag;
        public Node(TreeNode root,boolean flag){
            this.root = root;
            this.flag = flag;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return flag == node.flag && Objects.equals(root, node.root);
        }

        @Override
        public int hashCode() {
            return Objects.hash(root, flag);
        }
    }
    public int process(TreeNode root, boolean flag, Map<Node,Integer> map){
        if(root == null)
            return 0;
        Node node = new Node(root,flag);
        if(map.containsKey(node))
            return map.get(node);
        //当前树偷
        int r1 = 0;
        if(flag){
            r1 = process(root.right,!flag,map) + process(root.left,!flag,map) + root.val;
        }
        //当前树不偷
        int r2 = process(root.left,true,map) + process(root.right,true,map);
        map.put(node,Math.max(r1,r2));
        return Math.max(r1,r2);

    }
    public int rob(TreeNode root) {
        return process(root,true,new HashMap<>());
    }
}
