/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// method 1
// recursive
// 剩余的节点保持原有顺序

// idea
// 1把链表结点按照 k 个一组分组，所以可以使用一个指针 head 依次指向每组的头结点。这个指针每次向前移动 k 步，直至链表结尾。
// 对于每个分组，我们先判断它的长度是否大于等于 k。若是，我们就翻转这部分链表，否则不需要翻转
// 2 对于一个子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，以及子链表的尾部与下一个子链表连接


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode tail = prev;
        
        while(head!=null){
            // 1 crop the k-length sub-linkedlist
            for(int i=0; i<k; i++){
                tail = tail.next;
                if(tail == null){
                    return dummy.next;
                }
            }
            ListNode temp = tail.next;
            List<ListNode> tempPair = reverseNodes(head, tail);
            head= tempPair.get(0);
            tail = tempPair.get(1);

            // 2 link together
            prev.next = head;
            tail.next = temp;

            prev = tail;
            head = tail.next;
        }
        return dummy.next;
    }

    public List<ListNode> reverseNodes(ListNode head, ListNode tail){
        ListNode dummy = tail.next;
        ListNode cur = head;
        ListNode temp = null;

        List<ListNode> result = new ArrayList<ListNode>();
        while(dummy != tail){
            temp = cur.next;
            cur.next = dummy;
            
            dummy = cur;
            cur = temp;
        }

        result.add(tail);
        result.add(head);
        return result;
    }

    
}

















--------------------------------------------------------------------------------
// basic problems
// 206 reverse linked list
// recurseive and iterations
// or by external data structures: stacks
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// method 1
// recurseive
// 找到递归条件
// 注意完整路径，防止环的发生
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode p = reverseList(head.next);
        head.next.next = head;
        // 防止产生环
        head.next = null;
        
        return p;
    }
}






// method 2
// iteration
// note: 避免产生环
// long tracking 容易产生环
// idea: 改变箭头方向
// note： 与之前节点连接， 更新 previous node
/*
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = dummy;
            
            dummy = cur;
            cur = temp;
        }
        return dummy;
    }
}
*/









// method 3
// external data strucuture
// stack
/*
class Solution {
    public ListNode reverseList(ListNode head) {
        
    }
}
*/