package dp;

public class P91 {
    //--------------------------------------------暴力递归，超时---------------------------------------
    public int res = 0;
    public void process(char[] str,int index, int n){
        if(n == index){
            this.res++;
            return;
        }
        if(str[index] == '0')
            return;
        if(str[index] >= '1' && str[index] <= '2'){
            if(index + 1 < n){
                if(str[index] == '2'){
                    if(str[index+1] <= '6'){
                        process(str,index+2,n);
                        process(str,index+1,n);
                    }
                    else process(str,index+1,n);
                }
                else{
                    process(str,index+2,n);
                    process(str,index+1,n);
                }
            }
            else {
                process(str,index+1,n);
            }
        }
        else {
            process(str,index+1,n);
        }

    }
    public int numDecodings(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        process(str,0,n);
        return this.res;
    }
}
