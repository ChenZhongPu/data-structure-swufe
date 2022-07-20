package org.swufe.datastructure;

public class LeetCode92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 1 <= left <= right <= n; 1 <= n <= 500
        if (left == right) return head;
        int index = 1;
        ListNode pre = null;
        ListNode cur = head;
        while (index < left) {
            pre = cur;
            cur =cur.next;
            index++;
        }
        ListNode beforeLeft = pre;
        ListNode leftMost = cur;
        while (index >= left && index <= right) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            index++;
        }
        if (beforeLeft != null) {
            beforeLeft.next = pre;
        }
        leftMost.next = cur;

        if (beforeLeft != null) {
            return head;
        } else {
            return pre;
        }
    }
}
