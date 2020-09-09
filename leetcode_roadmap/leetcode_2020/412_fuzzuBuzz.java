class Solution {
    public List<String> fizzBuzz(int n) {
        // Write your code here
        // decide if the solution is multiply of some number
        List<String> res = new ArrayList<>();
        boolean divisibleby3 = false;
        boolean divisibleby5 = false;
        for(int i=1; i<=n; i++){
            divisibleby3 = (i % 3 == 0);
            divisibleby5 = (i % 5 == 0);
            String s = "";
            if(divisibleby3){
                s = "Fizz";
                if(divisibleby5){
                    s = s + "Buzz";
                }
            }
            else if(divisibleby5){
                s = "Buzz";
            }
            else s = Integer.toString(i);
            res.add(s);
        }
        return res;
    }
}