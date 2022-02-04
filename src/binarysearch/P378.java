package binarysearch;

public class P378 {

    public boolean check(int[][] matrix,int k,int num){
        int count = 0;
        int n = matrix.length;
        int i = 0;
        int j = n - 1;
        while (i < n && j >=0){
            if(matrix[i][j] <= num){
                i++;
                if(i == n){
                    count += i;
                    i--;
                    j--;
                }
            }
            else {
                count += i;
                j--;
            }
        }
        return count >= k;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while (left < right){
            int middle = left + ((right - left) >> 1);
            if(check(matrix,k,middle)){
                right = middle;
            }
            else left = middle + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new P378().kthSmallest(new int[][]{{1,5,9},{10,11,13},{12,13,15}},8));
    }
}
