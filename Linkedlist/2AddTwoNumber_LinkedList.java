//虚拟指针头+逆序+存在进位情况
// final值，表示进位
//逻辑简洁

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       int p = 0;
       ListNode res =  new ListNode(-1);
       ListNode curr = res;
       int temp1 = l1.val;
       int temp2 = l2.val;
       while(l1!=null || l2!=null ){
           curr.next = new ListNode(-1);
           curr.next.val = temp1+temp2+p;
           //判断进位
           //简洁性修改
           p = curr.next.val/10;
           curr.next.val = curr.next.val%10;
           //应对里链表长度不一致
           //取值选择或者和递归选择
           if(l1!=null){
              l1 = l1.next;
              //if(l1==null){temp1=0;}
              //else{temp1 = l1.val;}
             temp1 = l1==null?0:l1.val;

           }
           if(l2!=null){
              l2 = l2.next;
              //if(l2==null){temp2=0;}
              //else{temp2 = l2.val;}
              temp2 = l2==null?0:l2.val;
           }
          curr = curr.next;
       }
       // 保证最后进位
        if(p==1){
            curr.next = new ListNode(1);
        }
        return res.next;
    }
}

//简洁性修改：进位： p = curr.next.val/10;
/**
           if(curr.next.val>=10){
               curr.next.val = curr.next.val-10;
               p = 1;
           }
           else{
               p = 0;
           }
           */

/**
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
}
*/
