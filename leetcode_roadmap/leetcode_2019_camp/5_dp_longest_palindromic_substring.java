//动态规划
//给定一个最长回文子串（非序列），组合模式不同
//区间类型
//存储开始和结束位置
//若为回文子串：则标记为1
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        //举例满足条件的例子之一
        int start = 0;
        int end = 0;
        if(n==0){
            return "";
        }
        //初始化:所有单个字符成回文字符串
        //所有双子字符
        for(int i = 0;i<n;i++) dp[i][i] = 1;
        for(int i = 0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 1;
                //列举最后一个
                start = i;
                end = i+1;
            }
        }
        //长度慢慢递增，若此长度2次未更新最长字符串则直接退出
        for(int len = 3;len<=n;len++){
            for(int i = 0;i<n-len+1;i++){
                //自底向上不断递推
                int j = i+len-1;
                if(s.charAt(i) == s.charAt(j)&&dp[i+1][j-1] == 1){
                    dp[i][j] = 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start,end+1);
    }
}

//改正：连续两次都没有更新数据则直接跳出循环
//为改变级别，但加快了计算
/*
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        //dp[i][j]:i到j是否构成回文子串。若构成回文子串赋值为1，否则赋值为0
        int[][] dp = new int[n][n];
        //举例满足条件的例子之一
        int start = 0;
        int end = 0;
        //用来记录更新情况
        boolean previous = false;
        boolean current = false;
        if(n==0){
            return "";
        }
        //初始化:所有单个字符成回文字符串
        //所有双子字符
        for(int i = 0;i<n;i++) dp[i][i] = 1;
        previous = true;
        for(int i = 0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 1;
                //列举最后一个
                start = i;
                end = i+1;
                current = true;
            }
        }
        //长度慢慢递增，若此长度2次未更新最长字符串则直接退出
        for(int len = 3;len<=n;len++){
            previous = current;
            current = false;
            for(int i = 0;i<n-len+1;i++){
                //自底向上不断递推
                int j = i+len-1;
                if(s.charAt(i) == s.charAt(j)&&dp[i+1][j-1] == 1){
                    dp[i][j] = 1;
                    start = i;
                    end = j;
                    current = true;
                }
            }
            if(!current&&!previous){
                break;
            }
        }
        return s.substring(start,end+1);
    }
}
*/
//中心扩展算法
//扩展中心存在2n-1个
//分析：T:O(n^2)
//空间：S:O(1):常数空间
class Solution {
    public String longestPalindrome(String s) {
       int n = s.length();
       if(s==null||n==0) return "";
       int start=0, end = 0;
       //循环扩展
       for(int i = 0; i<n;i++){
           //奇数
           int len1 = expandAround(s,i,i);
           //偶数,保证数据通用性
           int len2 = expandAround(s,i,i+1);
           int len = Math.max(len1,len2);
           if(len>end-start){
               start = i - (len-1)/2;
               end = i + len/2;
           }
       }
       return s.substring(start,end+1);
    }
    //扩展
    //返回扩展达到回文字符串长度:正确：往外扩展一个错误边界
    //注意为向外扩展
    private int expandAround(String s, int left, int right){
      int L = left;
      int R = right;
      while(L>=0&&R<s.length()&&s.charAt(L) == s.charAt(R)){
          L--;
          R++;
      }
      //结合边界情况
      return R-L-1;
    }
}
