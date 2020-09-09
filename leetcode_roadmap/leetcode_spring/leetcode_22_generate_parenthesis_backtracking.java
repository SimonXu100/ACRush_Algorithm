// method 1
// backtracking
// keep validation of parenthesis
// running time: 
class Solution {
    public List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        backtracking_helper(sb, n, n);
        return res;
    }
    
    public void backtracking_helper(StringBuilder sb, int i, int j){
        // i: the number of '(' left
        // j: the number of ')' left
        
        // validation check 
        if(j < i){
            return;
        }
        
        if(i == 0 && j == 0){
            // meet requirement
            res.add(sb.toString());
            return;
        }
        
        if(i>0){
            sb.append('(');
            backtracking_helper(sb, i-1, j);
            sb.deleteCharAt(sb.length()-1);
        }
        if(j>0){
            sb.append(')');
            backtracking_helper(sb, i, j-1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}




























