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
// method 1
// recursive
// running time: O(n)
// space: O(n)
/*
class Solution {
    public ListNode swapPairs(ListNode head) {    
        ListNode temp1 = null;
        ListNode temp2 = null;
        // validation checking
        if(head == null) return null;
        if(head.next == null) return head;
    
        temp1 = head.next;
        temp2 = head.next.next;
        temp1.next = head;
        head.next = swapPairs(temp2);
        return temp1;
    }
}
*/

// method 2
// iteration
// linked two intermediate result
// 通过前一个循环的dummy, 减少操作复杂度
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode firstNode = null;
        ListNode secondNode = null;
        
        ListNode cur  = head;
        ListNode prevNode = dummy;
        while(cur!=null && cur.next!=null){
            firstNode = cur;
            secondNode = cur.next;
            
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            
            // re-initialize the head and prevNode for the next swap
            prevNode = firstNode;
            cur = firstNode.next;
            
        }
        return dummy.next;
    }
}













