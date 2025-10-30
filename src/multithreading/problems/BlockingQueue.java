package multithreading.problems;

import multithreading.threads.Thread1;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer>q;
    private int capacity;

    public BlockingQueue(int cap)
    {
        this.capacity=cap;
        q=new LinkedList<>();
    }

    public synchronized boolean producer(int val)
    {
        synchronized (q)
        {
            while (q.size()==capacity)
            {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            q.add(val);
            q.notifyAll();
            return true;
        }
    }

    public int consume()
    {
         synchronized (q)
         {
             if(q.isEmpty())
             {
                 try {
                     q.wait();
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }

             int k=q.poll();
             q.notifyAll();
             return k;
         }
    }

    public static void main(String[] args) {

        BlockingQueue blockingQueue=new BlockingQueue(5);
        new Thread(
                ()->{
                        for (int i = 0; i < 10; i++) System.out.println("Produced " + blockingQueue.producer(i));
                }
        ).start();

        new Thread(
                ()->{
                    for (int i = 0; i < 10; i++) System.out.println("consumed " + blockingQueue.consume());
                }
        ).start();
    }
}
