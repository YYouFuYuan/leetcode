package binarysearch;

public class P278 {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            //因为要寻找的是左边界，即左边第一个出现的
            if(isBadVersion(mid) == false){ //当前false，不可能是答案。移动
                left = mid + 1;
            }
            else right = mid;   //mid有可能是答案，把右边界设置在当前位置
        }
        return isBadVersion(left) == true ? left : left + 1; //最左边看看是否满足条件
    }

    private boolean isBadVersion(int mid) {
        return true;
    }
}
