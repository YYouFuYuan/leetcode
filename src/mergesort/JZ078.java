package mergesort;

public class JZ078 {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode mergeSort(ListNode[] lists,int left,int right){
        if(left == right)
            return lists[left];
        if(left > right){
            return null;
        }
        int mid = left + ((right - left) >> 1);
        ListNode first = mergeSort(lists,left,mid);
        ListNode second = mergeSort(lists,mid+1,right);
        return merge(first,second);
    }

    private ListNode merge(ListNode first, ListNode second) {
        ListNode p1 = first;
        ListNode p2 = second;
        ListNode result = new ListNode();
        ListNode r = result;
        while (p1 != null && p2 != null){
            if(p1.val < p2.val){
                result.next = p1;
                p1 = p1.next;
            }
            else {
                result.next = p2;
                p2 = p2.next;
            }
            result = result.next;
        }

        if (p1 != null){
            result.next = p1;
        }
        if (p2 != null){
            result.next = p2;
        }
        return  r.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeSort(lists,0, lists.length-1);
    }
}
