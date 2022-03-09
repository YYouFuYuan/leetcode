package daily;

import java.util.Arrays;

public class P798 {

    /**
     * 暴力求解
     * @param nums
     * @return
     */
    public int bestRotationRe(int[] nums) {
        int size = nums.length;
        int ansScore = 0;
        int ansIndex = -1;
        for(int k=0;k<size;k++){
            int curScore = 0;
            for(int i=0;i<size;i++){
                int index = (i-k) >=0 ? i-k : size + (i-k);
                if(nums[i] <= index ){
                    curScore += 1;
                }
            }
            if(ansScore < curScore){
                ansScore = curScore;
                ansIndex = k;
            }
        }
        return ansIndex;
    }



    public static void main(String[] args) {
        int res = new P798().bestRotation(new int[]{1,3,0,2,4});
        System.out.println(res);
    }

    /**
     * 差分数组 + 上下界
     * @param nums
     * @return
     */
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[][] sum = new int[n][2];
        //先求每个数能计分的上下界
        for(int i=0;i<n;i++){
            sum[i][0] = (i + 1)%n;
            sum[i][1] = (i + n - nums[i]) %n;
            //System.out.println(i + " " + Arrays.toString(sum[i]));
        }
        //差分数组 变更区间
        int[] diff = new int[n+1];
        for(int i=0;i<n;i++){
            if(sum[i][1] >= sum[i][0]){
                diff[sum[i][0]]++;
                diff[sum[i][1] + 1]--;
            }
            else {
                diff[0]++;
                diff[sum[i][1]+1]--;
                diff[sum[i][0]]++;
                diff[n]--;
            }
        }
        //System.out.println(Arrays.toString(diff));
        int score = diff[0];
        int ans = 0;
        for(int i=1;i<n;i++){
            diff[i] = diff[i-1] + diff[i];
            if(score < diff[i]){
                score = diff[i];
                ans = i;
            }
        }
        //System.out.println(Arrays.toString(diff));

        return ans;
    }

}
