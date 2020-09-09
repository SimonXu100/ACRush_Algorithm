/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// method 1 
// 双指针
// 快慢指针
// idea 要对比cur(cur最初始的定义指向空结点)指针的下一个结点与下下一个结点的值是否相等，
// 为了防止产生空指针异常，故退出迭代的条件为：cur.next != null && cur.next.next != null
// 迭代去重
// running time: O(n)
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            if(cur.next.val == cur.next.next.val){
                ListNode temp = cur.next;
                // inner iteration: find the first element with the values not same as cur.next.val
                /*
                while(temp!=null && temp.val == cur.next.val){
                    temp = temp.next;
                }
                cur.next = temp;
                */
                
                // method 2
                // better way
                while(temp!=null && temp.next!= null && temp.val == temp.next.val){
                    temp = temp.next;
                }
                cur.next = temp.next;
            }
            else cur = cur.next;
        }
        return dummy.next;
    }
}




// method 2:
// existence data structure couting if duplicates
// running time:O(n)
// space: O(n)
/*
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // counting the existence of elements in linked list
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        ListNode cur = head;
        while(cur != null){
            count.put(cur.val, count.getOrDefault(cur.val,0)+1);
            cur = cur.next;
        }
        
        // remove duplicate elements
        ListNode dummy  =  new ListNode(-1);
        dummy.next = head;
        cur = dummy;
        
        while(cur!=null && cur.next!=null){
            if(count.get(cur.next.val) > 1){
                cur.next = cur.next.next;
            }
            else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
*/



---------------------------------------------------------------------------
basic problems
leetcode 83: remove duplicates from sorted lists
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
// running time: O(n)
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }
            else{
                cur = cur.next;
            }
        }
        
        return head;   
    }
}