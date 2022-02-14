package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P101 {
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

    public void print(List<Integer> q){
        int n = q.size();
        for(int i=0;i<n;i++){
            System.out.print(q.get(i) + " ");
        }
        System.out.println();
    }
    public boolean isSymmetric(TreeNode root) {

        Queue<TreeNode> queue1 = new LinkedList<>();
        List<Integer> q1 = new ArrayList<>();
        queue1.add(root);
        while (!queue1.isEmpty()){
            int size = queue1.size();
            for(int i=0;i<size;i++){
                TreeNode cur = queue1.poll();
                if(cur != null){
                    q1.add(cur.val);
                    queue1.add(cur.left);
                    queue1.add(cur.right);
                }
                else {
                    q1.add(-101);
                }
            }
        }

        Queue<TreeNode> queue2 = new LinkedList<>();
        List<Integer> q2 = new ArrayList<>();
        queue2.add(root);
        while (!queue2.isEmpty()){
            int size = queue2.size();
            for(int i=0;i<size;i++){
                TreeNode cur = queue2.poll();
                if(cur != null){
                    q2.add(cur.val);
                    queue2.add(cur.right);
                    queue2.add(cur.left);
                }
                else {
                    q2.add(-101);
                }
            }
        }
        print(q1);
        print(q2);
        int n = q1.size();
        for(int i=0;i<n;i++){
            if(q1.get(i) != q2.get(i))
                return false;
        }
        return true;
     }
}
