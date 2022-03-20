package thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class P1114 {

    static class Foo1 {

        public Foo1() {

        }

        private Object lock = new Object();
        private boolean firstJobDone = false;
        private boolean secondJobDone = false;

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock){
                printFirst.run();
                firstJobDone = true;
                lock.notifyAll();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.

        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            synchronized (lock){
                while (firstJobDone == false)
                    lock.wait();
                printSecond.run();
                secondJobDone = true;
                lock.notifyAll();
            }

        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            synchronized (lock){
                while(secondJobDone == false)
                    lock.wait();
                printThird.run();
            }

        }
    }


    static class Foo {

        public Foo() {
            firstJob.add(0);
        }


        private BlockingDeque<Integer> firstJob = new LinkedBlockingDeque<>();
        private BlockingDeque<Integer> secondJob = new LinkedBlockingDeque<>();
        private BlockingDeque<Integer> thirdJob = new LinkedBlockingDeque<>();
        public void first(Runnable printFirst) throws InterruptedException {

            firstJob.take();
            printFirst.run();
            secondJob.add(0);
            // printFirst.run() outputs "first". Do not change or remove this line.

        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            secondJob.take();
            printSecond.run();
            thirdJob.add(0);


        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            thirdJob.take();
            printThird.run();


        }
    }
}
