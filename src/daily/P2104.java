package daily;

import java.util.*;

public class P2104 {
    public long subArrayRanges1(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for(int i=0;i<n;i++){
            int minValue = nums[i];
            int maxValue = nums[i];
            for(int j=i+1;j<n;j++){
                minValue = Math.min(minValue,nums[j]);
                maxValue = Math.max(maxValue,nums[j]);
                ans += (maxValue - minValue);
            }
        }
        return ans;
    }
    //转化题目
    //子数组范围=（max-min）
    //假设一共有m个子数组，则一共有m个max-min，再进行求和得结果
    //对每个数进行分析，每个数什么时候是最大值与最小值
    //通过单调栈可以轻松求得每个数左右两边比它大且比它小得数的索引
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[][] minRes = getCntMin(nums,n);
        int[] minResLeft = minRes[0];   //左边最近且比i小的数的索引
        int[] minResRight = minRes[1];  //右边最近且比i小的数的索引
        //System.out.println(Arrays.toString(minResLeft));
        //System.out.println(Arrays.toString(minResRight));
        int[][] maxRes = getCntMax(nums,n);
        int[] maxResLeft = maxRes[0];   //左边最近且比i大的数的索引
        int[] maxResRight = maxRes[1];  //右边最近且比i大的数的索引
        //System.out.println(Arrays.toString(maxResLeft));
        //System.out.println(Arrays.toString(maxResRight));

        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < n; i++) {
            //对于一个数，什么时候做最小值
            //l、、、、i....r
            //比它小        比它小
            //在左右两边比它小的中间的区间内，只要包含i就是最小值，最大值也同样
            //那么i做了几次最小值呢？
            //【l,i】[l,i+1]...[l,r]
            //【l+1,i】,...[l+1,r]
            //...
            //【i,i+1】....【i，r】
            //总数是(i-l) * (r-i)
            sumMax += (long) (maxResRight[i] - i) * (i - maxResLeft[i]) * nums[i];
            sumMin += (long) (minResRight[i] - i) * (i - minResLeft[i]) * nums[i];
        }
        return sumMax - sumMin;
    }

    //得到左右两边最近的小于的数
    public int[][] getCntMin(int[] nums,int n){
        int[] resRight = new int[n];
        int[] resLeft = new int[n];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        for(int i=0;i<n;i++){
            while (true){
                if(stack.isEmpty()){
                    LinkedList<Integer> r = new LinkedList<>();
                    r.add(i);
                    stack.add(r);
                    break;
                }
                LinkedList<Integer> peek = stack.peek();
                if(nums[i] >= nums[peek.getLast()]){
                    LinkedList<Integer> r = new LinkedList<>();
                    r.add(i);
                    stack.add(r);
                    break;
                }
                else {
                    LinkedList<Integer> pop = stack.pop();
                    for(Integer p : pop){
                        resRight[p] = i;
                        resLeft[p] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    }
                }
            }
        }
        //结算
        while (!stack.isEmpty()){
            LinkedList<Integer> pop = stack.pop();
            for(Integer p : pop){
                resRight[p] = n;
                resLeft[p] = stack.isEmpty() ? -1 : stack.peek().getLast();
            }
        }
        return new int[][]{resLeft,resRight};
    }

    //得到左右两边最近的大于的数
    public int[][] getCntMax(int[] nums,int n){
        int[] resRight = new int[n];
        int[] resLeft = new int[n];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        for(int i=0;i<n;i++){
            while (true){
                if(stack.isEmpty()){
                    LinkedList<Integer> r = new LinkedList<>();
                    r.add(i);
                    stack.add(r);
                    break;
                }
                LinkedList<Integer> peek = stack.peek();
                if(nums[i] <= nums[peek.getLast()]){
                    LinkedList<Integer> r = new LinkedList<>();
                    r.add(i);
                    stack.add(r);
                    break;
                }
                else {
                    LinkedList<Integer> pop = stack.pop();
                    for(Integer p : pop){
                        resRight[p] = i;
                        resLeft[p] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    }
                }
            }
        }
        //结算
        while (!stack.isEmpty()){
            LinkedList<Integer> pop = stack.pop();
            for(Integer p : pop){
                resRight[p] = n;
                resLeft[p] = stack.isEmpty() ? -1 : stack.peek().getLast();
            }
        }
        return new int[][]{resLeft,resRight};
    }
    public static void main(String[] args) {
        long res = new P2104().subArrayRanges(new int[]{4,-2,-3,4,1});
        System.out.println(res);
    }
}
