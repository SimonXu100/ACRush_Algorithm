/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// two pass
// know the size of linked list
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //step 1: find the size of the linkedlist
        int size = 0;
        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
            size++;
        }
        size++;
        // notes: special test case1
        if(size == 1) return null;
        //the position of element needed to delete: size - n + 1
        // the p-th from the start of list
        int p = size-n+1;
        //special test case2
        if(p == 1) return head.next;
        cur = head;
        int count = 1;
        // the previous node of the specific position
        while(count != p-1){
            cur = cur.next;
            count++;
        }
        ListNode previous = cur;
        ListNode node = cur.next;
        previous.next = node.next;
        return head;
    }
}
*/


// two pass method 2
// virtual head pointers, reduce special circumstances processing
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode v = new ListNode(-1);
        v.next = head;
        
        //step 1: find the size of the linkedlist
        int size = 0;
        ListNode cur = v;
        while(cur.next != null){
            cur = cur.next;
            size++;
        }
        // step2: calculate the postion need to be deleted
        int p = size-n+1;
        cur = v;
        for(int i=1;i<p;i++){
            cur = cur.next;
        }
        ListNode previous = cur;
        ListNode node = cur.next;
        previous.next = node.next;        
        return v.next;
    }
}
*/

// one pass method
// double pointes 
// keep a specific gap

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode v = new ListNode(-1);
        v.next = head;
        ListNode first = v;
        ListNode second = v;
        int i = 0;
        while(i<n+1){
            first = first.next;
            i++;
        }
        
        while(first != null){
            first = first.next;
            second = second.next;
        }
        
        second.next = second.next.next;
        
        return v.next;
        
    }
}




















