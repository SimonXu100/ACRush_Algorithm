// assume all the given expression is always valid
// condition 1: 只有加号，没有空格，假设所有输入都是合法的

class Solution {
    public int calculate(String s) {
    	//first tranfer the string into char Array and store them into a queue
    	Queue<Character> queue = new LinkedList<Character>();
    	for(char c : s.toCharArray()){
    		//filter out the space
    		if(c!=' ') queue.offer(c);
    	} 
         
        queue.offer('+');

        // num: the current number
        // sum: the result
        int num = 0, sum = 0;

    	while(!queue.isEmpty()){
    		char c = queue.poll();

    		if(Character.isDigit(c)){
    			num = num * 10 + (c - '0');

    		}
    		// if meets the '+', then sum the current num into the result and update the num to 0 
    		else{
    			sum += num;
    			num = 0;
    		}

    		//字符串最后没有加号，最后还要将num加入到sum中
    		//也可在初始队列尾添加多余处理加号
    		//sum += num;

    	}
    	return sum; 


        
    }
}

// condition 2: extension to subtraction
// whenn initialize the queue, filter space out

class Solution {
    public int calculate(String s) {
    	//first tranfer the string into char Array and store them into a queue
    	Queue<Character> queue = new LinkedList<Character>();
    	for(char c : s.toCharArray()){
    		//filter out the space
    		if(c!=' ') queue.offer(c);
    	} 
         
        queue.offer('+');

        // num: the current number
        // sum: the result
        int num = 0, sum = 0;
        //记录当前数字的正负，初始化为正数
        char sign = '+'


    	while(!queue.isEmpty()){
    		char c = queue.poll();

    		if(Character.isDigit(c)){
    			num = num * 10 + (c - '0');

    		}
    		// if meets the '+', then sum the current num into the result and update the num to 0 
    		else{
    			if(sign == '+'){
    				sum+= num;
    			}
    			else if(sign=='-'){
    				sum-=num;
    			}
    		}
            // 更新一下符号位,并将num清0
            num = 0;
            sign = c;
    	}
    	return sum;
    }
}

//condition 3: extension to muliplication and division
// priority: store the current result: adding stack
class Solution {
    public int calculate(String s) {
    	//first tranfer the string into char Array and store them into a queue
    	Queue<Character> queue = new LinkedList<Character>();
    	for(char c : s.toCharArray()){
    		//filter out the space
    		if(c!=' ') queue.offer(c);
    	} 
         
        queue.offer('+');

        // num: the current number
        // sum: the result
        int num = 0, sum = 0;
        //记录当前数字的正负，初始化为正数
        char sign = '+'

        Stack<Integer> stack = new Stack<>();

    	while(!queue.isEmpty()){
    		char c = queue.poll();

    		if(Character.isDigit(c)){
    			num = num * 10 + (c - '0');

    		}
    		// if meets the '+', then sum the current num into the result and update the num to 0 
    		else{
    			if(sign == '+'){
    				stack.push(num);
    			}
    			else if(sign == '-'){
    				stack.push(-num);
    			}
    			else if(sign == '*'){
    				stack.push(stack.pop() * num)
    			}
    			else if(sign == '/'){
    				stack.push(stack.pop() / num)
    			}
    		}
            // 更新一下符号位,并将num清0
            num = 0;
            sign = c;
    	}

    	//相加堆栈中存储的东西
    	int sum = 0;
    	while(!stack.isEmpty()){
    		sum += stack.pop();
    	}
    	return sum;
    }

// condition 4: parenthesis
// apply recursion for the content within parenthesis

// assume all the given expression is always valid
//analysis: 
//T: O(n)
//S:O(n)
class Solution {
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<Character>();
        for(char c:s.toCharArray()){
            if(c != ' ') queue.offer(c);
        }
        queue.offer('+');
        return calculate(queue);
    }
    
    public int calculate(Queue<Character> queue){
        int sum, num = 0;
        char sign = '+';
        
        Stack<Integer> stack = new Stack<Integer>();
        
        while(!queue.isEmpty()){
            char c = queue.poll();
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            else if(c == '('){
                // recursively call 
                num = calculate(queue);
            }
            else{
                if(sign == '+'){
                    stack.push(num);
                }
                else if(sign == '-'){
                    stack.push(-num);
                }
                else if(sign == '*'){
                    stack.push(stack.pop() * num);
                }
                else if(sign == '/'){
                    stack.push(stack.pop() / num);
                }
                
                num = 0;
                sign = c;
            }
            
            if(c == ')'){
                break;
            }   
        }
        
        sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
}















