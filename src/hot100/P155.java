package hot100;

import java.util.Stack;

public class P155 {
    static class MinStack {
        public Stack<Integer> stack;
        public Stack<Integer> minStack;
        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
            this.minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            this.stack.push(val);
            this.minStack.push(Math.min(val,this.minStack.peek()));
        }

        public void pop() {
            this.stack.pop();
            this.minStack.pop();
        }

        public int top() {
            return this.stack.peek();
        }

        public int getMin() {
            return this.minStack.peek();
        }
    }
}
