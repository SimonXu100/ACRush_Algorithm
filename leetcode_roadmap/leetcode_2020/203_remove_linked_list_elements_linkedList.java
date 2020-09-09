/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 // LinkedList
 /*
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // virtual head 
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        
        while(cur != null && cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }
            else cur =  cur.next;
        }
        
        return dummy.next; 
        //return null;
    }
}
*/
// 
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // virtual head 
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        
        while(cur != null){
            if(cur.val == val){
                prev.next = cur.next;            
            }
            else prev = cur;
            cur = cur.next;
        }
        
        return dummy.next; 
        //return null;
    }
}


















