package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.lang.model.type.ExecutableType;

public class CompletableFuture {
    public static void main(String[] args){
        ExecutorService executors1 = Executors.newFixedThreadPool(5);
        // here there is no queue, and as soon as the request comes, more number of threads are created to handle the request., and each threads terminates
        // after 60 sec of idle
        ExecutorService executors2 = Executors.newCachedThreadPool();

        ExecutorService executor3 = Executors.newSingleThreadExecutor();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        Runnable task = ()->{
            System.out.println("Hello");
        };
        scheduledExecutorService.schedule(task , 3000  , TimeUnit.MILLISECONDS);
        scheduledExecutorService.shutdown();



    }
}
