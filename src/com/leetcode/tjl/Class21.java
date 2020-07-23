public class Class21 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(2);
//        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(4);
        ListNode l3 = mergeTwoLists(l1, l2);
        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode l3 = new ListNode();
        ListNode lastNode = new ListNode();
        l3.next = lastNode;
        while (l1 != null) {
            if (l2 == null) {
                lastNode.next = new ListNode(l1.val, l1.next);
                break;
            } else if (l1.val < l2.val) {
                lastNode.next = l1;
                lastNode = lastNode.next;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                lastNode = lastNode.next;
                l2 = l2.next;
            }
        }
        if (l2!=null){
            lastNode.next = new ListNode(l2.val, l2.next);
        }
        return l3.next.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2==null){
            return l1;
        }
        if (l1.val<l2.val){
            ListNode l3=new ListNode(l1.val);
            l3.next=mergeTwoLists2(l1.next,l2);
            return l3;
        }else {
            ListNode l3=new ListNode(l2.val);
            l3.next=mergeTwoLists2(l1,l2.next);
            return l3;
        }
    }
    }
