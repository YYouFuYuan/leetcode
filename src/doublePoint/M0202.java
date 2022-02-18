package doublePoint;



public class M0202 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public int kthToLast(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        while (k>0){
            k--;
            fast = fast.next;
        }
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
    }
}
