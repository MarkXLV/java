package multithreading.problems;


import java.util.concurrent.locks.ReentrantReadWriteLock;

// It is type of extrinsic (manual) locking
public class ReadWriteLock {
    private int count;
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock(true);
    private ReentrantReadWriteLock.ReadLock readLock= reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock= reentrantReadWriteLock.writeLock();

    public ReadWriteLock(int start)
    {
        count=0;
    }

    public synchronized void write(){
        writeLock.lock();
        try{
            count++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public synchronized int read()
    {
        readLock.lock();
        try{
            return count;
        } finally {
            readLock.unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock=new ReadWriteLock(0);

        Thread t1=new Thread(
                ()->{
                    for (int i = 0; i < 10; i++) {
                        readWriteLock.write();
                        System.out.println("--Write--");
                    }
                }
        );

        Thread t2=new Thread(
                ()->{
                    for (int i = 0; i < 10; i++) {
                        System.out.println("--Read--" + readWriteLock.read());
                    }
                }
        );

        t1.start();t2.start();



    }

}
