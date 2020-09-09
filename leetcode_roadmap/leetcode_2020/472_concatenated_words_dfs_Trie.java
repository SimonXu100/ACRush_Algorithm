// method 1 dfs + set
// running time: 
// backtracking
// search if all elements maaking with up the current string, could be found at set
// running time: O(n^2)
// starting points and count the step
// notes: removing the effect from same word in the set
// bugs: check why stack overflow
// this method is better

class Solution {
    Set<String> hashset;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // sanity check
        List<String> res = new ArrayList<String>();
        if(words.length < 2 ) return res;
        
        // build hashset
        hashset = new HashSet<String>(Arrays.asList(words));
        for(String s : words){
            hashset.remove(s);
            if(backtracking_helper(s, 0)) res.add(s);
            hashset.add(s);
        }
        return res;  
    }
    // input word: substring
    // count: record the step, how many word I used
    
    public boolean backtracking_helper(String word, int count){
        if(hashset.contains(word)) return true;
        for(int i=1; i<word.length(); i++){
            if(hashset.contains(word.substring(0,i)) && backtracking_helper(word.substring(i), count+1)){
                return true;
            }
        }
        return false;  
    }
}


// method 2: Trie + DP
// backtrackingm, Trie 更高效的搜索
// 字典前缀树
// standard version
// runnign time: O(n):n the total number of all characters
// space: O(n)
/*
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // sanity check
        List<String> res = new ArrayList<String>();
        if(words.length < 2 ) return res;
        
        // build the Trie
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        
        // backtracking
        for(String word : words){
            if(trie.search(word,0)) res.add(word);
        }
        return res;
    }
    
}

class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    
    public void insert(String word){
        TrieNode node = root;
        for(int i=0; i<word.length(); i++){
            int c = word.charAt(i) - 'a';
            if(node.children[c] == null){
                node.children[c] = new TrieNode();
            }
            node = node.children[c]; 
        }
        node.val = word;
        // if set the existence of String as End tage, we can use String val as a tag
    }
    
    public boolean search(String word, int idx){
        TrieNode node = root;
        for(int i=idx; i<word.length(); i++){
            int c = word.charAt(i) - 'a';
            node = node.children[c];
            if(node == null) return false;
            if( i< word.length() - 1){
                if(node.val != null && !word.equals(node.val)){
                    if(search(word, i+1)) return true;
                }
            }
        }
        return (node.val != null && !word.equals(node.val));
    }
    
    
}

class TrieNode{
    TrieNode[] children;
    //boolean isEnd; 
    // can also be replaced by  String val, here also used to avoid duplication
    String val;
    
    public TrieNode(){
        val = null;
        children = new TrieNode[26];
    }
}

*/









// accumulation function
// string.substing()
// String substring(int beginIndex, int endIndex)
// String substring(int beginIndex)



// add all elements
// 1  set.addAll(Arrays.asList(words));

// 2  when initialization
//    Set<String> hashset = new HashSet<String>(Arrays.asList(words));


// set.remove()





// Trie tree
// Class: TrieNode
// Class: Trie
// main function in Trie: insert() and search()













































// clean code with trie
public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();     
        Trie root = new Trie();
        // Insert each word 
        for (String word : words) {
            if (word.length() == 0) continue;
            Trie node = root;         
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) 
                    node.next[c - 'a'] = new Trie();
                node = node.next[c - 'a'];
            }
            node.word = word;
        }    
        for (String word : words)
            if (search(word, 0, root, root))
                result.add(word);
        
        return result;
    }
    
    private boolean search(String word, int index, Trie node, Trie root) {
        // tricky : the last word of the concatenated word is definitely storing the last word
        if (index == word.length())
            return node.word != null && !node.word.equals(word); 
        
        Trie current = node.next[word.charAt(index) - 'a'];
        
        if (current == null) return false;
        
        // only time when you found word and the search for the rest of the words are true which is searched from the root
        if (current.word != null && 
            search(word, index + 1, root, root)) return true;
        
        // Keep moving with the next index
        return search(word, index + 1, current, root);
    }
    
    class Trie {
        Trie[] next = new Trie[26];
        String word;
    }
























