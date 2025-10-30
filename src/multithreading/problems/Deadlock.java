package multithreading.problems;

import multithreading.threads.Thread1;

public class Deadlock {

    public static void main(String[] args) {

        String lock1="lock1";
        String lock2="lock2";
        new Thread(
                ()->{
                    synchronized (lock1)
                    {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        synchronized (lock2)
                        {
                            System.out.println("lock acquired");
                        }
                    }
                }
        ).start();


        new Thread(
                ()->{
                    synchronized (lock2)
                    {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        synchronized (lock1)
                        {
                            System.out.println("lock acquired");
                        }
                    }
                }
        ).start();


        System.out.println("Main is exiting ...");
    }
}
