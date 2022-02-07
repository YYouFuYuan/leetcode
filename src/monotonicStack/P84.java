package monotonicStack;

import java.util.LinkedList;
import java.util.Stack;

public class P84 {

    //单调栈应用题：枚举每个高度
    //对每个高度h而言，要找到左右最近且小于h的位置
    //如果我们找到左边两边这样的位置
    //[L....h....R] 说明在[L,R]之间所有的高度都>=h
    //则以h的高度为矩形的面积：(R-L) * h
    //
    public int largestRectangleArea(int[] heights) {
        //单调栈里存储下标即可,构建一个栈底到栈顶，从小到大的栈
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] L = new int[n];   //左右边界
        int[] R = new int[n];
        for(int i = 0;i < n;i++){
            L[i] = -1;  //规定左极点为-1
            R[i] = n;   //规定右极点为n
        }

        //枚举每个高度
        for(int i=0;i<n;i++){
            while (true){
                //栈为空，直接入
                if(stack.isEmpty()){
                    stack.add(i);
                    break;
                }
                else {
                    //满足整个栈从小到大的性质，直接入栈
                    if(heights[stack.peek()] <= heights[i]){
                        stack.add(i);
                        break;
                    }
                    else {  //不满足性质,出栈
                        int p = stack.pop();
                        R[p] = i;               //记录右边界
                        if(!stack.isEmpty()){
                            L[p] = stack.peek();//记录左边界
                        }
                    }
                }
            }
        }
        //结算
        while (!stack.isEmpty()){
            int p = stack.pop();
            if(!stack.isEmpty()){
                L[p] = stack.peek();
            }
        }
        int res = 0;
        for(int i=0;i<n;i++){
            res = Math.max(res,((R[i]-L[i]) - 1) * heights[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        new P84().largestRectangleArea(new int[]{2,1,5,6,2,3});
    }
}
