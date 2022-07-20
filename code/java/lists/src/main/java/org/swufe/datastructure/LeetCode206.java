package org.swufe.datastructure;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LeetCode206 {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    public ListNode recursiveReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode restHead = recursiveReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return restHead;
    }
}
