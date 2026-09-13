package LocksDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fairness{
    private static final Lock lock = new ReentrantLock(true);
    
    public void use(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is using the resource");
            Thread.sleep(1000);
        }
        catch(Exception ex){}
        finally{
            lock.unlock();
        }
    }

}
public class Demo3 {
    public static void main(String[] args){
        testFairness();
    }

    public static void testFairness(){
        Fairness fairness = new Fairness();
        for(int i = 0;i<5;i++){
            Thread thread = new Thread(()->{
                for(int j = 0;j<3;j++){
                    fairness.use();
                }
            }, "Thread "+ i);
            System.out.println("Thread " + i + " is waiting to use the resource");
            thread.start();
        }
        
    }
    
}
