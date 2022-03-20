package daily;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
public class P589 {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public void dfs(Node root,List<Integer> res){
        if(root == null)
            return;
        res.add(root.val);
        for(Node child : root.children){
            dfs(child,res);
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root,res);
        return res;
    }
}
