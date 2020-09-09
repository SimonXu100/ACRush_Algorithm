// stack and stringBuilder
//检查方法实现时，请注意循环内是否有 O(n)O(n) 的方法调用，这可能会使复杂度达到 O(n^2)
// running time: O(n)
// space: O(n)
/*
class Solution {
    public String minRemoveToMakeValid(String s) {
        // Set stores the indexes needed to be removed
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    indexesToRemove.add(i);
                }
                else{
                    stack.pop();
                }
            }
        }
        // pop all left 'C' into removed set
        while(!stack.isEmpty()){
            indexesToRemove.add(stack.pop());
        }
        
        //StringBuilder: create the valid strings
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            // O(1)
            if(!indexesToRemove.contains(i)){
                sb.append(s.charAt(i));
            }
        }        
        return sb.toString();
    }
}
*/

// method 2
// two steps of StringBuilder
// 以上方法 可马上找到无效的 ")", 但是无法快速找到无效的 "("
// reverse and do the second check, find invalid "C"
// 每次截取找到有效的字符串
// running time: O(n)
// space: O(n): stringbuilder
/*
class Solution {
    public String minRemoveToMakeValid(String s) {
        
        //StringBuilder: create the valid strings
        StringBuilder result = removeInvalidClosing(s,'(', ')');
        result = removeInvalidClosing(result.reverse(), ')','(');
        return result.reverse().toString();
    }
    
    public StringBuilder removeInvalidClosing(CharSequence string, char open, char close){
        StringBuilder sb = new StringBuilder();
        // keep track of virtual stack size
        int balance = 0;
        for(int i=0; i<string.length(); i++){
            char c = string.charAt(i);
            if(c == open){
                balance++;
            }
            else if(c == close){
                if(balance == 0) continue;
                balance-- ;
            }
            sb.append(c);
        }
        return sb;  
    }
}
*/



// method 3: improvement of two strings
/*
这是方法二的简化版本，只需要保持平衡即可，不需要栈，也不需要执行两次完整的字符串扫描。在完成第一步扫描后，查看有多少个需要删除的 "("，然后从右侧开始扫描，删除对应个数的 "(" 即可。事实证明，如果删除最右边的 "("，一定可以保证字符串平衡。

因此，从右边开始根据余量删除 "("，每次删除都可以在保证字符串有效的情况下，修改余量。如果任何的 "(" 都是无效的，则说明在第一个 "(" 之前就存在 ")" 了，但是无效的 ")" 在第一步时就已经删除了，所以第二步中不存在这样的情况
算法：
为了避免第二步迭代（这会增加算法的复杂性），需要记录第一步扫描中字符串有多少个 "("。这样就可以在第二次扫描时计算出保留的 "(" 数量和删除的 "(" 数量。一旦达到足够数量的 "("，就可以直接删除后面的 "("。
*/

class Solution {
    public String minRemoveToMakeValid(String s) {
        
        //StringBuilder: create the valid strings
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                openSeen++;
                balance++;
            }
            else if(c ==')'){
                if(balance == 0) continue;
                balance--;
            }
            sb.append(c);   
        }
        // remove redundant "C"
        StringBuilder result = new StringBuilder();
        int openToKeep =  openSeen - balance;
        for(int i=0; i<sb.length(); i++){
            char c = sb.charAt(i);
            if(c=='('){
                openToKeep--;
                if(openToKeep < 0) continue;
            }
            result.append(c);
        }   
        return result.toString();
    }
}





the idea of determining if valid parenthesis
字符串中有相同数量的 "(" 和 ")"。
从左至右遍历字符串，统计当前 "(" 和 ")" 的数量，永远不会出现 ")" 的数量大于 "(" 的数量，称 count("(") - count(")") 为字符串的余量。

这是一段简单的伪代码，从左向右扫描字符串，每次遇到 "(" 时，余量递增，遇到 ")" 时，余量递减。在任何时候如果余量为负（")" 的数量多于 "("），则返回 false。如果扫描到字符串末尾时余量为 0，说明 ")" 的数量等于 "(" 的数量，返回 true。

仅仅贪心的保持一致性是不能解决问题的
实际上，我们应该确定哪个 "(" 与哪个 ")" 匹配。这里用不同的颜色显示每一对括号，其中 ")" 总是与它 最接近 的 "(" 的匹配

应该让每个 ")" 都与离它最近且尚未匹配的 "(" 匹配。如何在代码中做到这一点？需要知道未匹配的 "(" 的索引。

使用 栈，每次遇到 "("，都将它的索引压入栈中。每次遇到 ")"，都从栈中移除一个索引，用该 ")" 与栈顶的 "(" 匹配。栈的深度等于余量。需要执行以下操作：


使用 栈，每次遇到 "("，都将它的索引压入栈中。每次遇到 ")"，都从栈中移除一个索引，用该 ")" 与栈顶的 "(" 匹配。栈的深度等于余量。需要执行以下操作：

