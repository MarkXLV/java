package multithreading.executorframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorService {

    public static int factorial(int k) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int res = 1;
        for (int i = 1; i <= k; i++)
            res *= i;
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
//        for (int i = 1; i <= 5; i++) {
//            System.out.println(factorial(i));
//        }
//        System.out.println("Time Taken " +  (System.currentTimeMillis()-startTime));


//        ---------using multithreading ------------
//        Thread []threads=new Thread[5];
//        for (int i = 1; i <= 5; i++) {
//            int finalI = i;
//            threads[i-1]=new Thread(
//                    ()->{
//                        System.out.println(factorial(finalI));
//                    }
//            );
//            threads[i-1].start();
//        }
//
//        for(Thread thread:threads)l
//        {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println("Time Taken " +  (System.currentTimeMillis()-startTime));


//        ----------using executor framework --------------
//        ExecutorService executor= Executors.newFixedThreadPool(3);
//        for (int i = 1; i <= 5; i++) {
//            int finalI = i;
//            executor.submit(
//                    ()->{
//                        System.out.println(factorial(finalI));
//                    }
//            );
//        }
//        executor.shutdown();  // After shutdown, you cannot submit another task //
//        try {
//            while (!executor.awaitTermination(2000, TimeUnit.MICROSECONDS))
//            {
//                System.out.println("Waiting ..");
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("Time Taken " +  (System.currentTimeMillis()-startTime));

        /*
| Feature                         | `Runnable`                               | `Callable<V>`                                           |
| ------------------------------- | ---------------------------------------- | ------------------------------------------------------- |
| **Package**                     | `java.lang`                              | `java.util.concurrent`                                  |
| **Return Type**                 | Does **not return** a result (`void`)    | **Returns** a result of type `V`                        |
| **Can Throw Checked Exception** | **No** — cannot throw checked exceptions | **Yes** — can throw checked exceptions                  |
| **Method to Implement**         | `public void run()`                      | `public V call() throws Exception`                      |
| **Used With**                   | `Thread`, `Executor`                     | `ExecutorService`, especially with `Future`             |
| **When to Use**                 | When no result is needed                 | When a result is needed or an exception might be thrown |
*/
        java.util.concurrent.ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Runnable runnable=()-> System.out.println("Hello");
//        Callable <String> callable=()-> {
//            return "Hello";
//        };
//
//        Future <String>future = executorService.submit(callable);
//        System.out.println(future.get());
//        if(future.isDone()) {
//            System.out.println("Done ..");
//        }
//        executorService.shutdown();

//        -------------------------------------------------------------------------------------
//        invokeAll() -->takes list of callables and blocks the main thread
//        invokeAny() --> gives only output of first completed task

        java.util.concurrent.ExecutorService executorService1=Executors.newFixedThreadPool(2);
        Callable< Integer> callable1 = ()->{
            Thread.sleep( 1000);
            System.out.println("Task 1");
            return 1;
        };
        Callable<Integer> callable2= ()->{
            Thread.sleep( 1000);
            System.out.println("Task 2");
            return 2;
        };
        List<Callable<Integer>>list= Arrays.asList(callable1,callable2);
        List<Future<Integer>> futures = executorService1.invokeAll(list);

        for (Future<Integer> f:futures)
        {
            System.out.println(f.get());
        }

        System.out.println("Main thread is exiting ...");
    }

}
