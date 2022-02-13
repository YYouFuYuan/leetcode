package template;

import java.security.PublicKey;
import java.util.*;

public class Template211 {

    /*
                    1.基础数学
     */

    /*
            1.1异或结论  0^N = N
                       N^N = 0
     */
    /**
     * 原地交换
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums,int i,int j){
        if(i != j){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }

    /*
            1.2求最小公倍数与最大公约数(辗转相除法)
     */

    /**
     * 辗转相除法求最大公约数，递归版
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a % b);
    }

    /**
     * 通过最大公约数求最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a,int b){
        return a * b / gcd(a,b);
    }

    /*
                    2.二分
     */

    /*
                    2.1标准二分
     */
    /**
     * 标准二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch1(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return -1;
    }

    /*
                    2.2查找左边界1
     */
    /**
     * 查找左边界，如查找 >= target的第一个元素
     * 适用于2两种情况，1）有序数组，且有重复数据
     *               2）部分有序，且无重复数据
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch2(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] >= target)
                right = mid;
            else left = mid + 1;
        }
        return nums[left] >= target ? left : -1;
    }

    /*
                    2.3查找左边界2
     */
    /**
     * 查找左边界，如查找 >= target的第一个元素
     * 适用于情况， 部分有序，且有重复数据
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch3(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] > target)
                right = mid;
            else if(nums[mid] < target)
                left = mid + 1;
            else right--;       //等于的时候独立-1
        }
        return nums[left] >= target ? left : -1;
    }

    /*
                    2.4查找右边界  修改查找左边界模板即可得
     */


    /*
                    3.归并排序
     */
    public static int mergeSort(int[] nums,int left,int right){
        if(left >= right)
            return 0;
        int mid = left + ( (right - left) >> 1 );
        int r1 = mergeSort(nums,left,mid);
        int r2 = mergeSort(nums,mid+1,right);
        //do something 统计工作，从两个本身有序的子数组中统计出符合题意的要求
        //eg: 统计逆序对
        int ret = r1 + r2;
        int i = left;
        int j = mid + 1;
        while (i <= mid){
            while (j <= right && nums[i] > nums[j]){
                j++;
            }
            ret += (j - mid - 1);
            i++;
        }
        merge(nums,left,mid,right);
        return ret;
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0;
        int L = left;
        int R = mid + 1;
        while (L <= mid && R <= right){
            if(nums[L] <= nums[R]){
                temp[index++] = nums[L++];
            }
            else temp[index++] = nums[R++];
        }

        while (L <= mid){
            temp[index++] = nums[L++];
        }
        while (R <= right){
            temp[index++] = nums[R++];
        }

        for(int i=0;i<right - left + 1;i++){
            nums[left + i] = temp[i];
        }
    }

    /*
                    4.快速排序
     */
    public void quickSort(int[] nums,int left,int rihgt){
        if(left >= rihgt)
            return;
        swap(nums,left, (int) (left + Math.random() * (rihgt - left + 1)));
        int[] privotIndex = partition(nums,left,rihgt);
        quickSort(nums,left,privotIndex[0]-1);
        quickSort(nums,privotIndex[1]+1,rihgt);
    }


    private int[] partition(int[] nums, int left, int rihgt) {
        int L = left - 1;
        int R = rihgt + 1;
        int i = left;
        int target = nums[i];
        while (i < R){
            if(nums[i] == target)
                i++;
            else if(nums[i] > target){
                swap(nums,i,R - 1);
                R--;
            }
            else {
                swap(nums,i,L + 1);
                i++;
                L++;
            }
        }
        return new int[]{L+1,R-1};
    }

