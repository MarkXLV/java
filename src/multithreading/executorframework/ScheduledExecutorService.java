package multithreading.executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService {
    public static void main(String[] args) {

    /*
            -- used when load is variable and short-lived
            Executors.newCachedThreadPool()    // dynamic creation and termination of threads as per tasks ,but risks is many threads can be created
    */

    java.util.concurrent.ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);
//    -- goes to the queue immediately so scheduler.shutdown() will wait for the task to get completed
     scheduledExecutorService.schedule(()-> System.out.println("Running after fixed intervals"),2, TimeUnit.SECONDS);

//     -- weather previous task completed ro not next task will begin after delay
//     scheduledExecutorService.scheduleAtFixedRate(()-> System.out.println("Running after fixed schedule interval of 2 SECONDS"),1,2, TimeUnit.SECONDS);
//     scheduledExecutorService.schedule(scheduledExecutorService::shutdown,10, TimeUnit.SECONDS);

//    scheduledExecutorService.scheduleWithFixedDelay(()-> System.out.println("Running after fixed delay of 2 SECONDS"),1,2, TimeUnit.SECONDS);
//    scheduledExecutorService.schedule(scheduledExecutorService::shutdown,10, TimeUnit.SECONDS);

    }
}
