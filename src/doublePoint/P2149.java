package doublePoint;

import java.util.Arrays;

public class P2149 {


    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int pos = 0;
        int neg = 0;
        int index = 0;
        while (index < n){
            if(index % 2 == 0){
                while (nums[pos] < 0){
                    pos++;
                }
                res[index] = nums[pos];
                pos++;
            }
            else {
                while (nums[neg] > 0){
                    neg++;
                }
                res[index] = nums[neg];
                neg++;
            }
            index++;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] num = new P2149().rearrangeArray(new int[]{19,-26,-37,-10,-9,15,14,31});
        System.out.println(Arrays.toString(num));
    }
}
