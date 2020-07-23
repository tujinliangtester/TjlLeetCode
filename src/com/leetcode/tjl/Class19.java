public class Class19 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode seconde = new ListNode(2);
        head.next = seconde;
        System.out.println(removeNthFromEnd2(head, 1).val);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int myIndex = 1;
        ListNode first = head;
        while (head.next != null) {
            myIndex++;
            head = head.next;
        }
        myIndex = myIndex - n;
        head = first;
        if (myIndex == 0) {
            return head.next;
        }
        int tmpIndex = 0;
        while (head.next != null) {
            if (tmpIndex == myIndex - 1) {
                head.next = head.next.next;
                return first;
            }
            tmpIndex++;
            head = head.next;
        }
        return first;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode first = head;
        ListNode preTarget = head;

        int myN = 0;
        while (head.next != null) {
            myN++;
            head = head.next;
            if (myN >= n) {
                if (head.next == null) {
                    preTarget.next =preTarget.next.next;
                    return first;
                }
                preTarget=preTarget.next;
            }
        }
        return first.next;
    }


}
