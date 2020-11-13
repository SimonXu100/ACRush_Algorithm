// 相似题目：leetcode 753
// 通过图中所有边恰好一次且行遍所有顶点的通路称为欧拉通路。
// 通过图中所有边恰好一次且行遍所有顶点的回路称为欧拉回路。
// 具有欧拉回路的无向图称为欧拉图。
// 具有欧拉通路但不具有欧拉回路的无向图称为半欧拉图。

// 经典算法：Hierholzer 算法
// Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
// 从起点出发，进行深度优先搜索。
// 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
// 如果没有可移动的路径，则将所在节点加入到栈中，并返回。

// 我们可以改变入栈的规则，当我们遍历完一个节点所连的所有节点后，我们才将该节点入栈（即逆序入栈)
// 拓扑
// 当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈
//「一笔画」地走完所有边，最终的栈中逆序地保存了「一笔画」的结果。我们只要将栈中的内容反转，即可得到答案


// dfs, graph
// 计算入度和出度
// 多个有效行程， 自然排序最小的行程
// 欧拉通路
// 抽象：给定一个 n 个点 m 条边的图，要求从指定的顶点出发，经过所有的边恰好一次（可以理解为给定起点的「一笔画」问题），使得路径的字典序最小
// 每步贪心的选择字典序最小， 优先队列
// 存在死胡同节点
// 堆： 默认字典序排列
// running time: O(mlgm) m : 边的数量
// space: O(m)

class Solution {
    private Map<String, PriorityQueue<String>> map;
    private List<String> itinerary;
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<String, PriorityQueue<String>>();
        itinerary = new LinkedList<String>();
        // in order
        for(List<String> ticket : tickets){
            String src = ticket.get(0);
            String dst = ticket.get(1);

            if(!map.containsKey(src)){
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        // output in reverse order
        Collections.reverse(itinerary);
        return itinerary;
    }
    public void dfs(String src){
        while(map.containsKey(src) && map.get(src).size() > 0){
            dfs(map.get(src).poll());
        }
        // end point
        itinerary.add(src);
    }
}



// python 版本
// 利用 heapq 和 list 实现堆的功能
// vec = collections.defaultdict(list)
// heaqp.heapify(vec[key])

// list: 逆序
// stack[::-1]
// 嵌套函数
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        # built-in function
        def dfs(src : str):
            while vec[src]:
                dfs(heapq.heappop(vec[src]))
            stack.append(src)

        vec = collections.defaultdict(list)
        for src, dst in tickets:
            vec[src].append(dst)
        # sort
        for key in vec:
            heapq.heapify(vec[key])
        # result
        stack = list()
        dfs("JFK")
        return stack[::-1];


        




























