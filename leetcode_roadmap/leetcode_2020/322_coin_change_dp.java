

// initialzied helper function
// Arrays.fill(dp, max);

// add virtual head, eliminate the  condition
Fi = min F(i−cj))+1  for j=0…n−1
if i >= cj

​Cj 代表的是每个硬币的面值，所以 F(i)是能组成的金额的最小数量

class Solution {
    public int coinChange(int[] coins, int amount) {
       if(amount ==0) return 0;
       int[] num_of_coins = new int[amount];
       // initialize, for every amount, if amount == any one of element in array, then the fewest number is 1
       Arrays.fill(num_of_coins,amount+1);
       for(int i=0;i<coins.length;i++){
           if(coins[i]<=amount){
               num_of_coins[coins[i]-1] = 1; 
           }
       }
       // bottom_up iteration
       for(int j=1;j<amount;j++){
           int temp_fewest = num_of_coins[j];
           for(int k=0;k<coins.length;k++){
               int temp = amount+1;
               if(j+1>coins[k]){
                   if(num_of_coins[j-coins[k]]>amount) continue;
                   else temp = num_of_coins[j-coins[k]]+1;
               } 
               if(temp<temp_fewest) temp_fewest = temp;
           }
           num_of_coins[j] = temp_fewest;
       }
       // print out it to see sth happening
       if(num_of_coins[amount-1]>amount) return -1;
       else return num_of_coins[amount-1];
    }
}
/*
// method 2: add virtual head, reduce costs
public class Solution {
  public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }
}
*/














