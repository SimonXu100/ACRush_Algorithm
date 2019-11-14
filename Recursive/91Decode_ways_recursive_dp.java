//递归
//存在不合理，不可编码的情况100
/*
class Solution {
    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
         if(chars[0] == '0'){
            return 0;
        }
        return decode(chars,chars.length-1);
    }
    //根据参数个数判断需要调用外部函数
    public int decode(char[] chars,int index){
        //sanity check
        //terminal condition
        if(index<=0){
           return 0;
        }
        //scale down and combine
        int count = 0;
        char curr = chars[index];
        char prev = chars[index-1];
        // scale down
        //the first situation 
        if(curr > '0'){
           count = decode(chars,index-1);
        }
        // the second situation
        if((prev!='0' &&prev < '2') || (prev == '2'&&curr<='6' )){
           count += decode(chars,index-2);
        }
        return count;
    }
}
*/


// 动态规划
// 时间O（N）
// 空间O（N）
// charAt()能够节省空间

class Solution {
    public int numDecodings(String s) {
        //dp： result 
        //chars: swith into array
        int[] dp = new int[s.length()+1];
        char[] chars = s.toCharArray();
        // 边界条件
        if(chars[0] == '0') return 0;
        // 初始化
        dp[0] = 1;
        dp[1] =1;
        for(int i = 2;i<=s.length();i++){
            if(chars[i-1] > '0'){
                dp[i] += dp[i-1];
            }
            if((chars[i-2]!= '0'&& chars[i-2]<'2') || (chars[i-2] == '2' && chars[i-1]<='6' )){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
        
    }
}

//更简洁的表达
//增加其实空间，减少判断
/*
class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        
        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        
        for (int i = 2; i <= s.length(); i++) {
            //如果该位不为'0'，说明该位单独成字母合法
            if (s.charAt(i - 1) != '0') { 
                dp[i] += dp[i - 1];
            }
            //如果后两位能组成"1x"（x为任意数字）或者"2x"（x小于7），说明最后两位组成字母合法
            if ((s.charAt(i - 2) == '1') || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
*/







