如果在栈为空时遇到 ")"，则删除该 ")"，防止余量为负。
如果扫描到字符串结尾有 "("，则删除它，防止余量不为 0。


这种贪心方法是否安全，即为什么不删除前面的 ")"？可以这样做，但是不，因为这不会对删除的 ")" 总数有任何影响。



For efficiency:
在 字符串 的 任意一个位置 添加、删除或更改一个字符的操作都是 O(n)O(n) 的，因为 String 是 不可变的。每次修改整个字符串都会重建。

从 list，vector，array 的非最后一个位置添加或删除元素也是 O(n)O(n) 的，因为在其他索引操作需要创建新的空间或者填充空间。

检查一个元素是否在 list 中，常使用 线性查找，即使二分查找也要 O(\log n)O(logn) 的复杂度，这并不是理想的效率。

一种安全策略是遍历字符串，然后将要保留的字符插入到 list（Python）或 StringBuilder（Java）中。遍历完所有字符后，只需要一步 O(n)O(n) 的操作将其转换为字符串

检查某个元素是否在 集合 中需要 O(1)O(1)。如果需要删除的所有索引都在集合中，那么可以遍历字符串的每个索引，检查当前索引是否在集合中。如果不是，则将该索引处的字符添加到 StringBuilder 中



Accumulation function
StringBuilder:

StringBuilder.reverse()
append(String str)
Appends the specified string to this character sequence.
StringBuilder	append(StringBuffer sb)
capacity()
charAt(int index)
Returns the char value in this sequence at the specified index.
delete(int start, int end)
Removes the characters in a substring of this sequence.
deleteCharAt(int index)
Removes the char at the specified position in this sequence.

deleteCharAt(int index)
Removes the char at the specified position in this sequence.

indexOf(String str)
Returns the index within this string of the first occurrence of the specified substring.
indexOf(String str, int fromIndex)
Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
StringBuilder	insert(int offset, boolean b)
Inserts the string representation of the boolean argument into this sequence.
StringBuilder	insert(int offset, char c)
Inserts the string representation of the char argument into this sequence.

length()
Returns the length (character count).

reverse()
Causes this character sequence to be replaced by the reverse of the sequence.

setCharAt(int index, char ch)
The character at the specified index is set to ch.
void	setLength(int newLength)
Sets the length of the character sequence.
CharSequence	subSequence(int start, int end)
Returns a new character sequence that is a subsequence of this sequence.
String	substring(int start)
Returns a new String that contains a subsequence of characters currently contained in this character sequence.
String	substring(int start, int end)
Returns a new String that contains a subsequence of characters currently contained in this sequence.
String	toString()
Returns a string representing the data in this sequence.
void	trimToSize()
Attempts to reduce storage used for the character sequence.



Interface CharSequence:
char	charAt(int index)
Returns the char value at the specified index.
int	length()
Returns the length of this character sequence.
CharSequence	subSequence(int start, int end)
Returns a new CharSequence that is a subsequence of this sequence.
String	toString()
Returns a string containing the characters in this sequence in the same order as this sequence.

int length()
Returns the length of this character sequence. The length is the number of 16-bit chars in the sequence.

char charAt(int index)
Returns the char value at the specified index. An index ranges from zero to length() - 1. The first char value of the sequence is at index zero, the next at index one, and so on, as for array indexing.
If the char value specified by the index is a surrogate, the surrogate value is returned.

Parameters:
index - the index of the char value to be returned
Returns:
the specified char value
Throws:
IndexOutOfBoundsException - if the index argument is negative or not less than length()


CharSequence subSequence(int start,
                       int end)
Returns a new CharSequence that is a subsequence of this sequence. The subsequence starts with the char value at the specified index and ends with the char value at index end - 1. The length (in chars) of the returned sequence is end - start, so if start == end then an empty sequence is returned.
Parameters:
start - the start index, inclusive
end - the end index, exclusive
Returns:
the specified subsequence
Throws:
IndexOutOfBoundsException - if start or end are negative, if end is greater than length(), or if start is greater than end

String toString()
Returns a string containing the characters in this sequence in the same order as this sequence. The length of the string will be the length of this sequence.
Overrides:
toString in class Object
Returns:
a string consisting of exactly this sequence of characters







HashSet
boolean	add(E e)
Adds the specified element to this set if it is not already present.
void	clear()
Removes all of the elements from this set.
Object	clone()
Returns a shallow copy of this HashSet instance: the elements themselves are not cloned.
boolean	contains(Object o)
Returns true if this set contains the specified element.
boolean	isEmpty()
Returns true if this set contains no elements.
Iterator<E>	iterator()
Returns an iterator over the elements in this set.
boolean	remove(Object o)
Removes the specified element from this set if it is present.
int	size()
Returns the number of elements in this set (its cardinality).

































