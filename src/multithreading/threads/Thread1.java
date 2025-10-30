package multithreading.threads;

public class Thread1 extends Thread{
    public Thread1(String threadName)
    {
        super(threadName);
    }

    @Override
    public void run()
    {
        System.out.println(currentThread().getName());
    }
}
