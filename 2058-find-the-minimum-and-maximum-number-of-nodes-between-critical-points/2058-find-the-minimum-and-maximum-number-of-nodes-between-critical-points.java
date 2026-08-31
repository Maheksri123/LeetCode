/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;

        int firstIndex = -1;
        int prevIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a local maxima or local minima
            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {
                
                if (firstIndex == -1) {
                    firstIndex = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevIndex);
                }
                prevIndex = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // If fewer than 2 critical points are found
        if (prevIndex == firstIndex || firstIndex == -1) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}