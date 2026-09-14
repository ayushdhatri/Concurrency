package LocksDemo;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Inventory{
    private int availableRooms;
    private final Lock lock = new ReentrantLock();
    ExecutorService

    public Inventory(int availableRooms){
        this.availableRooms = availableRooms;
    }

    public int getAvailableRooms(){
        return this.availableRooms;
    }

    public boolean tryReserve(){
        System.out.println(Thread.currentThread().getName() + " is trying to reserve the room");
        lock.lock();
        try{
            if(availableRooms > 0){
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException ex){}
                this.availableRooms--;
                System.out.println(Thread.currentThread().getName() + " has reserved the room");
                return true;
            }
            return false;
        }
        finally{
            lock.unlock();
        }
    }

}
public class Locks1 {

    public static void main(String[] args) throws InterruptedException{
            int count = 0;
        while( count < 1000){
            Inventory inventory = new Inventory(1);
            Runnable book = ()-> inventory.tryReserve();

            Thread thread1 = new Thread(book);
            Thread thread2 = new Thread(book);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("AvailableRooms are : " + inventory.getAvailableRooms());
            /*
              * so many times you will get to see that available values are -1 
              * it is because two thread are sometimes able to access the same data which cause race condition
            */

        }
    }
    
}
