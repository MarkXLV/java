package multithreading.threads;

import static java.lang.Thread.currentThread;

public class ThreadTester {
    public static void main(String[] args) {
        System.out.println("Main is starting");

        Thread thread1=new Thread1("thread-1");
        thread1.start();

        Thread thread2=new Thread(new Thread2(),"thread-2");
        thread2.start();

        Thread thread3=new Thread(()-> System.out.println(currentThread().getName()),"thread-3");
        thread3.start();

        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                System.out.println(currentThread().getName());
            }
        };

        Thread thread4=new Thread(()-> System.out.println(currentThread().getName()),"thread-4");
        thread4.start();



        System.out.println("Main is exiting");

    }
}
