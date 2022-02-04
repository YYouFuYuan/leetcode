package tree;

import java.util.*;

public class Tree {
     public class TreeNode {
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
    //非递归前序  中 左 右
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
         if(root == null)
             return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            root = stack.pop();
            result.add(root.val);
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left != null){
                stack.push(root.left);
            }
        }
        return result;
    }

    //非递归后序 左 右 中
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            root = stack1.pop();
            stack2.push(root.val);
            if(root.left != null)
                stack1.push(root.left);
            if(root.right != null){
                stack1.push(root.right);
            }
        }
        while (!stack2.isEmpty()){
            result.add(stack2.pop());
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if( root != null){
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            for(int i=0;i<size;i++){
                root = queue.poll();
                row.add(root.val);
                if(root.left != null)
                    queue.add(root.left);
                if(root.right != null)
                    queue.add(root.right);
            }
            result.add(row);
        }
        return result;
    }
    static class TreeHeight{
         boolean balance = true;
         int height = 0;
         public TreeHeight(boolean balance, int height){
             this.balance = balance;
             this.height = height;
         }
         public TreeHeight(){}
    }

    public TreeHeight process(TreeNode root){
         if(root == null)
             return new TreeHeight(true,0);
         TreeHeight L = process(root.left);
         TreeHeight R = process(root.right);
         TreeHeight treeHeight = new TreeHeight(false,Math.max(L.height,R.height) + 1);
         if(L.balance && R.balance && Math.abs(L.height - R.height) < 2){
             treeHeight.balance = true;
         }
         return treeHeight;
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).balance;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
         if(root == p || root == q)
            return root;

         TreeNode L = lowestCommonAncestor(root.left,p,q);
         TreeNode R = lowestCommonAncestor(root.right,p,q);
         if(L != null && R != null)
             return root;
         else if(L != null){
             return L;
         }
         else {
             return R;
         }
    }
}
