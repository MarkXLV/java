package multithreading.latch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CountDownLatch is a synchronization aid that lets one or more threads wait until other threads complete tasks.

You initialize it with a count, and each countDown() call reduces the count.

Threads calling await() block until the count reaches zero.
 */

public class CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        int dependents=3;
        ExecutorService executorService=Executors.newFixedThreadPool(dependents);
        java.util.concurrent.CountDownLatch latch=new java.util.concurrent.CountDownLatch(dependents);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        latch.await();
        executorService.shutdown();
        System.out.println("Main is exiting ... ");
    }
}

class DependentService implements Callable<String> {

    java.util.concurrent.CountDownLatch countDownLatch;
    public DependentService(java.util.concurrent.CountDownLatch latch) {
        this.countDownLatch=latch;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+ " Started ...");
        countDownLatch.countDown();
        return "ok";
    }
}