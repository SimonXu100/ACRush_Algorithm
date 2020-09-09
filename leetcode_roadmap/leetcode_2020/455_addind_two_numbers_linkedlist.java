/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// LinkedList
// recursion 
// method 1: the problem is reverse order, first we can convert the LinkedList to arrayList, so we can 
// get the reverse order easily
// running time; O(N)
// space: O(N)
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ArrayList<Integer> first_number = new ArrayList<Integer>();
        ArrayList<Integer> second_number = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        ListNode curNode = null;
        curNode = l1;
        while(curNode != null){
            first_number.add(curNode.val);
            curNode = curNode.next;
        }
        curNode = l2;
        while(curNode != null){
            second_number.add(curNode.val);
            curNode = curNode.next;
        }
        
        // plus together
        int first_size = first_number.size();
        int second_size = second_number.size();
        int cur = 0;
        Collections.reverse(first_number);
        Collections.reverse(second_number);
        // 借位
        int flag = 0;
        int temp_first = 0;
        int temp_second = 0;
        for(int i=0; i<first_size || i<second_size; i++){
            if(i>= first_size) temp_first = 0;
            else temp_first = first_number.get(i);
            
            if(i>= second_size) temp_second = 0;
            else temp_second = second_number.get(i);
            res.add((temp_first+temp_second + flag) % 10);
            flag = (temp_first + temp_second + flag) / 10;
        }
        if(flag !=0) res.add(flag);
        // build the final linkedList
        curNode = dummy;
        for(int i=res.size()-1; i>=0; i--){
            curNode.next = new ListNode(res.get(i));
            curNode = curNode.next;
        }
        return dummy.next;    
    }
}
*/



// method 2: recursion
// main idea
/*
如果len1与len2都为1，那么当前的值应为（l1.val+l2.val）%10,进位更新为（l1.val+l2.val）/10；
如果len1大于len2，递归计算（l1.next,l2），当前的值应为（l1.val+进位）%10，进位更新为（l1.val+进位）/10；
如果len1等于len2，递归计算（l1.next,l2.next）,当前的值应为（l1.val+进位+l2.val）%10，进位更新为（l1.val+进位+l2.val）/10；
返回当前节点的指针
递归结束
为方便递归，递归开始前我们保证len1>=len2，另外递归结束后若进位为1，需要新建值为1的头节点
*/
// 递归使低位先进性计算
// recursion, 改变计算顺序
// running time: O(n)
// space: O(1)

class Solution {
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        int len1 = 0;
        int len2 = 0;
        
        ListNode dummy = new ListNode(-1);
        ListNode curNode = null;
        curNode = l1;
        // find the length of each number
        while(curNode != null){
            len1++;
            curNode = curNode.next;
        }
        curNode = l2;
        while(curNode != null){
            len2++;
            curNode = curNode.next;
        }
        ListNode res = len1>len2? add(l1,l2,len1,len2) : add(l2,l1,len2,len1);
        // finally position, if the flag != 0, then add a new position with value of 1
        if(carry==1) {
            dummy = new ListNode(1);
            dummy.next = res;  
            return dummy;
        }
        return res;
    }
    // return the points of current node
    public ListNode add(ListNode tempNode1, ListNode tempNode2, int len1, int len2){
        // find the aligned position
        int temp = 0;
        if(len1==1 && len2==1){
            temp = tempNode1.val;
            // 借用其中一个linkedList, eliminate space usuage
            tempNode1.val = (tempNode1.val + tempNode2.val) % 10;
            carry = (temp + tempNode2.val) / 10;
            return tempNode1;
        }
        
        //recursion processing
        if(len1 > len2) {
            temp = tempNode1.val;
            tempNode1.next = add(tempNode1.next, tempNode2, len1-1, len2);
            tempNode1.val = (temp + carry) % 10;
            carry = (temp + carry) / 10;
            return tempNode1;
        }
        // condition: len1 == len2
        // because in the beginning, we choose len1>len2, so in this recursion process, we will not meet len2<len1
        temp = tempNode1.val;
        tempNode1.next = add(tempNode1.next, tempNode2.next, len1-1, len2-1);
        tempNode1.val = (temp + tempNode2.val + carry) % 10;
        carry = (temp + tempNode2.val + carry) / 10;
        return tempNode1;
    }        
}






// stack solution 
// 使用两个栈的方法
// method 3
// 用来翻转链表
// stack 作用： 保持临近值或者改变计算顺序
// 更简洁的操作
// idea: 翻转记录stack, and then compute number
// running time: O(n)
// space: O(n)
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        ListNode curNode = null;
        ListNode dummy = null;
        int carry = 0;
        // 入栈
        curNode = l1;
        while(curNode != null){
            stack1.push(curNode.val);
            curNode = curNode.next;
        }
        curNode = l2;
        while(curNode != null){
            stack2.push(curNode.val);
            curNode = curNode.next;
        }
        
        // compute
        int value = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            value = 0;
            if(!stack1.isEmpty()) value += stack1.pop();
            if(!stack2.isEmpty()) value += stack2.pop();
            value += carry;
            ListNode node = new ListNode(value % 10);
            carry = value / 10;
            node.next = dummy;
            dummy = node;
        }
        return dummy;
    }
}
*/







// 进阶题目， 不能反转链表
// 用 recursion 可以解决