    /*
                    5.堆排序 (大根堆)
     */
    public static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr,int heapSize,int index){
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize){
            int largest = (leftChild + 1) < heapSize && arr[leftChild + 1] > arr[leftChild] ? leftChild + 1 : leftChild;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr,index,largest);
            index = largest;
            leftChild = index * 2 + 1;
        }
    }

    public static void heapSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int size = arr.length;
        while (size > 0){
            swap(arr,0,--size);
            heapify(arr,size,0);
        }
    }

    /*
                    6.二叉树
     */
    static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    /*
                    6.1前序遍历  中左右
     */
    public List<Integer> preOrderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }
        return list;
    }

    public void preOrderRe(TreeNode root){
        if(root == null)
            return;
        System.out.println(root.val);
        if(root.left != null)
            preOrderRe(root.left);
        if(root.right != null)
            preOrderTraversal(root.right);
    }

    /*
                    6.2后序遍历 左右中
     */
    public List<Integer> posterOrderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        while (stack1.isEmpty()){
            root = stack1.pop();
            stack2.add(root.val);   //没有stack2的话，顺序是中右左
            if(root.left != null){
                stack1.push(root.left);
            }
            if(root.right != null){
                stack1.push(root.right);
            }
        }
        while (stack2.isEmpty()){
            list.add(stack2.pop());
        }
        return list;
    }

    public void posterOrderRe(TreeNode root){
        if(root == null)
            return;
        posterOrderTraversal(root.left);
        posterOrderTraversal(root.right);
        System.out.println(root.val);

    }

    /*
                        6.3 中序遍历 左中又
     */
    public List<Integer> inorderTracersal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    public void inorderRe(TreeNode root){
        if(root == null)
            return;
        posterOrderTraversal(root.left);
        System.out.println(root.val);
        posterOrderTraversal(root.right);
    }

    /*
                        6.4 层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> row = new ArrayList<>();
            for(int i=0;i<size;i++){
                root = queue.poll();
                row.add(root.val);
                if(root.left != null){
                    queue.add(root.left);
                }
                if(root.right != null){
                    queue.add(root.right);
                }
            }
            result.add(row);
        }
        return result;
    }

    /*
                        6.5 树形dp 二叉树套路模板
                    以检查一颗二叉树是否是平衡二叉树为例子
     */
    static class ReturnResult{  //左右子树要返回的并集
        public boolean isBalanced;
        public int height;          //当前子树的高度和是否平衡的标志
        public ReturnResult(boolean isBalanced,int height){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    /**
     *  二叉树递归套路模板
     *  1。首先分析以当前节点，需要左右子树各提供什么信息，去并集做返回类型
     *  函数开始
     *      2. 递归边界条件
     *      3. 调用左右子树获得信息
     *      4. 根据信息计算当前节点并返回
     *      @param root
     *      @return
     * */
    public boolean isBalanced(TreeNode root){
        return treeProcess(root).isBalanced;
    }

    private ReturnResult treeProcess(TreeNode root) {
        //1.边界条件
        if(root == null){
            return new ReturnResult(true,0);
        }
        //2.调用左右子树并返回信息
        ReturnResult leftResult = treeProcess(root.left);
        ReturnResult rightResult = treeProcess(root.right);

        //3.合并信息并生成当前该子树的信息
        boolean curBalanced = false;
        if(leftResult.isBalanced && rightResult.isBalanced && Math.abs(leftResult.height - rightResult.height) < 2){
            curBalanced = true;
        }
        return new ReturnResult(curBalanced,Math.max(leftResult.height,rightResult.height) + 1);
    }

    /*
                        7.并查集
     */
    static class UFindSet{
        public Map<Integer,Integer> fatherMap;
        public Map<Integer,Integer> rankMap;
        public UFindSet(int n){
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            for(int i=1;i<=n;i++){
                fatherMap.put(i,i);
                rankMap.put(i,1);
            }
        }

        public int findFather(int element){
            Stack<Integer> stack = new Stack<>();
            while (element != fatherMap.get(element)){
                stack.push(element);
                element = fatherMap.get(element);
            }
            while (!stack.isEmpty()){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }

        public void union(int a,int b){
            int aFather = fatherMap.get(a);
            int bFather = fatherMap.get(b);
            if(aFather != bFather){
                int aRank = rankMap.get(aFather);
                int bRank = rankMap.get(bFather);
                if( aRank <= bRank){
                    fatherMap.put(aFather,bFather);
                    rankMap.put(bFather,aRank + bRank);
                }
                else {
                    fatherMap.put(bFather,aFather);
                    rankMap.put(aFather,aRank + bRank);
                }
            }
        }
    }
    /*
                        8. 图
     */
    static class Graph{
        //节点
        static class Node{
            public int value;
            public int in;
            public int out;
            public List<Node> nexts;
            public List<Edge> edges;
            public Node(int value){
                this.value = value;
                in = 0;
                out = 0;
                nexts = new ArrayList<>();
                edges = new ArrayList<>();
            }
        }
        //边
        static class Edge{
            public int weight;
            public Node from;
            public Node to;
            public Edge(int weight,Node from,Node to){
                this.from = from;
                this.to = to;
                this.weight = weight;
            }
        }

        public HashMap<Integer,Node> nodes;
        public HashSet<Edge> edges;

        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }

        /*
                       8.1 结构体的bfs和dfs
        */
        public void bfs(Node node){
            HashSet<Node> set = new HashSet<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);
            set.add(node);
            while (!queue.isEmpty()){
                Node cur = queue.poll();
                System.out.println(cur.value);
                for(Node next : cur.nexts){
                    if(!set.contains(next)){
                        set.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        public void dfs(Node node){
            HashSet<Node> set = new HashSet<>();
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            set.add(node);
            while (!stack.isEmpty()){
                Node cur = stack.pop();
                System.out.println(cur.value);
                for(Node next : cur.nexts){
                    if(!set.contains(next)){
                        stack.push(cur);
                        stack.push(next);
                        set.add(next);
                        break;
                    }
                }
            }
        }

        /*
                        8.2 拓扑排序
         */
        public List<Node> sortedTopology(Graph graph){
            Queue<Node> queue = new LinkedList<>();
            for(Node node : graph.nodes.values()){
                if(node.in == 0)
                    queue.offer(node);
            }
            List<Node> res = new ArrayList<>();
            while (!queue.isEmpty()){
                Node cur = queue.poll();
                for(Node next : cur.nexts){
                    if(--next.in == 0){
                        queue.offer(next);
                    }
                }
            }
            return res;
        }

        /*
                        8.3 kruscal MST
         */
        public Set<Edge> kruscalMST(Graph graph){
            UFindSet uSet = new UFindSet(graph.nodes.size());
            PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.weight - o2.weight;
                }
            });
            queue.addAll(graph.edges);
            Set<Edge> res = new HashSet<>();
            while (!queue.isEmpty()){
                Edge edge = queue.poll();
                if(uSet.findFather(edge.from.value) != uSet.findFather(edge.to.value)){
                    uSet.union(edge.from.value,edge.to.value);
                    res.add(edge);
                }
            }
            return res;
        }

        /*
                        8.4 prim
         */
        public Set<Edge> primMST(Graph graph){
            PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.weight - o2.weight;
                }
            });
            Set<Node> set = new HashSet<>();
            Set<Edge> result = new HashSet<>();
            for(Node node : graph.nodes.values()){
                if(!set.contains(node)){
                    set.add(node);
                    queue.addAll(node.edges);
                    while (!queue.isEmpty()){
                        Edge edge = queue.poll();
                        Node toNode = edge.to;
                        if(!set.contains(toNode)){
                            set.add(toNode);
                            result.add(edge);
                            queue.addAll(toNode.edges);
                        }
                    }
                }
            }
            return result;
        }

        /*
                        8.5 dijkstra
         */
        public Map<Node,Integer> dijkstra(Node head){
            Map<Node,Integer> distanceMap = new HashMap<>();
            Set<Node> selectNode = new HashSet<>();
            distanceMap.put(head,0);

            Node minNode = getMinDistanceUnselectNode(distanceMap,selectNode);
            while (minNode != null){
                selectNode.add(minNode);
                for(Edge edge : minNode.edges){
                    Node to = edge.to;
                    if(!distanceMap.containsKey(to)){
                        distanceMap.put(to,distanceMap.get(minNode) + edge.weight);
                    }
                    else {
                        distanceMap.put(to,Math.min(distanceMap.get(to),distanceMap.get(minNode) + edge.weight));
                    }
                }
                minNode = getMinDistanceUnselectNode(distanceMap,selectNode);
            }
            return distanceMap;
        }

        private Node getMinDistanceUnselectNode(Map<Node, Integer> distanceMap, Set<Node> selectNode) {
            int distance = Integer.MAX_VALUE;
            Node result = null;
            for(Map.Entry<Node,Integer> entry :distanceMap.entrySet()){
                if(!selectNode.contains(entry.getKey()) && entry.getValue() < distance){
                    distance = entry.getValue();
                    result = entry.getKey();
                }
            }
            return result;
        }

        /*
                        8.6 多源最短路径
         */
    }



    /*
                        8.X 二维矩阵的深搜和广搜
     */
    public int[][] dirs = { {1,0},{-1,0},{0,1},{0,-1}};
    public void bfs(int[][] matrix,int startX ,int startY,int targetX,int targetY){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] flag = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        flag[startX][startY] = 1;
        queue.add(new int[]{startX,startY});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == targetX && cur[1] == targetY) //do something
                return;
            for(int d=0;d<4;d++){
                int nextX = cur[0] + dirs[d][0];
                int nextY = cur[1] + dirs[d][1];
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || flag[nextX][nextY] == 1){
                    continue;
                }
                flag[nextX][nextY] = 1;
                queue.add(new int[]{nextX,nextY});
            }
        }
    }

    public void dfs(int[][] matrix,int curX,int curY,int targetX,int targetY,int[][] flag){
        if(curX < 0 || curX >= matrix.length || curY < 0 || curY >= matrix[0].length)
            return;
        //do something
        if(curX == targetX && curY == targetY)
            return;
        for(int i=0;i<4;i++){
            int nextX = curX + dirs[i][0];
            int nextY = curY + dirs[i][1];
            if(flag[nextX][nextY] == 0){
                flag[nextX][nextY] = 1;
                dfs(matrix,nextX,nextY,targetX,targetY,flag);
                flag[nextX][nextY] = 0;
            }
        }
    }

    /*
                        9. 前缀树
     */

    static class Trie{
        //节点类
        class TrieNode{
            public int path;
            public int end;
            public HashMap<Character,TrieNode> nexts;
            public TrieNode(){
                this.end = 0;
                this.path = 0;
                this.nexts = new HashMap<>();
            }
        }

        private TrieNode root;
        public Trie(){
            this.root = new TrieNode();
        }

        public int size(){
            return this.root.path;
        }

        public void insert(String word){
            if(word == null)
                return;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.path++;
            for(int i=0;i<chars.length;i++){
                if(!node.nexts.containsKey(chars[i])){
                    node.nexts.put(chars[i],new TrieNode());
                }
                node = node.nexts.get(chars[i]);
                node.path++;
            }
            node.end++;
        }

        public int search(String word){
            if(word == null)
                return 0;
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for(int i=0;i<chars.length;i++){
                if(!node.nexts.containsKey(chars[i])){
                    return 0;
                }
                node = node.nexts.get(chars[i]);
            }
            return node.end;
        }

        public void delete(String word){
            if(this.search(word) != 0){
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.path--;
                for(int i=0;i<chars.length;i++){
                    if(--node.nexts.get(chars[i]).path == 0){
                        node.nexts.remove(chars[i]);
                        return;
                    }
                    node = node.nexts.get(chars[i]);
                }
                node.end--;
            }
        }

        public int prefixNumber(String pre){
            if(pre == null)
                return 0;
            char[] chars = pre.toCharArray();
            TrieNode node = root;
            for(int i=0;i<chars.length;i++){
                if(!node.nexts.containsKey(chars[i]))
                    return 0;
                node = node.nexts.get(chars[i]);
            }
            return node.path;
        }
    }


    /*
                        10. 回溯
     */
    /*
                        10.1子集树
     */
    public static void processBT(char[] str,int i,List<Character> res){
        if(i == str.length){
            //do something , count or print
            return;
        }
        res.add(str[i]);
        processBT(str,i+1,res);
        res.remove(res.size()-1);

        processBT(str,i+1,res);
    }
    public static void strChildCollection(){
        processBT(new char[]{'a','b','c'},0,new ArrayList<>());
    }

    /*
                        10.2 排列树
     */
    public static void strAllPermutetionsProcess(char[] str,int i,List<String> res,boolean[] visited){
        if(i == str.length){
            res.add(String.valueOf(str));
            return;
        }
        for(int j = 0;j < str.length;j++){
            if( !visited[str[j] - 'a']){
                visited[str[j] - 'a'] = true;
                charSwap(str,i,j);
                strAllPermutetionsProcess(str,i+1,res,visited);
                charSwap(str,i,j);
                visited[str[j] - 'a'] = false;
            }
        }
    }

    private static void charSwap(char[] str, int i, int j) {
        if(i != j){
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    /*
                        11. dp
     */

    /*
                        11.1 线性dp
     */

    /*
                        11.2 背包dp
     */

    /*
                        12. KMP
     */

    /*
                        13.滑动窗口
     */

    /*
                         14.单调栈
     */

}
