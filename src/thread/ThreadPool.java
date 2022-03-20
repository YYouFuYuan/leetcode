package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPool {

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        String s3 = new String("xyz");
        String s4 = new String("xyz");
        System.out.println(s3==s4);

        String s5 = new String("a");
        String s6 = "a";
        System.out.println(s5 == s6);
    }
}
