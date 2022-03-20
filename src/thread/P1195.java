package thread;

import java.util.function.IntConsumer;

public class P1195 {

    static class FizzBuzz {
        private int n;

        public FizzBuzz(int n) {
            this.n = n;
        }

        private Object lock = new Object();
        private int i = 1;

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
                while (i <= n){
                    synchronized (lock){
                        if (i %3 == 0 && i % 5 !=0){
                            printFizz.run();
                            i++;
                            lock.notifyAll();
                        }
                        else lock.wait();
                    }
                }

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (i <= n){
                synchronized (lock){
                    if (i %5 == 0 && i % 3 !=0){
                        printBuzz.run();
                        i++;
                        lock.notifyAll();
                    }
                    else lock.wait();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (i <= n){
                synchronized (lock){
                    if (i %5 == 0 && i % 3 ==0){
                        printFizzBuzz.run();
                        i++;
                        lock.notifyAll();
                    }
                    else lock.wait();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (i <= n){
                synchronized (lock){
                    if (i %5 != 0 && i % 3 !=0){
                        printNumber.accept(i);
                        i++;
                        lock.notifyAll();
                    }
                    else lock.wait();
                }
            }
        }
    }
}
