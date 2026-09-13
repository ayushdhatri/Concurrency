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



############################# What was the need of reentrant lock ######################
Without Reentrant Lock: 
public class BankAccount {
    private double balance = 100.0;
    private final ReentrantLock lock = new ReentrantLock();

    // Method 1: Needs to be thread-safe for standalone calls
    public void debit(double amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    // Method 2: Also needs to be thread-safe
    public void transferWithFee(double amount, double fee) {
        lock.lock(); // Holds the lock (holdCount = 1)
        try {
            // Reuses debit() logic to apply fee and transfer amount
            debit(amount); // Calls debit() -> requests lock again!
            debit(fee);
        } finally {
            lock.unlock();
        }
    }
}

Problem occure due to this:
- A thread enters transferWithFee() and acquires the lock.

- It then calls debit(amount).

- debit() asks: "Is the lock free?"

- The system sees the lock is busy (held by the thread itself).

- The thread is put to sleep, waiting for the lock to be released.

- The thread is now waiting for itself to release the lock before it can proceed to release it.

- Result: Instant, unrecoverable deadlock.

After Reentrant Lock:

- transferWithFee() acquires the lock $\to$ holdCount = 1, owner = Thread-A.

- It calls debit(amount).

- The lock checks: "Who holds me?" $\to$ Thread-A.

- Since the caller is already the owner, it grants immediate access $\to$ holdCount = 2

- debit() exits and calls unlock() $\to$ holdCount = 1 (lock remains held).

- transferWithFee() finishes and calls unlock() $\to$ holdCount = 0 (lock is fully released to other threads).