package com.xuegao.数据结构与算法;

/**
 * <br/> @PackageName：com.xuegao
 * <br/> @ClassName：广度深度
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:19
 */
public class 广度深度 {

    // 目录
    // 多级树的深度优先遍历与广度优先遍历（Java实现）
    // 节点模型
    //      深度优先遍历
    //      广度优先遍历
    // 深度优先遍历与广度优先遍历其实是属于图算法的一种，多级树可以看做是一种特殊的图，所以多级数的深/广遍历直接套用图结构的遍历方法即可。
    //
    // 工程中后端通常会用多级树来存储页面表单的各级联动类目，本文提供了深度遍历与广度遍历的示例，在使用时只要根据你的业务需求稍加改动即可。
    //
    // 我们知道，遍历有递归，非递归两种方式。在工程项目上，一般是禁用递归方式的，因为递归非常容易使得系统爆栈。同时，JVM也限制了最大递归数量，在你的树结构非常深的时候很容易出现StackOverflowError异常，所以最好采用非递归的方式。
    //
    // public class Node {
    //     //值
    //     public int value;
    //     //所有的子节点
    //     public ArrayList<Node> nexts;
    //
    //     public Node(int value) {
    //         this.value = value;
    //     }
    // }
    // 深度优先搜索英文缩写为DFS即Depth First Search.其过程简要来说是对每一个可能的分支路径深入到不能再深入为止，而且每个节点只能访问一次。多级树可以看做一个特殊的图结构，总的来说遍历的方法还是不变的，都是利用栈和Set来进行操作。
    //
    // 主要步骤：
    //
    // 准备一个栈结构和一个Set结构的集合，栈用来记录还有孩子没有被遍历到的节点，Set用来记录遍历的历史记录
    //         将首节点加入到栈和set中
    // 弹栈拿到首节点
    // 从首节点开始深度遍历，下面示例代码配合注解近进行理解。
    // public static void dfs(Node node) {
    //     if (node == null) {
    //         return;
    //     }
    //     Stack<Node> stack = new Stack<>();
    //     HashSet<Node> set = new HashSet<>();
    //     stack.add(node);
    //     set.add(node);
    //     System.out.println(node.value);
    //
    //     while (!stack.isEmpty()) {
    //         //弹栈获得一个节点
    //         Node cur = stack.pop();
    //         //查看这个节点的所有孩子
    //         for (Node next : cur.nexts) {
    //             //如果有孩子是之前没有遍历到的，说明这个节点没有深度遍历完
    //             if (!set.contains(next)) {
    //                 //此节点与其孩子加入栈与Set中
    //                 stack.push(cur);
    //                 stack.push(next);
    //                 set.add(next);
    //                 System.out.println(next.value);
    //                 break;
    //             }
    //         }
    //     }
    // }
    // 宽度优先搜索算法（又称广度优先搜索）是最简便的图的搜索算法之一，这一算法也是很多重要的图的算法的原型。对于多级数来说，就是先遍历该节点的所有孩子，然后在遍历孩子节点的所有孩子，先遍历一层再遍历下一次层。
    //
    // 主要思路就是利用队列来将下一层的所有节点记录下来，然后顺序遍历就可以了。
    //
    // public static void bfs(Node node) {
    //     if (node == null) {
    //         return;
    //     }
    //     Queue<Node> queue = new LinkedList<>();
    //     //用来注册已加入队列的节点——>防止重复添加节点
    //     HashSet<Node> set = new HashSet<>();
    //     queue.add(node);
    //     set.add(node);
    //     while (!queue.isEmpty()) {
    //         Node cur = queue.poll();
    //         System.out.println(cur.value);
    //         //将节点的所有下游节点加入到队列
    //         for (Node next : cur.nexts) {
    //             if (!set.contains(next)) {
    //                 set.add(next);
    //                 queue.add(next);
    //             }
    //         }
    //     }
    // }
    public static void main(String[] args) {
    }
}