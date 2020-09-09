//stack + string
//复杂问题转化为简单问题分析
/*
//case1: only plus situation andn assume valid
class Solution {
    public int calculate(String s) {
        //做一个转换,将字符串的字符放入到一个优先队列
        Queue<Character> queue = new LinkedList<>();
        for(char c : s.toCharArray()){
            queue.offer(c);
        }
        // num: current number
        // sum: final sum
        int num = 0;
        int sum = 0;

        //transverse queue
        while(!queue.isEmpty()){
            char c = queue.poll();

            //若当前字符是数字，则更新num变量（连续数字表示整个数字）
            if(Character.isDigit(c)){
        		num = num * 10 + c -'0';
            }
            //若遇到加号，把当前num加入结果中，num清零防止影响计算
            else{
            	sum += num;
            	num = 0;
            }
        }
        //由于字符串没有加号，最终还要再一次将num加入到sum中
        sum += num;

        return sum;
    }
}
//添加加号，简洁代码

class Solution {
    public int calculate(String s) {
        //做一个转换,将字符串的字符放入到一个优先队列
        Queue<Character> queue = new LinkedList<>();
        for(char c : s.toCharArray()){
            queue.offer(c);
        }
        // 在队列尾添加加号
        queue.add('+');
        // num: current number
        // sum: final sum
        int num = 0;
        int sum = 0;

        //transverse queue
        while(!queue.isEmpty()){
            char c = queue.poll();

            //若当前字符是数字，则更新num变量（连续数字表示整个数字）
            if(Character.isDigit(c)){
        		num = num * 10 + c -'0';
            }
            //若遇到加号，把当前num加入结果中，num清零防止影响计算
            else{
            	sum += num;
            	num = 0;
            }
        }
        //由于队列末尾添加了加号，此部分可以省略
        //sum += num;

        return sum;
    }
}
*/
//扩展：过滤空格
/*
if(c!=' '){
   queue.offer();
}
*/

//扩展减法
//s1:两个stack -有效数字， -有效符号
//s2:将表达式看做1+2-10
//sign: 同步更新方向
class Solution {
    public int calculate(String s) {
        //做一个转换,将字符串的字符放入到一个优先队列
        Queue<Character> queue = new LinkedList<>();
        for(char c : s.toCharArray()){
            if(c!=' '){
               queue.offer(c);
            }
        }
        // 在队列尾添加加号
        queue.add('+');
        // num: current number
        // sum: final sum
        //sign 变量记录当前数字的正负，初始化为‘+’

        char sign = '+';
        int num = 0;
        int sum = 0;

        //transverse queue
        while(!queue.isEmpty()){
            char c = queue.poll();

            //若当前字符是数字，则更新num变量（连续数字表示整个数字）
            if(Character.isDigit(c)){
        		num = num * 10 + c -'0';
            }
            else{
                //如果当前数字的符号是正号，直接加入至总和
                if(sign=='+'){
 					sum += num;
                }//当前数字的符号是减号，即是负数，从当前总和中减去该数
                else if(sign=='-'){
 					sum -= num;
                }
                num = 0;
                //更新符号位
                sign = c;
            	
            }
        }
        return sum;
    }
}

//支持乘法和除法，考虑优先级问题








































