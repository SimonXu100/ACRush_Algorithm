//notes: 难题精讲2
//1 brute froce:
// check every permutation: n*(n-1)
// set the average length is k
// running time: O(n*n*k)


// 2 tries: 避免不必要的search, create trie in inverse order
//running time:
// create trie: O(n*k*k):first K: average length, the second k: palindromes check
// search: O(n*k)
// total running time: O(n*k*k)
// based on the length of k, it could reach linear running time
class Solution {
    public List<List<Integer>> palindromePairs(String[] words){
        // 定义一个列表， 用来记录找到的配对
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        
        // 创建Trie
        for(int i=0; i<words.length; i++){
            addWord(root, words[i], i);
        }
        // 利用Trie, 找出所有的配对
        for(int i=0; i<words.length; i++){
            search(words,i,root,res);
        }
         return res;
    }
    
    //创建Trie时，从每个字符的末尾进行遍历
    public void addWord(TrieNode root, String word, int index){
        for(int i = word.length()-1; i>=0; i--){
            char ch = word.charAt(i);
            //对于每个当前字符；如果他还未添加到children哈希表中，就创建一个新的节点
            if(!root.children.containsKey(ch)){
                root.children.put(ch, new TrieNode());
            }
            // 难点： 如果该字符串从头开始到当前位置能成为回文， 则把该字符串的下标添加到这个Trie节点的回文列表中
            // eg: "aaaba", 由于从后往前遍历，当遍历到字符b时，发现aaa是回文，于是需要更新一下b指向的节点。这个节点往下有一个字符能构成回文
            
            if(isPalindrome(word, 0, i)){
                root.palindromes.add(index);
            }
            
            root = root.children.get(ch);
        }
        // 只剩一个几点，本身一定能构成回文
        root.palindromes.add(index);
        // 到达末尾
        root.index = index;
    }
    
    // 处理查找
    public void search(String[] words, int i, TrieNode root, List<List<Integer>> res){
        //处理查找字符串时，从头遍历每个字符串
        //然后尝试从Trie中查找匹配的字符串
        for(int j=0; j < words[i].length(); j++){
            //当遇到第二种情况 k1 > k2, 并且S1剩下的字符能够构成回文，则把这对组合添加到结果中
            // root.index != i, 保证非重复相同的字符串进行匹配
            if(root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length()-1) ){
                res.add(Arrays.asList(i, root.index));
            }
            
            //然后不断的向下寻找
            root = root.children.get(words[i].charAt(j));
            if(root == null) return;
        }
        
        //最后，遇到第一种（k1<k2), 第三种(k1=k2）情况
        //只需把回文列表中的字符串与S1 组合在一起既可以
        for(int j : root.palindromes){
            if(i == j) continue; //保证非重复相同的字符串进行匹配
            res.add(Arrays.asList(i,j));
        }
    }

    
    // check if the substring of word from i to j is palindrome
    public boolean isPalindrome(String word, int i, int j){
        while(i<j){
            if(word.charAt(i++) != word.charAt(j--)){
                return false;
            }
        }
        return true;
    }
        
}

class TrieNode{
    int index; // 修改TrieNode结构，用index 替换isEnd
    List<Integer> palindromes;  // 添加palindrome 列表， 用来记录从该节点往下的字符串中，能构成回文所有输入字符串的下标
    HashMap<Character, TrieNode> children;  
    
    TrieNode(){
        index = -1;
        children = new HashMap<>();
        palindromes = new ArrayList<>();
    }
}






/*
//notes
// check if the substring of word from i to j is palindrome
    public boolean isPalindrome(String word, int i, int j){
        while(i<j){
            if(word.charAt(i++) != word.charAt(j--)){
                return false;
            }
            return true;
        }
    }


// typical treeNode 
class TrieNode{
	boolean isEnd; // isEnd: 布尔值， 表示该节点是否为某字符串的结尾
	HashMap<Character, TrieNode> children; // 数组或者集合, 罗列出每个分支中包含的所有字符

	TrieNode(){
		isEnd = false;
		children = new HashMap<>();
	}
}
*/

