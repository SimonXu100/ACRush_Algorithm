//有一种叫做有序字典的数据结构，综合了哈希表和链表，在 Python 中为 OrderedDict，在 Java 中为 LinkedHashMap。
1 
1.1 
LinkedHashMap: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
class LRUCache extends LinkedHashMap<Integer, Integer>

super(capacity, 0.75F, true);
@Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }

该方法是用来被重写的，一般地，如果用LinkedHashmap实现LRU算法，就要重写该方法。比如可以将该方法覆写为如果设定的内存已满，则返回true，
这样当再次向LinkedHashMap中putEntry时，在调用的addEntry方法中便会将近期最少使用的节点删除掉（header后的那个节点）


// functions:
super.getOrDefault(key, -1);

super.put(key,value);

LinkedHashMap采用的hash算法和HashMap相同，但是它重新定义了Entry。LinkedHashMap中的Entry增加了两个指针 before 和 after，它们分别用于维护双向链接列表。特别需要注意的是，
next用于维护HashMap各个桶中Entry的连接顺序，before、after用于维护Entry插入的先后顺序的，源代码如下：
private static class Entry<K,V> extends HashMap.Entry<K,V> {

    // These fields comprise the doubly linked list used for iteration.
    Entry<K,V> before, after;

    Entry(int hash, K key, V value, HashMap.Entry<K,V> next) {
        super(hash, key, value, next);
    }
    ...
}


初始容量 和负载因子是影响HashMap性能的两个重要参数。同样地，它们也是影响LinkedHashMap性能的两个重要参数。此外，LinkedHashMap 增加了双向链表头结点 header和标志位 accessOrder两个属性用于保证迭代顺序。


1.2 LinkedHashMap with LRU
LinkedHashMap区别于HashMap最大的一个不同点是，前者是有序的，而后者是无序的。为此，LinkedHashMap增加了两个属性用于保证顺序，分别是双向链表头结点header和标志位accessOrder

header是LinkedHashMap所维护的双向链表的头结点，而accessOrder用于决定具体的迭代顺序

// 当accessOrder == true, 按照访问的先后顺序排列，包括put and get
当accessOrder标志位为true时，表示双向链表中的元素按照访问的先后顺序排列，可以看到，
虽然Entry插入链表的顺序依然是按照其put到LinkedHashMap中的顺序，但put和get方法均有调用recordAccess方法（put方法在key相同时会调用）。
recordAccess方法判断accessOrder是否为true，如果是，则将当前访问的Entry（put进来的Entry或get出来的Entry）移到双向链表的尾部（key不相同时，put新Entry时，会调用addEntry，它会调用createEntry，该方法同样将新插入的元素放入到双向链表的尾部，既符合插入的先后顺序，又符合访问的先后顺序，因为这时该Entry也被访问了）；

// 当accessOrder == false, 按照插入的先后循序排列
当标志位accessOrder的值为false时，表示双向链表中的元素按照Entry插入LinkedHashMap到中的先后顺序排序，即每次put到LinkedHashMap中的Entry都放在双向链表的尾部，这样遍历双向链表时，Entry的输出顺序便和插入的顺序一致，这也是默认的双向链表的存储顺序

使用LinkedHashMap实现LRU的必要前提是将accessOrder标志位设为true以便开启按访问顺序排序的模式




2 OrderedDict
from collections import OrderedDict

2.1 
self.move_to_end(key)
OrderedDict.popitem(last=True)¶
The popitem() method for ordered dictionaries returns and removes a (key, value) pair. The pairs are returned in LIFO order if last is true or FIFO order if false

from collections import OrderedDict
class LRUCache(OrderedDict):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self:
            return - 1
        
        self.move_to_end(key)
        return self[key]

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        if key in self:
            self.move_to_end(key)
        self[key] = value
        if len(self) > self.capacity:
            self.popitem(last = False)























