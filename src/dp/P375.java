package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class P375 {
    /*
        暴力递归
    * */
    public boolean process(int curIndex, int target, Set<Integer> set, int preK){
        //base case
        if(curIndex == target)
            return true;
        //原地不跳就直接false
        if(preK <= 0)
            return false;
        //跳到水里是不行的
        if(!set.contains(curIndex)){
            return false;
        }
        return process(curIndex + (preK - 1),target,set,preK - 1) ||
                process(curIndex + (preK),target,set,preK) ||
                process(curIndex + (preK + 1),target,set,preK + 1);
    }

    public boolean canCross1(int[] stones) {
        if(stones[1] != 1 || stones[0] != 0)
            return false;
        int n = stones.length;
        Set<Integer> set = new HashSet<>();
        for(int s : stones){
            set.add(s);
        }
        return process(1,stones[n-1],set,1);
    }


    /*
        记忆化搜索,两个变量curIndex,preK
    * */
    static class State{
        public int curIndex;
        public int preK;
        public State(int curIndex,int preK){
            this.curIndex = curIndex;
            this.preK = preK;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return curIndex == state.curIndex && preK == state.preK;
        }

        @Override
        public int hashCode() {
            return Objects.hash(curIndex, preK);
        }
    }

    public boolean getState(int curIndex,int target,Set<Integer> set,int K,HashMap<State,Boolean> map){
        boolean res = true;
        //如果已经跳过的，直接拿
        if(map.containsKey(new State(curIndex,K))){
            return map.get(new State(curIndex,K));
        }
        else {
            //没跳过的process一下
            res = processMemory(curIndex,target,set,K,map);
            map.put(new State(curIndex,K),res);
            return res;
        }
    }

    public boolean processMemory(int curIndex, int target, Set<Integer> set, int preK,HashMap<State,Boolean> map){
        //base case
        if(curIndex == target)
            return true;
        //原地不跳就直接false
        if(preK <= 0)
            return false;
        //跳到水里是不行的
        if(!set.contains(curIndex)){
            return false;
        }
        //看接下来的3个状态
        return getState(curIndex + (preK - 1),target,set,preK - 1,map) ||
                getState(curIndex + (preK),target,set,preK,map) ||
                getState(curIndex + (preK + 1),target,set,preK + 1,map);
    }

    public boolean canCross(int[] stones) {
        if(stones[1] != 1 || stones[0] != 0)
            return false;
        int n = stones.length;
        Set<Integer> set = new HashSet<>();
        for(int s : stones){
            set.add(s);
        }
        HashMap<State,Boolean> map = new HashMap();
        return processMemory(1,stones[n-1],set,1,map);
    }
}
