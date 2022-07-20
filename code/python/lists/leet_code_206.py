from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverselist(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        pre = None
        cur = head
        while cur.next is not None:
            nxt = cur.next
            cur.next = pre
            pre = cur
            cur = nxt
        cur.next = pre
        return cur

    def recursive_reverse_list(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None or head.next is None:
            return head
        rest_head = self.recursive_reverse_list(head.next)
        head.next.next = head
        head.next = None
        return rest_head
