class Trie {
    // build root node
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    //时间复杂度分析：T:O(m): m is the length of key
    //空间复杂度分析：S：O(m): 最坏的情况下无公共前缀，需要插入m个节点
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node  = root;
        for(int i =0;i<word.length();i++){
            char currentChar  = word.charAt(i);
            if(!node.containsKey(currentChar){
               node.put(currentChar,new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }
    
    //时间复杂度分析：T:O(m)
    //空间复杂度：S:O(1)
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node!=null&&node.isEnd();
    }

    //search--helper function
    //using the last node present the status of results
    public TrieNode searchPrefix(Strgin word){
        TrieNode node = root;
        for(int i =0;i<word.length();i++){
            char  curLetter = word.charAt(i);
            if(node.containsKey(curLetter)){
               node  = node.get(curLetter);
            }
            else{
                return null;
            }
            return node;
        }
    }
    
    //查找Trie树中的键前缀
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(word);
        return node != null;
    }
}

// 1 数组记录children node
// 2 记录是否为结束状态
//TrieNode
class TrieNode{
    //
    private TrieNode[] links;
    private final int R = 26;
    private boolean isEnd;

    public TrieNode(){
        links = new TrieNode[R];
    }

    public boolean containsKey(char c){
        return links[c-'a']!=null;
    }

    public TrieNode get(char c){
        return links[c-'a'];
    }

    public void put(char c, TrieNode node){
        links[c-'a'] = node;
    }

    public void setEnd(){
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



// Trie 重要操作
//插入，从根节点开始搜索
//1、链接存在：沿链接移动到下一子层，算法继续搜索下一键字符
//2、链接不存在：创建一个新的节点，并将他与父节点的链接相连，该链接与当前键字符字符相匹配

//搜索
//用第一个键字符从根开始，检查当前节点中与键字符对应的链接
//1、存在链接，移动到该链接后面路径的下一个节点，并继续搜索下一键字符
//2、不存在链接,且当前节点标记为End,返回true
//否则存在两种情况
// 还有键字符剩余，但无法跟随Trie树的键路径，找不到键
// 没有键字符剩余，但当前节点没有标记为isEnd.即：待查找键只是Trie树中另一个键的前缀
//3寻找是否存在指定前缀，只用关心是否存在。不用关心时候为isEnd



