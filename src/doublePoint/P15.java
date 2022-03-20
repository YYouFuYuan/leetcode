package doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i] > 0)
                return res;
            if(i>0 && nums[i] == nums[i-1])
                continue;
            int left = i + 1;
            int right = n - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if( sum == 0){
                    List<Integer> row = new ArrayList<>();
                    row.add(nums[i]);
                    row.add(nums[left]);
                    row.add(nums[right]);
                    res.add(row);
                    while (left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
                else if(sum > 0){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res;
    }
}
