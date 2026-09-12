- Always when you are doing concurrency, use the thread.join() method else your required output wont works well
because thread finishes up the execution faster and does not wait for threads.

- You can create threads using
 -> Using Thread Class implementing runnable 
 -> Using lambda
 -> Using ExecutorService 


 # Locks

 Using synchonized keyword:
 -> If I use synchonized keyword on a function call then that specific function of that class gets synchonized not other function available.
 -> Let say we have few static functions then in that we need to take class level locks.
 eg. synchonized(Inventory.class){
    
 }
