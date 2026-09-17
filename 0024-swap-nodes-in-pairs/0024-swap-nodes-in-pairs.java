class Solution {
    public ListNode swapPairs(ListNode head) {
        // Dummy node simplifies handling edge cases like swapping the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Swapping pointers
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move 'prev' pointer two nodes ahead for the next pair
            prev = first;
        }

        return dummy.next;
    }
}