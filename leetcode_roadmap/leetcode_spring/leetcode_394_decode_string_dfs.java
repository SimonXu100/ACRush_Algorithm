// well formatted string conversion
// all numbers represent repeated times
// all characters in square brackets
// dfs 难点在嵌套结构
// method 1
// recursive
// idea:
// 不同点在于将 [ 和 ] 分别作为递归的开启与终止条件
// 当 s[i] == ']' 时，返回当前括号内记录的 res 字符串与 ] 的索引 i （更新上层递归指针位置）；
// 当 s[i] == '[' 时，开启新一层递归，记录此 [...] 内字符串 tmp 和递归后的最新索引 i，并执行 res + multi * tmp 拼接字符串。
// 遍历完毕后返回 res。
// 经验： tracking steps
// running time: O(n)

// space time:O(n)
/*
class Solution {
    public String decodeString(String s) {
        return dfs_helper(0, s)[0];
        
    }
    
    public String[] dfs_helper(int i, String s){
        // the first return value: is the 
        // k: starting point
        int length = s.length();
        StringBuilder res = new StringBuilder();
        int multi = 0;
        
        // decide condition
        while(i<length){
            char cur = s.charAt(i);
            
            if(cur >= '0' && cur <='9'){
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            else if(cur == '['){
                String[] tmp = dfs_helper(i+1 , s);
                i = Integer.parseInt(tmp[0]);
                while(multi > 0){
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if(cur ==']'){
                // finish and return
                return new String[]{String.valueOf(i), res.toString()};
            }
            else{
                res.append(String.valueOf(cur)); 
            } 
            i++;
        }
        
        return new String[] {res.toString()};        
    }
}
*/


// method 2
// assistance stack
// 辅助栈
// 难点在于括号内嵌套括号，需要从内向外生成与拼接字符串，这与栈的先入后出特性对应
// Notes: 嵌套: 
// running time: O(n)
// space: O(n)
/*
构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
当 c 为字母时，在 res 尾部添加 c；
当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 00：
记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
进入到新 [ 后，res 和 multi 重新记录。
当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res，其中:
last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2。
返回字符串 res。
*/
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        
        LinkedList<Integer> stack_multi = new LinkedList<Integer>();
        LinkedList<String> stack_res = new LinkedList<String>();
        
        for(Character cur : s.toCharArray()){
            if(cur>='0' && cur<='9'){
                // 读写多位数字内容
                multi = multi * 10 + Integer.parseInt(cur +"");
            }
            else if(cur=='['){
                stack_multi.addLast(multi);
                multi = 0;
                stack_res.addLast(res.toString());
                res = new StringBuilder();
            }
            else if(cur==']'){
                int cur_multi = stack_multi.removeLast();
                StringBuilder tmp = new StringBuilder();
                while(cur_multi>0){
                    tmp.append(res);
                    cur_multi--;
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else res.append(cur);    
        }
        return res.toString();  
    }
}



// method 3
// 可以直接用个计数器记一下括号的数量，可以不用栈



















































































---------------------------------------------------------------------------------------
StringBuilder
StringBuilder res = new StringBuilder();
res.append()

1 Constructors 
Constructor and Description
StringBuilder()
Constructs a string builder with no characters in it and an initial capacity of 16 characters.
StringBuilder(CharSequence seq)
Constructs a string builder that contains the same characters as the specified CharSequence.
StringBuilder(int capacity)
Constructs a string builder with no characters in it and an initial capacity specified by the capacity argument.
StringBuilder(String str)
Constructs a string builder initialized to the contents of the specified string.


2 
delete(int start, int end)
Removes the characters in a substring of this sequence.

StringBuilder	deleteCharAt(int index)
Removes the char at the specified position in this sequence.

3 
indexOf(String str)
Returns the index within this string of the first occurrence of the specified substring.

int	indexOf(String str, int fromIndex)
Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.

4 toString()
Returns a string representing the data in this sequence.

5 
substring(int start)
Returns a new String that contains a subsequence of characters currently contained in this character sequence.

String	substring(int start, int end)
Returns a new String that contains a subsequence of characters currently contained in this sequence.

6 insert() 










String 
Integer.parseInt()
String.valueOf(s.charAt(i))

return new String[] { String.valueOf(i), res.toString() }

String valueOf() The java string valueOf() method converts different types of values into string.



or Character + ""


for(Character cur : s.toCharArray()){

}





















