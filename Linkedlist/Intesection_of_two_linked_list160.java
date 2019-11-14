// two pointers
// 利用重合的部分，拼接指针，抵消长度差距
// 分析：T（O（m+n））
// 分析：S（O（1））
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != null || pB != null){
            if(pA == pB){return pA;}
            //if(pA == null){pA = headB;}
             pA = pA == null? headB:pA.next;
             pB = pB == null? headA:pB.next;
            //if( pB == null){pB = headA;}
           // pA = pA.next;
           // pB = pB.next;
    
        }
        return null;
    }
}

// hashTableL： 存储一个链表，遍历找到是否有相关元素（自带顺序）
//TO(m or n)
//S(O(m))
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
       // 遍历存在顺序
        Set<ListNode> node = new HashSet<>();
        while(headA != null){
           node.add(headA);
           headA = headA.next;
        }
        while(headB != null){
            if(node.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}


