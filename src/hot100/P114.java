package hot100;

public class P114 {
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

    static class ReturnResult{
        public TreeNode head;
        public TreeNode tail;
        public boolean empty;
        public ReturnResult(){
            this.head = null;
            this.tail = null;
            empty = true;
        }

        public ReturnResult(TreeNode node){
            this.head = node;
            this.tail = node;
            empty = false;
        }
    }

    public ReturnResult process(TreeNode root){
        if(root == null){
            return new ReturnResult();
        }
        ReturnResult left = process(root.left);
        ReturnResult right = process(root.right);
        root.left = null;
        if(left.empty){
            root.right = right.head;
        }else{
            root.right = left.head;
            left.tail.right = right.head;
        }

        ReturnResult r = new ReturnResult(root);
        r.tail = right.tail != null ? right.tail : left.tail != null ? left.tail : root;
        r.empty = false;
        return r;
    }

    public void flatten(TreeNode root) {
        ReturnResult r = process(root);
        root = r.head;
    }
}
