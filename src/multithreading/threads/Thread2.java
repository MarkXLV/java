package multithreading.threads;

import static java.lang.Thread.currentThread;

public class Thread2 implements Runnable {
    @Override
    public void run()
    {
        System.out.println(currentThread().getName());
    }
}
