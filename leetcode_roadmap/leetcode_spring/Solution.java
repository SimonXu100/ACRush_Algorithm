import java.util.Scanner;
public class Solution {
    public static int leastCost = -1;
    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int outputInt = 0;
        int x =0;
        int[] temp = new int[4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 4; j++){
                x = sc.nextInt();
                temp[j] = x;
            }
            leastCostConvertInt(temp[0],temp[1], temp[2], temp[3], 0);
            System.out.println(leastCost);
            leastCost = -1;
        } 
        */
        int[] temp = new int[4];
        temp[0] = 1;
        temp[1] = 6;
        temp[2] = 2;
        temp[3] = 3;

        leastCostConvertInt(temp[0], temp[1], temp[2], temp[3], 0);
        System.out.println(leastCost);
    }
    
    
    public static void leastCostConvertInt(int n, int m, int w2, int w3, int cost){
        int res = 0;
        if(n>=m){
            if(leastCost != -1){
                leastCost = leastCost > cost?  cost : leastCost;
            }
            else{
                leastCost = cost;
            }
            return;
        }
        leastCostConvertInt(n*2, m, w2, w3, cost+w2);
        leastCostConvertInt(n*3, m, w2, w3, cost+w3);
    }
}