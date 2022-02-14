package prefixSum;

public class P1413 {
    //nlogn不够快
    public int minStartValueNlogN(int[] nums) {
        int right = 0;
        for(int num : nums){
            right += num < 0 ? Math.abs(num) : 0;
        }
        int left = 1;
        while (left < right){
            int middle = left + ((right - left) >> 1);
            int startValue = middle;
            boolean flag = true;
            for(int num : nums){
                startValue += num;
                if(startValue <= 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                right = middle;
            }
            else {
                left = middle + 1;
            }
        }
        return left;
    }

    public int minStartValue(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int sum = 0;
        for(int num : nums){
            sum += num;
            minValue = Math.min(minValue,sum);
        }
        return minValue <= 0 ? Math.abs(minValue) + 1 : minValue;
    }

    public static void main(String[] args) {
        new P1413().minStartValue(new int[]{-4,-2,5});
    }
}
