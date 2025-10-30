package multithreading.problems;

import multithreading.threads.Thread1;

public class Counter {
    private int count;

    public Counter(int start)
    {
        count=start;
    }

    public synchronized void increment()
    {
        count++;
    }

    public static void main(String[] args) {
        Counter counter=new Counter(0);
        Thread t1=new Thread(
                ()->{
                    for (int i = 0; i < 10000; i++) {
                        counter.increment();
                    }
                }
        );


        Thread t2=new Thread(
                ()->{
                    for (int i = 0; i < 10000; i++) {
                        counter.increment();
                    }
                }
        );

        t1.start();t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.count);
    }
}
