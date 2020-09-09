//arrays+double pointers
//暴力法：O（n^2）
//超出时间限制
/*
class Solution {
    public int maxArea(int[] height) {
       int maxVolume = 0;
       int i = 0,j = 0;
       while(i<height.length){
           j = i+1;
           while(j<height.length){
               int temp = Math.min(height[i],height[j]);
               maxVolume = Math.max(temp*(j-i),maxVolume);
               j++;
           }
           i++;
       }
        return maxVolume;
    }
}
*/
//由计算方式，从两边向中间靠近
//每次移动较短的那段（移动较长只会减小结果，移动较短有可能增加结果）
class Solution {
    public int maxArea(int[] height) {
       int maxVolume = 0;
       int i = 0,j = height.length-1;
       while(i<j){
          maxVolume = Math.max(Math.min(height[i],height[j])*(j-i),maxVolume);     
          if(height[i]<height[j]){
              i++;
          }
          else{ 
              j--;
          }
       }
        return maxVolume;
    }
}






















