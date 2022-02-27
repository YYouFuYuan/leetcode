package daily;

public class P553 {
    public String optimalDivision(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<nums.length;i++){
            sb.append(nums[i]);
            if(i<nums.length-1){
                sb.append("/");
            }
        }
        if(nums.length>2){
            sb.insert(sb.indexOf("/")+1,"(");
            sb.append(")");
        }
        return sb.toString();
    }
}
