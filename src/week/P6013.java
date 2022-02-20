package week;

public class P6013 {

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public ListNode mergeNodes(ListNode head) {
        ListNode count = null;
        ListNode p = head;
        int sum = 0;
        while (p != null){
            if(p.val != 0 && count == null){
                count = p;
                sum = p.val;
            }
            else if(p.val != 0){
                sum += p.val;
            }
            else {
                //p.val ==0
                if(count != null){
                    count.val = sum;
                    count.next = p;
                    sum = 0;
                    count = null;
                }
            }
            p = p.next;
        }
        ListNode myHead = new ListNode();
        ListNode res = myHead;

        p = head;
        while (p != null) {
            if(p.val != 0){
                res.next = p;
                res = p;
            }
            p = p.next;
        }

        return myHead;
    }
}
