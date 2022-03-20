package thread;

import java.util.function.IntConsumer;

public class P1116 {
    static class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        private Object lock = new Object();
        private boolean zeroDone = false;
        private boolean evenDene = true;
        private boolean oddDone = true;

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for(int i=1;i<=this.n;i++){

                synchronized (lock){
                    //有一个是true就行
                    while (zeroDone == true && (oddDone == false || evenDene == false)){
                        lock.wait();
                    }
                    printNumber.accept(0);
                    zeroDone = true;
                    if(i % 2 == 0){
                        evenDene = false;
                    }
                    else oddDone = false;
                    lock.notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i=2;i<=this.n;i+=2){
                synchronized (lock){

                    while (zeroDone == false || oddDone == false){
                        lock.wait();
                    }
                    printNumber.accept(i);
                    this.evenDene = true;
                    this.zeroDone = false;
                    lock.notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for(int i=1;i<=this.n;i+=2){
                //必须等偶数打印了，且0也打印了
                synchronized (lock){
                    while (zeroDone == false || evenDene == false){
                        lock.wait();
                    }
                    printNumber.accept(i);
                    this.oddDone = true;
                    this.zeroDone = false;
                    lock.notifyAll();
                }
            }
        }
    }
}
