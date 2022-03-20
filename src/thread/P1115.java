package thread;

public class P1115 {
    static class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        private Object lock = new Object();
        private boolean fooDone = false;

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                synchronized (lock){
                    while (fooDone == true){
                        lock.wait();
                    }
                    printFoo.run();
                    fooDone = true;
                    lock.notifyAll();
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock){
                    while (fooDone == false){
                        lock.wait();
                    }
                    printBar.run();
                    fooDone = false;
                    lock.notifyAll();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.

            }
        }
    }
}
