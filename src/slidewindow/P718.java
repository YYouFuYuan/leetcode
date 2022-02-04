package slidewindow;

public class P718 {
    public int ans = 0;
    public int process(int[] nums1,int[] nums2,int begin1,int begin2,int len1,int len2){
        if(begin1 >= len1 || begin2 >= len2){
            return 0;
        }
        int cur = 0;
        if(nums1[begin1] == nums2[begin2]){
            cur = Math.max(process(nums1,nums2,begin1 + 1, begin2,len1,len2), process(nums1,nums2,begin1, begin2 + 1 ,len1,len2)) + 1;
            ans = Math.max(cur,ans);
        }
        return cur;

    }

    public int findLength(int[] nums1, int[] nums2) {
        process(nums1,nums2,0,0,nums1.length,nums2.length);
        return ans;
    }
}
