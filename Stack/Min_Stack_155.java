import java.util.Vector;
/** 0(n)运行时间，暴力方法解决
class MinStack {
    Vector<Integer> v;
    private int top;
    public MinStack() {
    v = new Vector<Integer>();
    top = -1;
    }
    public void push(int x) {
        // 特殊情况 top==-1,表示没有元素
        top++;
        v.add(x);
    }
    
    public void pop() {
        v.removeElementAt(top);
        top--;
    }
    
    public int top() {
        return v.elementAt(top); 
    }
    
    public int getMin() {
        // 再添加一个数据结构
        int min = Integer.MAX_VALUE;
     
        int size = v.size();
        for (int i = 0; i < size; i++) {
         if(v.elementAt(i)<min){min = v.elementAt(i); };
        }
        return min;

    }
}
*/
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


// 辅助栈：
// constant time for searching
// 辅助空间
// constant time for searching
// 辅助空间
class MinStack {
    //private Stack<Integer> stack;
    Vector<Integer> v;
    Vector<Integer> min;
    private int top;
    private int top_min;
    //int min_initial = Integer.MAX_VALUE;
    public MinStack() {
    v = new Vector<Integer>();
    min = new Vector<Integer>();
    top = -1;
    //min.add(min_initial);
    top_min=-1;
    }
    public void push(int x) {
        // 特殊情况 top==-1,表示没有元素
        top++;
        v.add(x);
        if(min.isEmpty() || x <= min.elementAt(top_min)){
            top_min++;
            min.add(x);
            //System.out.println(x);
        }
    }
    
    public void pop() {
        // 第一遍检错，发现没有进入
        if(top() == min.elementAt(top_min)){
            min.removeElementAt(top_min);
            top_min--;
            //System.out.println(top_min);
        }
        v.removeElementAt(top);
        top--;
    }
    
    public int top() {
        return v.elementAt(top); 
    }
    
    public int getMin() {
        
        return min.elementAt(top_min);
    }
}
 


