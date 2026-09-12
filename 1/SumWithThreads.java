import java.util.concurrent.atomic.AtomicInteger;

public class SumWithThreads{
    public static int [] arr = new int[2000];
    public static AtomicInteger sum = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException{
        for(int i = 0;i<arr.length;i++)
            arr[i] = i+1;

        Thread[] threads = new Thread[4];// array of threads
        
        int chunkSize = arr.length / 4; // divide the lenght into chunks of 500

        for(int i = 0;i<4;i++){
            int start = i * chunkSize;
            int end = start + chunkSize;
            threads[i] = new Thread(new SumThread(start,end));
            threads[i].start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        System.out.println(sum.get());


    }
}

class SumThread implements Runnable{
    private final int start;
    private final int end;
    public SumThread(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override 
    public void run(){
        int localSum  = 0;
        for(int i = start;i < end;i++){
            localSum += SumWithThreads.arr[i];
        }

        SumWithThreads.sum.addAndGet(localSum);

    }
}