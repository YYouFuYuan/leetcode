package tree;


public class P897 {

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
    static class Result{
  public TreeNode l; //子树中的最左树
  public TreeNode r; //子树的最右树
  public Result(){}
  public Result(TreeNode left,TreeNode right){
   this.l = left;
   this.r = right;
  }
 }

 public Result process(TreeNode root) {
  if(root == null)
   return null;
  Result L = process(root.left);
  Result R = process(root.right);
  Result cur = new Result();
  cur.l = root;
  cur.r = root;
  System.out.println("cur:" + root.val);


  if(L != null){
   System.out.println("L:" + L.l.val);
   System.out.println("L:" + L.r.val);
   cur.l = L.l;
   L.r.right = root;
   root.left = null;
  }
  if(R != null){
   System.out.println("R:" + R.l.val);
   System.out.println("R:" + R.l.val);
   root.right = R.l;
   cur.r = R.r;
  }
  return cur;
 }

 public TreeNode increasingBST(TreeNode root) {
  return process(root).l;
 }

}
