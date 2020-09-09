// brute force
// 每当我们遇到一个 \text{‘(’}‘(’ ，我们把它放在栈顶。对于遇到的每个 \text{‘)’}‘)’ ，我们从栈中弹出一个 \text{‘(’}‘(’ ，如果栈顶没有 \text{‘(’}‘(’，或者遍历完整个子字符串后栈中仍然有元素，那么该子字符串是无效的


// note: without space cost, may need process both from left to right and right to left
// stack, dp, optimization: tracking:(windows idea)


// method 1
// brute force
// 遍历all substring and check all substrings they are valid parentheses or not
// running time: O(n^3)
// space: O(n)
// 非空偶数长度子字符串
// check if valid or not: stack 
//每当我们遇到一个 \text{‘(’}‘(’ ，我们把它放在栈顶。对于遇到的每个 \text{‘)’}‘)’ ，我们从栈中弹出一个 \text{‘(’}‘(’ ，如果栈顶没有 \text{‘(’}‘(’，
// 或者遍历完整个子字符串后栈中仍然有元素，那么该子字符串是无效的
// TLE
/*
class Solution {
    public int longestValidParentheses(String s) {
        // 遍历所有偶数长度substring
        int  maxlen = 0;
        for(int i=0; i<s.length(); i++){
            for(int j=i+2; j<=s.length(); j=j+2){
                 if(isValidParentheses(s.substring(i,j))){
                     maxlen = Math.max(maxlen, j-i);
                 } 
            }
        }
        return maxlen;   
    }
    
    public boolean isValidParentheses(String substr){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<substr.length(); i++){
            char cur = substr.charAt(i);
            
            if(cur == '('){
                stack.push(cur);
            }
            else if(cur == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                else if(stack.peek() == '('){
                    stack.pop();
                }   
            }  
        }
        return stack.isEmpty();
    }   
}
*/

// method 2
// dynamic programming
// optimal substructure
// 有效必须要是连着有效
// trasfer equation
// s[i] = ")", s[i-1] == "(", dp[i] = dp[i-2] + 2
// s[i] == ")", s[i-1] == ")", if dp[i- dp[i-1]-1] == "(" , dp[i] = dp[i-1] + dp[i-2-dp[i-1]] + 2
// runninng time: O(n^2)
class Solution {
    public int longestValidParentheses(String s) {
       // dp[i]: longest valid parentheses end in i
        // i 必须在有效字符串中
        int[] dp = new int[s.length()];
        int maxlen = 0;
        
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) ==')'){
                
                if(s.charAt(i-1) == '('){
                    dp[i] = (i>=2 ? dp[i-2] : 0) + 2;
                }
                else if(i-dp[i-1] >0 && s.charAt(i-dp[i-1]-1) == '('){
                    // dp[i-1]: may not existence: may cannot meet all conditions
                    dp[i] = dp[i-1] + ((i-dp[i-1] >=2)? dp[i-dp[i-1]-2] : 0) + 2;
                }
                //dp[i] may not exist
                maxlen = Math.max(maxlen, dp[i]);
            } 
        }
        return maxlen;
    }
}








// method 3
// stack
// 用栈在遍历给定字符串的过程中去判断到目前为止扫描的子字符串的有效性，同时能的都最长有效字符串的长度。我们首先将 −1 放入栈顶
// 每次更新匹配对数的时候,更新最大长度， 计算有效字符串的长度
// 栈中放入下标
// idea: virtual head
// 记录前一无效的字符位置，作为起始位置标志
// running time: O(n)
/*
class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        // store the indexes
        Stack<Integer> stack = new Stack<Integer>();
        // idea: skill: virtual head
        stack.push(-1);
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else{
                // 不管之前是任何字符都为stack.pop()
                // 若栈顶indexes对应为‘（’，pop it out, 利用无效标记计算
                // 若栈顶indexes 对应其他符号， 则pop it out, 更新无效标记位置
                stack.pop();
                if(stack.isEmpty()){
                    // 更新标记位置
                    stack.push(i);
                }
                else{
                    //更新最大有效字符串
                    maxlen = Math.max(maxlen, i-stack.peek());
                }
                
            }
  
        }
        
        return maxlen;
        
    }
}
*/




// method 4 optimization
// without space cost 
// track the apperance of left bracket and right bracket
// idea:
// 对于遇到的每个 \text{‘(’}‘(’，我们增加 leftleft 计算器，对于遇到的每个 \text{‘)’}‘)’ ，我们增加 rightright 计数器。每当 leftleft 计数器与 rightright 计数器相等时，
// 我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。如果 rightright 计数器比 leftleft 计数器大时，我们将 leftleft 和 rightright 计数器同时变回 00 。
// both from left to right and right to left
// running time: O(n)
/*
class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        int left = 0;
        int right = 0;
        
        // left to right
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            if(cur == '('){
                left++;
            }
            else{
                right++;
            }
            // when left == right, update the maxlen
            if(left == right) maxlen = Math.max(maxlen, 2 * right);
            else if(right > left){
                left = 0;
                right = 0;
            }
        }
        
        
        left = 0;
        right = 0;
        // from right to left
        for(int i=s.length()-1; i>=0; i--){
            char cur = s.charAt(i);
            if(cur == '('){
                left++;
            }
            else{
                right++;
            }
            // when left == right, update the maxlen
            if(left == right) maxlen = Math.max(maxlen, 2 * left);
            else if(left > right){
                left = 0;
                right = 0;
            }
        }
        
        return maxlen;   
    }
}
*/


















