import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int m = sc.nextInt();
            List<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < m; j++){
                x = sc.nextInt();
                list.add(x);
            }
            System.out.println(maxValueXOR(list, list.size())); 
        }  
    }
    
    public int maxValueXOR(List<Integer> list, int length){
        int max = 0;
        
        
        
    }
    
    
    
}