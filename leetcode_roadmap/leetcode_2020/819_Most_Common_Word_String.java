// String
// simple counting

// at least one word, the answer is unique
// lowercase
// case sensitive
// count and record the elements with highest frequency  
// running time: O(P+B)
// Space:O(P + B)
// P: the length of paragraph
// B: the length of banned list
/*
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // for searching if a particular word in banned list
        Set<String> banned_set = new HashSet<String>();
        for(String s : banned) banned_set.add(s);
        
        Map<String, Integer> count = new HashMap<String, Integer>();
        String[] words = paragraph.split("\\W+");
        
        int max_frequency  = 0;
        int temp = 0;
        String string_max = "";
        String temp_string = "";
        for(String s : words){
            temp_string = s.toLowerCase();
            if(!banned_set.contains(temp_string)){
                temp = count.getOrDefault(temp_string, 0);
                count.put(temp_string, temp+1);
                if(temp+1 > max_frequency){
                    max_frequency = temp + 1;
                    string_max = temp_string;
                }
            }
        }
        
        return string_max;    
    }
}
*/

// self filter out: regular regression
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // for searching if a particular word in banned list
        Set<String> banned_set = new HashSet<String>();
        for(String s : banned) banned_set.add(s);
        
        Map<String, Integer> count = new HashMap<String, Integer>();
        
        int max_frequency  = 0;
        int temp = 0;
        String string_max = "";
        
        // add "." to paragraph in case of last word cannot be found
        paragraph += ".";
        
        StringBuilder word = new StringBuilder();
        for(char c : paragraph.toCharArray()){
            if(Character.isLetter(c)){
                word.append(Character.toLowerCase(c));
            }
            else if(word.length()>0){
                String finalword = word.toString();
                if(!banned_set.contains(finalword)){
                    temp = count.getOrDefault(finalword, 0);
                    count.put(finalword, temp+1);
                    if(temp+1 > max_frequency){
                        max_frequency = temp +1;
                        string_max = finalword;
                    }
                }
                word = new StringBuilder();   
            }
        }
        return string_max;    
    }
}












// accumulation function
// String processing
// Java split()
//The limit parameter controls the number of times the pattern is applied and therefore affects the length of the resulting array
/*
public String[] split(String regex,
             int limit)

public String[] split(String regex)

Java String
split: JavaRegularExpressions
https://www.vogella.com/tutorials/JavaRegularExpressions/article.html



// String.toLowerCase()


// Character.isLetter()
// Character.toLowerCase()


StringBuilder s
s.append()


*/
