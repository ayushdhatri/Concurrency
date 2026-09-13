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


 Using locks:
 -> It gives more granular control

 In Reentrant Lock we have a parameter called fairness:
 If fairness is false then in that case, if a thread acquires a lock, perform its task, unlocks, it , then immedately after that it can 
 reacquire the lock(example in case of for loop the thread inside that loop can keep on reacquiring the lock and nobody stops it)
 - To solve this problem we can introduce some time of sleep,but solving it using sleep you have to pass the timeWaiting, which is difficult to predict
 - So reentrant locks introduced something called fairness, whose value when true ensures that the thread which acquires the lock should not reacquire
 it if other threads are in queue to use this resource.

 <-------------------Start--------------------------------------->

############# Before Fairness Output ############################
Look at Demo3.java
Thread 0 is using the resource
Thread 0 is using the resource
Thread 0 is using the resource
Thread 1 is using the resource
Thread 1 is using the resource
Thread 1 is using the resource
Thread 2 is using the resource
Thread 2 is using the resource
Thread 2 is using the resource
Thread 3 is using the resource
Thread 3 is using the resource
Thread 3 is using the resource
Thread 4 is using the resource
Thread 4 is using the resource
Thread 4 is using the resource


############# After Fairness Output ############################
Thread 0 is using the resource
Thread 1 is using the resource
Thread 2 is using the resource
Thread 3 is using the resource
Thread 4 is using the resource
Thread 0 is using the resource
Thread 1 is using the resource
Thread 2 is using the resource
Thread 3 is using the resource
Thread 4 is using the resource
Thread 0 is using the resource
Thread 1 is using the resource
Thread 2 is using the resource
Thread 3 is using the resource
Thread 4 is using the resource


Look how each thread is getting chance one after other
<---------------------End----------------------------------------->



