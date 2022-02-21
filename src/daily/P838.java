package daily;

import java.util.Arrays;

public class P838 {
    public String pushDominoes(String dominoes) {

        char[] str = dominoes.toCharArray();
        int n = dominoes.length();
        int[] left = new int[n];    //当前的第i个骨牌受到来自左边最近的力
        int[] right = new int[n];   //当前的第i个骨牌受到来自右边最近的力
        int[] lleft = new int[n];   //当前的第i个骨牌是否受到左边的力 相互抵消的情况
        int[] rright = new int[n];  //当前的第i个骨牌是否受到右边的力
        Arrays.fill(left,-1);   //默认不受力 -1表示不受到左边某个位置的力
        Arrays.fill(right,-1);
        Arrays.fill(lleft,0);
        Arrays.fill(rright,0);

        for(int i=1;i<n;i++){
            //记录i位置左边的力的index（最接近i位置的）
            if(str[i-1] == 'R'){
                left[i] = i-1;
            }
            else {
                if(left[i-1] != -1)
                    left[i] = left[i-1];
            }
            //统计当前i位置是否受到力
            lleft[i] = Math.max(lleft[i-1],0);
            //有来自左边的力，因为题目要求对正在倒下的牌不施加额外的力，所以左边有力为1就好，如果施加力 应该++
            if(str[i-1] == 'R'){
                lleft[i] = 1;
            }
            else if(str[i-1] =='L'){    //如果有出现右边的力，那么当前状态受到来自左边的力肯定为0，原来如果有肯定被抵消
                lleft[i] = 0 ;
            }

        }
        for(int i=n-2;i>=0;i--){
            if(str[i+1] == 'L'){
                right[i] = i+1;
            }
            else {
                if(right[i+1] != -1)
                    right[i] = right[i+1];
            }

            rright[i] = Math.max(rright[i+1],0);
            if(str[i+1] == 'L'){
                rright[i] = 1;
            }
            else if(str[i+1] =='R'){
                rright[i] = 0;
            }
        }

        char[] res = new char[n];
        for(int i=0;i<n;i++){

            if(str[i] == '.'){
                //如果当前不受力，保持原样
                if((lleft[i] ==0 && rright[i] == 0) ){
                    res[i] = str[i];
                }
                else if(lleft[i] != 0 && rright[i] != 0){
                    //受到来自双方的力，取最近
                    int l = Math.abs(left[i] - i);
                    int r = Math.abs(right[i] - i);
                    if(l == r){ //平衡
                        res[i] = str[i];
                    }
                    else {
                        res[i] = l < r ? 'R' : 'L';
                    }
                }
                else {
                    //只受到来自一方的力
                    res[i] = lleft[i] == 0 ? 'L' : 'R';
                }
            }
            else {  //力的出发点直接是该状态
                res[i] = str[i];
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        String res = new P838().pushDominoes(".L.R...LR..L..");
        System.out.println(res);
    }
}
