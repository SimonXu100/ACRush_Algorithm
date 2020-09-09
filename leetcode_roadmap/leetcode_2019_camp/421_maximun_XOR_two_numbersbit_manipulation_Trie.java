// 异或运算性质
// (a ^ b = c) == (a ^ c = b) == (b ^ c = a)


// java 四种运算符号
// 1 与运算符： &
// 2 或运算符： |
// 3 非运算符： ~
// 4 异或运算符： ^


//关心这个最大的异或值需要满足什么性质，进而推出这个最大值是什么，而不必关心这个异或值是由哪两个数得来的
// idea
// 1 最大的异或值： 使高位出现更多的1
// 2 可以从最高位开始，到最低位，首先假设高位是 “1”，把这 n 个数全部遍历一遍，看看这一位是不是真的可以是“1”，否则这一位就得是“0”，判断的依据是上面“异或运算的性质”，即下面的第 3 点
// 3 如果 a ^ b = max 成立 ，max 表示当前得到的“最大值”，那么一定有 max ^ b = a 成立。我们可以先假设当前数位上的值为 “1”，再把当前得到的数与这个 n 个数的 前缀
//（因为是从高位到低位看，所以称为“前缀”）进行异或运算，放在一个哈希表中，再依次把所有 前缀 与这个假设的“最大值”进行异或以后得到的结果放到哈希表里查询一下，如果查得到，就说明这个数位上可以是“1”，否则就只能是 0
// 4 如何得到前缀，可以用掩码（mask），掩码可以进行如下构造，将掩码与原数依次进行 “与” 运算，就能得到前缀
// requirement: running time analysis: O(n)

// 红黑树是一种自平衡二叉查找树

// 位移运算符

// <<      :     左移运算符，num << 1,相当于num乘以2

// >>      :     右移运算符，num >> 1,相当于num除以2

// >>>    :     无符号右移，忽略符号位，空位都以0补齐
// java Integer to String
// System.out.println(Integer.toBinaryString(mask));

// function accumulation
// int bit = (n>>i) & 1;



// Bit manipulation
// brute force: runnning time: O(n^2)
// 用前缀锁定异或值最大的两个数

import java.util.HashSet;
import java.util.Set;
// running time: O(k^n)
/*
class Solution {
    public int findMaximumXOR(int[] nums) {
        // 先确定高位，再确定低位，贪心算法
        // 一位接着一位的确定这个数位的大小
        // 利用异或的性质
        int res = 0;
        int mask = 0;
        for(int i = 31; i>=0; i--){
            // 得到前缀的方法
            // 用异或也是可以的
            // 生成不同位置的掩码
            mask = mask | (1<<i);
            // test mask creation
            
            // check if the current high order could be '1'
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            // assume n-th digit is 1
            // previous n-1 digits are got from iteration
            // assume max XOR value
            int temp = res | (1 << i);
            // check if there exists  elements that could get this max_value
            // every step decide on digits
            for(Integer prefix : set){
                if(set.contains(prefix ^ temp)){
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }
}
*/
// summary:
// 异或运算性质
// (a ^ b = c) == (a ^ c = b) == (b ^ c = a)
//关心这个最大的异或值需要满足什么性质，进而推出这个最大值是什么，而不必关心这个异或值是由哪两个数得来的
// idea
// 1 最大的异或值： 使高位出现更多的1
// 2 可以从最高位开始，到最低位，首先假设高位是 “1”，把这 n 个数全部遍历一遍，看看这一位是不是真的可以是“1”，否则这一位就得是“0”，判断的依据是上面“异或运算的性质”，即下面的第 3 点
// 3 如果 a ^ b = max 成立 ，max 表示当前得到的“最大值”，那么一定有 max ^ b = a 成立。我们可以先假设当前数位上的值为 “1”，再把当前得到的数与这个 n 个数的 前缀
//（因为是从高位到低位看，所以称为“前缀”）进行异或运算，放在一个哈希表中，再依次把所有 前缀 与这个假设的“最大值”进行异或以后得到的结果放到哈希表里查询一下，如果查得到，就说明这个数位上可以是“1”，否则就只能是 0
// 4 如何得到前缀，可以用掩码（mask），掩码可以进行如下构造，将掩码与原数依次进行 “与” 运算，就能得到前缀



// method 2 Trie + DFS transversal
// same logical, the prefix could be calculated by Trie
// two possible condition 0 or 1 and fixed length: isEnd not necessary
// the advantage of low space cost
// runnign time: O(KN): K is the length of every element's digits: 32
// for every entry, only search 32 most times in trie


class TrieNode{
    TrieNode left;
    TrieNode right;
    public TrieNode(){
        left = null;
        right = null;
    }
}

class Trie{
    private TrieNode root;
    // initialization
    public Trie(){
        root = new TrieNode(){};
    }
    
    // two main operation
    // most proper way, insert a number or a word
    void insert(int n){
        TrieNode node =  root;
        // fixed length
        for(int i=31; i>=0; i--){
            int bit = (n >> i) & 1;
            if(bit == 0){
                if(node.left == null){
                    node.left = new TrieNode();
                }
                node = node.left;
            }
            else{
                if(node.right == null){
                    node.right = new TrieNode();
                }
                node = node.right;   
            }
        }
    }
    // transversal all elements, if the currrent digit is 1, then first find right child with 1 for XOR
    int search(int n){
        TrieNode node = root;
        int ans = 0;
        for(int i=31; i>=0; i--){
            int bit = (n >> i) & 1;
            if(bit == 0){
                if(node.right != null){
                    node = node.right;
                    ans += (int) Math.pow(2, i);
                }
                else node = node.left;
            }
            else{
                if(node.left != null){
                    node = node.left;
                    ans += (int) Math.pow(2,i);
                }
                else node = node.right;
            }  
        }
        return ans;  
    }    
}


class Solution {
    public int findMaximumXOR(int[] nums) {
        int len = nums.length;
        Trie obj = new Trie();
        int res = 0;
        int temp = 0;
        // insert
        for(int i=0; i<len; i++){
            obj.insert(nums[i]);
        }
        // search max xor for every element
        for(int i=0; i<len; i++){
            temp = obj.search(nums[i]);
            if(res < temp){
                res = temp;
            }   
        }
        return res;  
    }
}