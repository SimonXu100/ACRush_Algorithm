// backtracking, str
// combinations, no order
//running time: 3^N * 4 * N
class Solution {
    public List<String> letterCombinations(String digits) {
        
        if(digits == null) return null;
        List<String> res = new ArrayList<String>();
        if(digits.length() == 0) return res;
        if(digits.length() == 1 && digits.charAt(0) == '1') return res;
        ArrayList<ArrayList> letters = new ArrayList<ArrayList>();
        // search order
        for(int i = 0,temp = 0;i < 26; i=i+temp){
            if(i == 15 || i == 22){
                temp = 4;
            }
            else{
                temp = 3;
            }
            ArrayList temp_list = new ArrayList<Character>();
            for(int j = i; j < i+temp ; j++){
                char cur_char = (char) (j+97);
                temp_list.add(cur_char);
            }
            letters.add(temp_list);
        }
        // remove all '1' in digits
        StringBuilder digits_clean = new StringBuilder();
        for(int i=0;i<digits.length();i++){
            if(digits.charAt(i) != '1'){
                digits_clean.append(digits.charAt(i));
            }
        }
        String digits_clean_string = digits_clean.toString();
        backtrack_helper(digits_clean_string,letters,0, digits.length(), res, null);
        return res;
    }
    
    public void backtrack_helper(String digits, ArrayList<ArrayList> letters, int count, int n, List<String> res, char[] cur_string){
        if(count == n){
            String temp_string =  new String(cur_string);
            res.add(temp_string);
            return;
        } 
        if(cur_string == null && count == 0)  cur_string = new char[n];
        int cur_digit = (int) digits.charAt(count) - 48;
        ArrayList temp_list = letters.get(cur_digit - 2);
        int temp_count = temp_list.size();
        for(int i = 0; i< temp_count; i++){
             cur_string[count] = (char) temp_list.get(i);
             backtrack_helper(digits, letters, count+1, n, res ,cur_string);
             // directly cover
        }
        return;  
    }
}

// example code
// fixed mapping, hashmap

Map<String, String> phone = new HashMap<String, String>() {{
    put("2", "abc");
    put("3", "def");
    put("4", "ghi");
    put("5", "jkl");
    put("6", "mno");
    put("7", "pqrs");
    put("8", "tuv");
    put("9", "wxyz");
  }};

  List<String> output = new ArrayList<String>();

  public void backtrack(String combination, String next_digits) {
    // if there is no more digits to check
    if (next_digits.length() == 0) {
      // the combination is done
      output.add(combination);
    }
    // if there are still digits to check
    else {
      // iterate over all letters which map 
      // the next available digit
      String digit = next_digits.substring(0, 1);
      String letters = phone.get(digit);
      for (int i = 0; i < letters.length(); i++) {
        String letter = phone.get(digit).substring(i, i + 1);
        // append the current letter to the combination
        // and proceed to the next digits
        backtrack(combination + letter, next_digits.substring(1));
      }
    }
  }

  public List<String> letterCombinations(String digits) {
    if (digits.length() != 0)
      backtrack("", digits);
    return output;
  }
}


























































