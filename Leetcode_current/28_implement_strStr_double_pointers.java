
// built-in method for java
//haystack.indexOf(needle); 
// method 1: brute force
// 从左到右扫描，每一个位置，扫描遍历是否等于needle字符串
// running time: assume the length of haystack:m
// assume the length of needle: n
// the total running time：O(m*n)
/*
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        for(int i=0;;i++){ //i指针，用来遍历haystack字符串
            for(int j=0;;j++){ //j指针，用来遍历needle字符串
                if(j==needle.length()) return i;   //needle字符串扫描完毕，表示找到相同的字符串匹配，返回匹配字符串第一次出现的位置
                if(i+j==haystack.length()) return -1; // haystack 字符串扫描完毕，表示扫描完haystack字符串，未找到匹配的位置
                //如比较的两个字符不相等，则退出内循环。i指针向前挪一个位置，继续刚才的比较
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
            }
        }
    }
}
*/

//method 2: KMP algorithm
class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if(n==0) return 0;
        //求出needle的LPS,即最长的公共前缀和后缀数组
        int[] lps = getLPS(needle);
        int i=0, j=0; //i指针用来扫描haystack, j指针用来扫描needle
        while(i<m){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
                if(j==n) return i-n; //一旦发现j已经扫描完needle字符串，说明已经找到了needle,立即返回它在haystack中的起始位置
            }
            // 在循环体中，当发现i指针与j指针指向的字符不相同时，尝试进行跳跃操作
            else if(j>0){
                j = lps[j-1];
            }
            //j=0, 表示此处needle的第一个字符已不同于haystack的字符，尝试对比haystack的下一个字符，故i++
            else{
               i++; 
            }
        }
        return -1;
    }

    public int[] getLPS(String str){
        //初始化一个lps数组用来保存最终的结果
        int[] lps = new int[str.length()];
        int i=1,len=0;
        //由于lps的第一个值一定为0，且长度为1的字符串的最长公共前缀和后缀的长度为0，直接从第二个位置开始遍历
        //初始化当前最长的lps长度为0， 用len变量记录一下
        while(i<str.length()){
            //i 指针能够延续前缀和后缀，更新lps值为len+1
            if(str.charAt(i)==str.charAt(len)){
                lps[i++] = ++len;
            }
            // 判断len是否大于0，然后尝试第二长的前缀和后置，看看是否能够继续延续
            else if(len>0){
                len = lps[len-1];
            }
            // 尝试了左右前缀和后缀，则当前的lps为0，i++
            else{
                i++;
            }
        }
        return lps;   
    }
}












