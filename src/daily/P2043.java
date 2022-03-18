package daily;

public class P2043 {


    static class Bank {
        private long[] bankBalance;
        private int n;
        public Bank(long[] balance) {
            this.n = balance.length;
            this.bankBalance = new long[ n + 1];
            for(int i=1;i<n;i++){
                this.bankBalance[i] = balance[i-1];
            }
        }

        public boolean transfer(int account1, int account2, long money) {
            if(account1 <= 0 || account1 > n || account2 <= 0 || account2 > n || bankBalance[account1] < money)
                return false;
            bankBalance[account1] -= money;
            bankBalance[account2] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if(account <= 0 || account > n){
                return false;
            }
            bankBalance[account] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if(account <= 0 || account > n || bankBalance[account] < money){
                return false;
            }
            bankBalance[account] -= money;
            return true;
        }
    }
}
