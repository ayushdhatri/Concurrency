package LocksDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



class Inventoryy{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    public Inventoryy(){};

    private final Map<String, Integer> inventory = new HashMap<>();
    public int getPrice(String item){
        System.out.println(Thread.currentThread().getName() + " is trying to get the price of " + item);
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " axquired the read lock");
        try{
            try{
                Thread.sleep(1000);

            }
            catch(InterruptedException ex){

            }
            System.out.println(Thread.currentThread().getName() + " got the price of " + item);
            return inventory.get(item);
        }
        catch(Exception ex){}
        finally{
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the read lock");
        }
        return 0;

        
    }

    public void addItem(String item, int price){
        System.out.println(Thread.currentThread().getName() + " is trying to add the price  " + price + " for item" + item);
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " axquired the write lock");
        try{
            Thread.sleep(1000);
            inventory.put(item, price);
            System.out.println(Thread.currentThread().getName() + " Add the price of  " + item);
        }
        catch(InterruptedException ex){}
        finally{
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the write lock");
        }
    }


}
public class Demo4 {
    public static void main(String[] args) throws InterruptedException{
        Inventoryy inventoryy = new Inventoryy();
        inventoryy.addItem("item1", 100);
        inventoryy.addItem("item2", 200);
        inventoryy.addItem("item3", 300);
        inventoryy.addItem("item4", 400);

        Thread thread1 = new Thread(()-> inventoryy.getPrice("item1"), "Thread1");
        Thread thread2 = new Thread(()-> inventoryy.getPrice("item1"), "Thread2");
        Thread thread3 = new Thread(()-> inventoryy.addItem("item1", 500), "Thread3");

        thread1.start();
        thread2.start();
        thread3.start();


        thread1.join();thread2.join();thread3.join();
        



    }
    
}
