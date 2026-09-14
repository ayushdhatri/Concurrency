package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.lang.model.type.ExecutableType;

public class CompletableFuture {
    public static void main(String[] args){
        ExecutorService executors1 = Executors.newFixedThreadPool(5);
        // here there is no queue, and as soon as the request comes, more number of threads are created to handle the request., and each threads terminates
        // after 60 sec of idle
        ExecutorService executors2 = Executors.newCachedThreadPool();

        ExecutorService executor3 = Executors.newSingleThreadExecutor();
        



    }
}
