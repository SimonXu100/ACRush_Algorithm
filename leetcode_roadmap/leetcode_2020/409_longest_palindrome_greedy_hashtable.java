// String converted to Character Array
// String s
// s.toCharArray()
// HashTable




// method 1: brute force:
// try all possible substring and check if Palindrome
// running time: O(N^3)

// or choose one or two core, expend from the center
// running time: O(N^3)


// method 2
// Greedy and HashTable
// find the length of the longest palindromes that can be built with those letters
// idea: using hashmap record the count of every character
// if the count number of characeter is even, we can add them all to longest palindrome
// if the count number of the current character is odd,
// then we choose to add the count number of the character -1 for someone with odd count number, finally if there are at least one
// odd number, we should add 1 finally
// running time: O(n): all by one pass
// space: O(n): by HashTable
/*
class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> hashmap = new HashMap<>();
        // build hashmap
        Character c = null;
        int count = 0;
        int res = 0;
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            count = hashmap.getOrDefault(c,0);
            hashmap.put(c, count+1);
        }
        // if have character with odd number;
        boolean flag = false;
        for(Character key : hashmap.keySet()){
            count = hashmap.get(key);
            if(count % 2 == 0) res = res + count;
            else{
                res += count -1;
                flag = true;     
            }
        }
        return flag?res+1:res;
    }
}
*/


// method 3: optimization
// using fixed number of array 
// space: O(1)
// running time: O(n)
/*
class Solution {
    public int longestPalindrome(String s) {
        int[] record = new int[128];
        // build hashmap
        Character c = null;
        int count = 0;
        int res = 0;
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            record[c]++;
        }
        // if have character with odd number;
        boolean flag = false;
        for(int i=0; i<record.length; i++){
            count = record[i];
            if(count % 2 == 0) res = res + count;
            else{
                res += count -1;
                flag = true;     
            }
        }
        return flag?res+1:res;
    }
}*/


// method 3
// one pass
// more flexible record: general for odd count and even count number:
// count/n * 2 numbers could be added to res
class Solution {
    public int longestPalindrome(String s) {
        int[] record = new int[128];
        int res = 0;
        for(char c:s.toCharArray()){
            record[c]++;
        }
        
        for(int count : record){
            res += count / 2 *2;
            if(count % 2 == 1 && res % 2 == 0) res++;
        }
        return res;
    }
}







