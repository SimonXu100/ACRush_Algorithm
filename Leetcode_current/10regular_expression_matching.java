//backtracking
class Solution {
    public boolean isMatch(String s, String p) {
    	if(s == null || p == null){
    		return false;
    	}

    	return isMatch(s,0,p,0);
    }

    public boolean isMatch(String s, int i, String p, int j){
    	int m = s.length();
    	int n = p.length();

    	//判断pattern and string 是否都扫描完毕
    	if(j == n ){
    		return i == m;
    	}
        
        //next char is not '*': 必须满足当前字符并递归到下一层
        if(j == n-1 || p.charAt(j+1) != '*'){
        	return (i < m) &&( p.charAt(j) == '.' || (s.charAt(i) == p.charAt(j)) ) && isMatch(s,i+1,p,j+1);
        }

        // next char is '*': 如果有连续s[i]出现并且都等于p[j], 一直尝试下去
        if(j<n-1 && p.charAt(j+1) == '*'){
        	while( (i<m) && (p.charAt(j) == '.' || (s.charAt(i) == p.charAt(j)))){
        		if(isMatch(s,i,p,j+2)){
        			return true;
        		}
        		i++;
        }

       }
       //当i耗尽，继续下去进行
       //当 i 字符和 j 字符不相同，或者 i 已经遍历完了 s 字符串，(其中一种同时 j 字符后面跟着一个 '*'的情况，只能用 '*'组合去表示一个空字符串，然后递归下去)
       return isMatch(s, i, p, j + 2);        
    }
}
