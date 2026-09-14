package ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        Future<List<Integer>> futureObj = executor.submit(()->{
            List<Integer> output = new ArrayList<>();
            try{
                Thread.sleep(3000);
                output.add(300);
            }
            catch(InterruptedException ex){}
            return output;
        });

        try{
            List<Integer> result = futureObj.get();
            System.out.println(result.get(0));
        }
        catch(Exception ex){

        }

        executor.shutdown();


    }
    
}
