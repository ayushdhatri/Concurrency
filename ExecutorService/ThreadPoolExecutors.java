package ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutors {
    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2),new CustumThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        for(int i = 1;i<=4;i++){
            executor.submit(() ->{
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException ex){}

                System.out.println("Task processed by: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}

class CustumThreadFactory implements  ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
       Thread th = new Thread(r);
       th.setPriority(Thread.NORM_PRIORITY);
       th.setDaemon(false);
       return th;

    }
    
}
