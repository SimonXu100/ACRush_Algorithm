/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// o(nlgn): merger sort or quick sort
// method 1: merge sort 
// merge sort are stable and suitable for large dataset
// running time: O(nlgn)
// space: O(1)
class Solution {
    public ListNode sortList(ListNode head) {
        // validation check
        if(head==null || (head!=null&& head.next==null)) return head;
        
        // find the middle using two pointers: slow and fast
        ListNode fast = head.next;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        // recursive
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // merge, return the sorted head ListNode
        // virtual head
        ListNode p = new ListNode(0);
        ListNode res = p;
        while(left!=null && right!=null){
            if(left.val <= right.val){
                p.next = left;
                left = left.next;
            }
            else{
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        p.next = (left==null?right: left);
        return res.next;
    }
}