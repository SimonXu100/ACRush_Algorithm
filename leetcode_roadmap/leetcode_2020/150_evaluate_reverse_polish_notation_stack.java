// 逆波兰表达式的解释器一般是基于堆栈的。解释过程一般是：操作数入栈；遇到操作符时，操作数出栈，求值，将结果入栈；
// 当一遍后，栈顶就是表达式的值。因此逆波兰表达式的求值使用堆栈结构很容易实现，并且能很快求值

// Division between two integers should truncate toward zero
// The given RPN expression is always valid


// function accumulation
//Integer.parseInt(s)
// s.equals("+")

// stack
class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null) return 0;
        Stack<Integer> stack = new Stack<>();
        int tmp = 0;
        for(String s : tokens){
            if(s.equals("+")) stack.push(stack.pop() + stack.pop());
            else if(s.equals("-")) stack.push(-stack.pop() + stack.pop());
            else if(s.equals("*")) stack.push(stack.pop() * stack.pop());
            // notes: computing order
            else if(s.equals("/")) {
                tmp = stack.pop();
                stack.push(stack.pop() / tmp);
            }
            else{
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek(); 
    }
}