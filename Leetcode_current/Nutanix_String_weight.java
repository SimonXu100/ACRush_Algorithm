// https://www.geeksforgeeks.org/maximum-weight-transformation-of-a-given-string/
// DP
/*
Weight of string = Weight of total pairs + 
                   weight of single characters - 
                   Total number of toggles.

Two consecutive characters are considered as pair only if they 
are different. 
Weight of a single pair (both character are different) = 4
Weight of a single character = 1 
*/

// Java program to find maximum  
// weight transformation of a 
// given string 
class GFG { 
      
    // Returns wieght of the maximum  
    // weight transformation 
    static int getMaxRec(String str, int i, 
            int n, int[] lookup)  
    { 
        // Base case 
        if (i >= n)  
        { 
            return 0; 
        } 
  
        // If this subproblem is already solved 
        if (lookup[i] != -1)  
        { 
            return lookup[i]; 
        } 
  
        // Don't make pair, so  
        // weight gained is 1 
        int ans = 1 + getMaxRec(str, i + 1, 
                            n, lookup); 
  
        // If we can make pair 
        if (i + 1 < n) 
        { 
              
            // If elements are dissmilar,  
            // weight gained is 4 
            if (str.charAt(i) != str.charAt(i + 1)) 
            { 
                ans = Math.max(4 + getMaxRec(str, i + 2, 
                                n, lookup), ans); 
            }  
              
            // if elements are similar so for  
            // making a pair we toggle any of  
            // them. Since toggle cost is 
            // 1 so overall weight gain becomes 3 
            else 
            { 
                ans = Math.max(3 + getMaxRec(str, i + 2, 
                                n, lookup), ans); 
            } 
        } 
  
        // save and return maximum 
        // of above cases 
        return lookup[i] = ans; 
    } 
  
    // Initializes lookup table 
    // and calls getMaxRec() 
    static int getMaxWeight(String str)  
    { 
        int n = str.length(); 
  
        // Create and initialize lookup table 
        int[] lookup = new int[n]; 
        for (int i = 0; i < n; i++) 
        { 
            lookup[i] = -1; 
        } 
  
        // Call recursive function 
        return getMaxRec(str, 0, str.length(), 
                            lookup); 
    } 
  
    // Driver Code 
    public static void main(String[] args) 
    { 
  
        String str = "AAAAABB"; 
        System.out.println("Maximum weight of a"
                        + " transformation of "
                        + str + " is "
                        + getMaxWeight(str)); 
    } 
} 
  
// This code is contributed by 29AjayKumar 